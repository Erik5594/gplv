package com.br.erik5594.bo;

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
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RastreamentoBo implements Serializable{
    @Inject
    private RastreamentoDao rastreamentoDao;
    @Inject
    private RastreamentoClient rastreamentoClient;

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

    public RastreamentoDto consultarRastreamentoCorreios(String codRastreamento){
        RetornoHubCorreios retornoHubCorreios = rastreamentoClient.atualizarEventosRastreamento(codRastreamento);
        if(retornoHubCorreios == null){
            return null;
        }
        RastreamentoDto rastreamentoDto = new RastreamentoDto();
        rastreamentoDto.setCodigoRastreamento(codRastreamento);
        rastreamentoDto.setUrlImagemUltimoStatus(retornoHubCorreios.getImagem_status());
        rastreamentoDto.setDataUltimaAtualizacao(new Date());
        List<RastreamentoEventoDto> listaEventos = new ArrayList<>();
        for(RetornoHubDados evento : retornoHubCorreios.getResult()){
            RastreamentoEventoDto eventoDto = new RastreamentoEventoDto();
            eventoDto.setDataEvento(evento.getData());
            eventoDto.setLocalEvento(evento.getLocal());
            eventoDto.setMensagemEvento(evento.getRetorno());
            eventoDto.setStatus(retornaStatusEvento(evento));
            eventoDto.setRastreamento(rastreamentoDto);
            listaEventos.add(eventoDto);
        }
        rastreamentoDto.setEventos(listaEventos);
        return rastreamentoDto;
    }

    private StatusPedidoCorreios retornaStatusEvento(RetornoHubDados evento){
        String mensagemRetorno = evento.getRetorno();
        Calendar dataAtraso = Calendar.getInstance();
        dataAtraso.setTime(evento.getData());
        if(StringUtils.isNotBlank(mensagemRetorno)) {
            if(mensagemRetorno.contains(Const.MSG_ENTREGUE)){
                return StatusPedidoCorreios.ENTREGUE;
            }
            if(mensagemRetorno.contains(Const.MSG_CORREIOS)){
                dataAtraso.add(Calendar.DAY_OF_YEAR, 60);
            }
        }

        return StatusPedidoCorreios.INDEFINIDO;
    }
}
