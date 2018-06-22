package com.br.erik5594.bo;

import com.br.erik5594.dao.RastreamentoDao;
import com.br.erik5594.dto.RastreamentoDto;
import com.br.erik5594.model.Rastreamento;
import com.br.erik5594.util.cast.RastreamentoCast;
import com.br.erik5594.util.jpa.Transactional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RastreamentoBo implements Serializable{
    @Inject
    private RastreamentoDao rastreamentoDao;

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

    @Transactional
    public void adicionarRastreamento(RastreamentoDto rastreamentoDto){
        rastreamentoDao.adicionarRastreamento(RastreamentoCast.castRastreamentoDto(rastreamentoDto));
    }

}
