package com.br.erik5594.bo;

import com.br.erik5594.dao.RastreamentoDao;
import com.br.erik5594.dto.PedidoShopifyDto;
import com.br.erik5594.dto.RastreamentoDto;
import com.br.erik5594.model.PedidoShopify;
import com.br.erik5594.model.Rastreamento;
import com.br.erik5594.util.cast.RastreamentoCast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RastreamentoBo implements Serializable{

    private RastreamentoDao rastreamentoDao = new RastreamentoDao();

    public boolean salvarListaRastreamento(List<RastreamentoDto> rastreamentosDto){
        List<Rastreamento> rastreamentos = new ArrayList<>();
        for(RastreamentoDto rastreamentoDto : rastreamentosDto){
            rastreamentos.add(RastreamentoCast.castRastreamentoDto(rastreamentoDto));
        }
        return rastreamentoDao.salvarListaRastreamento(rastreamentos);
    }

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

    public boolean adicionarRastreamento(RastreamentoDto rastreamentoDto){
        return rastreamentoDao.adicionarRastreamento(RastreamentoCast.castRastreamentoDto(rastreamentoDto));
    }

}
