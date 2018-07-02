package com.br.erik5594.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import com.br.erik5594.dao.RastreamentoEventosDao;
import com.br.erik5594.dto.RastreamentoDto;
import com.br.erik5594.dto.RastreamentoEventoDto;
import com.br.erik5594.model.Rastreamento;
import com.br.erik5594.model.RastreamentoEventos;
import com.br.erik5594.util.cast.RastreamentoCast;
import com.br.erik5594.util.cast.RastreamentoEventosCast;
import com.br.erik5594.util.jpa.Transactional;

public class RastreamentoEventoBo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
    private RastreamentoEventosDao rastreamentoEventosDao;

    @Transactional
    public void adcionarEventos(List<RastreamentoEventoDto> eventosDto, RastreamentoDto rastreamentoDto){
        if(eventosDto != null && !eventosDto.isEmpty() && rastreamentoDto != null){
            Rastreamento rastreamento = RastreamentoCast.castRastreamentoDto(rastreamentoDto);
            List<RastreamentoEventos> eventosBanco = rastreamentoEventosDao.buscarEventos(rastreamento);
            if(eventosBanco != null && !eventosBanco.isEmpty()){
                List<RastreamentoEventoDto>  eventosAdicionar = retornaApenasNaoExistenteBanco(eventosDto, eventosBanco);
                adicionarEventos(eventosAdicionar, rastreamento);
            }else{
                adicionarEventos(eventosDto, rastreamento);
            }
        }
    }

    private List<RastreamentoEventoDto> retornaApenasNaoExistenteBanco(List<RastreamentoEventoDto> eventosDto, List<RastreamentoEventos> eventosBanco) {
        List<RastreamentoEventoDto> adicionarEventosDto = new ArrayList<>();
        for(RastreamentoEventoDto eventoDto : eventosDto){
            boolean encontrou = false;
            for (RastreamentoEventos eventoBanco : eventosBanco){
                if(Objects.equals(eventoBanco.getDataEvento().getTime(), eventoDto.getDataEvento().getTime())
                        && Objects.equals(eventoBanco.getMensagemEvento(), eventoDto.getMensagemEvento())
                        && Objects.equals(eventoBanco.getLocalEvento(), eventoDto.getLocalEvento())){
                    encontrou = true;
                    break;
                }
            }
            if(!encontrou){
                adicionarEventosDto.add(eventoDto);
            }
        }
        return adicionarEventosDto;
    }

    private void adicionarEventos(List<RastreamentoEventoDto> listaEventosDto, Rastreamento rastreamento){
        if(listaEventosDto != null && !listaEventosDto.isEmpty()) {
            for (RastreamentoEventoDto eventoDto : listaEventosDto) {
                RastreamentoEventos evento = RastreamentoEventosCast.castRastreamentoEventosDto(eventoDto, rastreamento);
                rastreamentoEventosDao.salvarEvento(evento);
            }
        }
    }
}
