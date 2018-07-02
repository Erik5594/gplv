package com.br.erik5594.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.br.erik5594.client.RastreamentoClient;
import com.br.erik5594.client.domain.RetornoHubCorreios;
import com.br.erik5594.client.domain.RetornoHubDados;
import com.br.erik5594.constantes.Const;
import com.br.erik5594.dao.RastreamentoDao;
import com.br.erik5594.dto.RastreamentoDto;
import com.br.erik5594.dto.RastreamentoEventoDto;
import com.br.erik5594.model.Rastreamento;
import com.br.erik5594.model.StatusPedidoCorreios;
import com.br.erik5594.util.cast.RastreamentoCast;
import com.br.erik5594.util.jpa.Transactional;

public class RastreamentoBo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
    private RastreamentoDao rastreamentoDao;
    @Inject
    private RastreamentoClient rastreamentoClient;
    @Inject
    private RastreamentoEventoBo rastreamentoEventoBo;

    public List<RastreamentoDto> getTodosRastreamentos(){
        List<RastreamentoDto> rastreamentosDto = new ArrayList<>();
        List<Rastreamento> rastreamentos = rastreamentoDao.getTodosRastreamentos();
        if(rastreamentos == null){
            return rastreamentosDto;
        }
        for(Rastreamento rastreamento : rastreamentos){
            rastreamentosDto.add(RastreamentoCast.castRastreamento(rastreamento));
        }
        return rastreamentosDto;
    }

    public RastreamentoDto buscarRastreamento(String codRastreamento){
        return RastreamentoCast.castRastreamento(rastreamentoDao.buscarRastreamento(codRastreamento));
    }

    @Transactional
    public void adicionarRastreamento(RastreamentoDto rastreamentoDto){
        rastreamentoDao.adicionarRastreamento(RastreamentoCast.castRastreamentoDto(rastreamentoDto));
    }

    @Transactional
    public void atualizarRastreamentoCorreios(String codRastreamento){
        salvarAtualizacaoBanco(consultaEventosHub(codRastreamento));
    }

    private RastreamentoDto consultaEventosHub(String codRastreamento) {
        RastreamentoDto rastreamentoDto;
        RetornoHubCorreios retornoHubCorreios = rastreamentoClient.atualizarEventosRastreamento(codRastreamento);
        if(retornoHubCorreios == null || "NOK".equals(retornoHubCorreios.getRetorno())){
            rastreamentoDto = new RastreamentoDto();
            rastreamentoDto.setCodigoRastreamento(codRastreamento);
            rastreamentoDto.setStatus(StatusPedidoCorreios.NAO_ENCONTRADO);
            rastreamentoDto.setDataUltimaAtualizacao(new Date());
        }else{
            rastreamentoDto = new RastreamentoDto();
            rastreamentoDto.setCodigoRastreamento(codRastreamento);
            rastreamentoDto.setUrlImagemUltimoStatus(retornoHubCorreios.getImagem_status());
            rastreamentoDto.setDataUltimaAtualizacao(new Date());
            List<RastreamentoEventoDto> listaEventos = new ArrayList<>();
            List<RetornoHubDados> dadosHub = retornoHubCorreios.getResult();
            if(dadosHub != null && !dadosHub.isEmpty()){
                for(RetornoHubDados evento : retornoHubCorreios.getResult()){
                    RastreamentoEventoDto eventoDto = new RastreamentoEventoDto();
                    eventoDto.setDataEvento(evento.getData());
                    eventoDto.setLocalEvento(evento.getLocal());
                    String mensagemRetorno = evento.getRetorno();
                    if(StringUtils.isNotBlank(mensagemRetorno) && mensagemRetorno.length() < 255){
                        eventoDto.setMensagemEvento(mensagemRetorno);
                    }else{
                        eventoDto.setMensagemEvento(mensagemRetorno.substring(0,200)+"... ->Para mais informações acess o site dos Correios.");
                    }
                    eventoDto.setRastreamento(rastreamentoDto);
                    listaEventos.add(eventoDto);
                }
            }
            rastreamentoDto.setStatus(obterStatusRastreamento(listaEventos));
            rastreamentoDto.setEventos(listaEventos);
        }
        return rastreamentoDto;
    }

    private StatusPedidoCorreios obterStatusRastreamento(List<RastreamentoEventoDto> listaEventos){
        if(listaEventos != null && !listaEventos.isEmpty()){
            RastreamentoEventoDto eventoObterResultado = null;
            Date dataUltimaAtualizacao = null;
            for(RastreamentoEventoDto rastreamentoEventoDto : listaEventos){
                if(dataUltimaAtualizacao == null){
                    dataUltimaAtualizacao = rastreamentoEventoDto.getDataEvento();
                    eventoObterResultado = rastreamentoEventoDto;
                }else if(rastreamentoEventoDto.getDataEvento().after(dataUltimaAtualizacao)){
                    eventoObterResultado = rastreamentoEventoDto;
                }
            }
            return obterStatusRastreamento(eventoObterResultado);
        }
        return StatusPedidoCorreios.INDEFINIDO;
    }

    private StatusPedidoCorreios obterStatusRastreamento(RastreamentoEventoDto evento){
        if(evento != null && StringUtils.isNotBlank(evento.getMensagemEvento())){
            Date hoje = new Date();
            Calendar data = Calendar.getInstance();
            if(evento.getMensagemEvento().contains(Const.MSG_ENTREGUE)){
                return StatusPedidoCorreios.ENTREGUE;
            }
            if(evento.getMensagemEvento().contains(Const.MSG_CORREIOS)
                    || evento.getMensagemEvento().contains(Const.MSG_FISCALIZACAO)
                    || evento.getMensagemEvento().contains(Const.MSG_FISCALIZACAO_FINALIZADA)){
                if(evento.getDataEvento() != null){
                    data.setTime(evento.getDataEvento());
                    if(evento.getMensagemEvento().contains(Const.MSG_FISCALIZACAO)){
                        data.add(Calendar.DAY_OF_YEAR, 90);
                    }else{
                        data.add(Calendar.DAY_OF_YEAR, 60);
                    }

                    if(hoje.after(data.getTime())){
                        return StatusPedidoCorreios.ATRASADO;
                    }else if (evento.getMensagemEvento().contains(Const.MSG_FISCALIZACAO)){
                        return StatusPedidoCorreios.FISCALIZACAO;
                    }else{
                        return StatusPedidoCorreios.CORREIOS;
                    }
                }else{
                    return StatusPedidoCorreios.INDEFINIDO;
                }
            }
            if(evento.getMensagemEvento().contains(Const.MSG_ENTREGA_HOJE)
                    || evento.getMensagemEvento().contains(Const.MSG_CARTEIRO_NAO_ATENDIDO)
                    || evento.getMensagemEvento().contains(Const.MSG_PROXIMO_DIA_UTIL)
                    || evento.getMensagemEvento().contains(Const.MSG_ENDERECO_INCORRETO)){
                return StatusPedidoCorreios.PENDENTE;
            }
            if(evento.getMensagemEvento().contains(Const.MSG_AGUARDANDO_RETIRADA)
                    || evento.getMensagemEvento().contains(Const.MSG_AGUARDANDO_RETIRADA2)){
                return StatusPedidoCorreios.AGUARDANDO_RETIRADA;
            }
            if(evento.getMensagemEvento().contains(Const.MSG_DEVOLVIDO)
                    || evento.getMensagemEvento().contains(Const.MSG_DEVOLVIDO2)){
                return StatusPedidoCorreios.DEVOLVIDO;
            }
            if(evento.getMensagemEvento().contains(Const.MSG_EXTRAVIADO)){
                return StatusPedidoCorreios.EXTRAVIADO;
            }
            if(evento.getMensagemEvento().contains(Const.MSG_OBJETO_RECEBIDO)
                    && evento.getLocalEvento().contains("CHINA")){
                return StatusPedidoCorreios.DEVOLVIDO;
            }
        }
        return StatusPedidoCorreios.INDEFINIDO;
    }

    public void salvarAtualizacaoBanco(RastreamentoDto rastreamentoDtoAtualizado){
        Rastreamento rastreamento = RastreamentoCast.castRastreamentoDto(rastreamentoDtoAtualizado);
        rastreamentoDao.atualizarRastreamento(rastreamento);
        if(rastreamentoDtoAtualizado.getEventos() != null && !rastreamentoDtoAtualizado.getEventos().isEmpty()){
            rastreamentoEventoBo.adcionarEventos(rastreamentoDtoAtualizado.getEventos(), rastreamentoDtoAtualizado);
        }
    }
}
