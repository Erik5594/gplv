package com.br.erik5594.util.cast;

import com.br.erik5594.dto.ReclamacaoCorreiosDto;
import com.br.erik5594.model.ReclamacaoCorreios;

public class ReclamacaoCorreiosCast {
    public static ReclamacaoCorreios castReclamacaoCorreiosDto(ReclamacaoCorreiosDto reclamacaoCorreiosDto){
        if(reclamacaoCorreiosDto == null){
            return null;
        }
        ReclamacaoCorreios reclamacaoCorreios = new ReclamacaoCorreios();
        reclamacaoCorreios.setDataReclamacao(reclamacaoCorreiosDto.getDataReclamacao());
        reclamacaoCorreios.setDataRetornoCorreios(reclamacaoCorreiosDto.getDataRetornoCorreios());
        reclamacaoCorreios.setIdReclamacao(reclamacaoCorreiosDto.getIdReclamacao());
        reclamacaoCorreios.setRetornoCorreios(reclamacaoCorreiosDto.getRetornoCorreios());
        reclamacaoCorreios.setRastreamento(RastreamentoCast.castRastreamentoDto(reclamacaoCorreiosDto.getRastreamento()));
        return reclamacaoCorreios;
    }

    public static ReclamacaoCorreiosDto castReclamacaoCorreios(ReclamacaoCorreios reclamacaoCorreios){
        if(reclamacaoCorreios == null){
            return null;
        }
        ReclamacaoCorreiosDto reclamacaoCorreiosDto = new ReclamacaoCorreiosDto();
        reclamacaoCorreiosDto.setDataReclamacao(reclamacaoCorreios.getDataReclamacao());
        reclamacaoCorreiosDto.setDataRetornoCorreios(reclamacaoCorreios.getDataRetornoCorreios());
        reclamacaoCorreiosDto.setIdReclamacao(reclamacaoCorreios.getIdReclamacao());
        reclamacaoCorreiosDto.setRetornoCorreios(reclamacaoCorreios.getRetornoCorreios());
        reclamacaoCorreiosDto.setRastreamento(RastreamentoCast.castRastreamento(reclamacaoCorreios.getRastreamento()));
        return reclamacaoCorreiosDto;
    }
}
