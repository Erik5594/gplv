package com.br.erik5594.util.cast;

import com.br.erik5594.dto.RastreamentoEventoDto;
import com.br.erik5594.model.RastreamentoEventos;

public class RastreamentoEventosCast {
    public static RastreamentoEventos castRastreamentoEventosDto(RastreamentoEventoDto rastreamentoEventoDto){
        if(rastreamentoEventoDto == null){
            return null;
        }
        RastreamentoEventos rastreamentoEventos = new RastreamentoEventos();
        rastreamentoEventos.setDataEvento(rastreamentoEventoDto.getDataEvento());
        rastreamentoEventos.setIdEvento(rastreamentoEventoDto.getIdEvento());
        rastreamentoEventos.setLocalEvento(rastreamentoEventoDto.getLocalEvento());
        rastreamentoEventos.setMensagemEvento(rastreamentoEventoDto.getMensagemEvento());
        rastreamentoEventos.setRastreamento(RastreamentoCast.castRastreamentoDto(rastreamentoEventoDto.getRastreamento()));
        rastreamentoEventos.setStatus(rastreamentoEventoDto.getStatus());
        return rastreamentoEventos;
    }

    public static RastreamentoEventoDto castRastreamentoEventos(RastreamentoEventos rastreamentoEventos){
        if(rastreamentoEventos == null){
            return null;
        }
        RastreamentoEventoDto rastreamentoEventoDto = new RastreamentoEventoDto();
        rastreamentoEventoDto.setDataEvento(rastreamentoEventos.getDataEvento());
        rastreamentoEventoDto.setIdEvento(rastreamentoEventos.getIdEvento());
        rastreamentoEventoDto.setLocalEvento(rastreamentoEventos.getLocalEvento());
        rastreamentoEventoDto.setMensagemEvento(rastreamentoEventos.getMensagemEvento());
        rastreamentoEventoDto.setRastreamento(RastreamentoCast.castRastreamento(rastreamentoEventos.getRastreamento()));
        rastreamentoEventoDto.setStatus(rastreamentoEventos.getStatus());
        return rastreamentoEventoDto;
    }
}
