package com.br.erik5594.util.cast;

import com.br.erik5594.dto.RastreamentoDto;
import com.br.erik5594.dto.RastreamentoEventoDto;
import com.br.erik5594.model.Rastreamento;
import com.br.erik5594.model.RastreamentoEventos;

import java.util.ArrayList;
import java.util.List;

public class RastreamentoCast {
    public static RastreamentoDto castRastreamento(Rastreamento rastreamento){
        if(rastreamento == null){
            return null;
        }
        RastreamentoDto rastreamentoDto = new RastreamentoDto();
        rastreamentoDto.setDataUltimaAtualizacao(rastreamento.getDataUltimaAtualizacao());
        rastreamentoDto.setCodigoRastreamento(rastreamento.getCodigoRastreamento());
        rastreamentoDto.setReclamacaoCorreios(ReclamacaoCorreiosCast.castReclamacaoCorreios(rastreamento.getReclamacaoCorreios()));
        rastreamentoDto.setUrlImagemUltimoStatus(rastreamento.getUrlImagemUltimoStatus());
        rastreamentoDto.setStatus(rastreamento.getStatus());
        List<RastreamentoEventos> eventos = rastreamento.getEventos();
        List<RastreamentoEventoDto> eventosDto = new ArrayList<>();
        if(eventos != null && !eventos.isEmpty()) {
            for (RastreamentoEventos evento : eventos) {
                RastreamentoEventoDto eventoDto = RastreamentoEventosCast.castRastreamentoEventos(evento);
                eventosDto.add(eventoDto);
            }
        }else{
            eventosDto = null;
        }
        rastreamentoDto.setEventos(eventosDto);
        return rastreamentoDto;
    }

    public static Rastreamento castRastreamentoDto(RastreamentoDto rastreamentoDto){
        if(rastreamentoDto == null){
            return null;
        }
        Rastreamento rastreamento = new Rastreamento();
        rastreamento.setDataUltimaAtualizacao(rastreamentoDto.getDataUltimaAtualizacao());
        rastreamento.setCodigoRastreamento(rastreamentoDto.getCodigoRastreamento());
        rastreamento.setReclamacaoCorreios(ReclamacaoCorreiosCast.castReclamacaoCorreiosDto(rastreamentoDto.getReclamacaoCorreios()));
        rastreamento.setUrlImagemUltimoStatus(rastreamentoDto.getUrlImagemUltimoStatus());
        rastreamento.setStatus(rastreamentoDto.getStatus());
        List<RastreamentoEventos> eventos = new ArrayList<>();
        List<RastreamentoEventoDto> eventosDto = rastreamentoDto.getEventos();
        if(eventosDto != null && !eventosDto.isEmpty()) {
            for (RastreamentoEventoDto eventoDto : eventosDto) {
                RastreamentoEventos evento = RastreamentoEventosCast.castRastreamentoEventosDto(eventoDto);
                eventos.add(evento);
            }
        }else{
            eventos = null;
        }
        rastreamento.setEventos(eventos);
        return rastreamento;
    }
}
