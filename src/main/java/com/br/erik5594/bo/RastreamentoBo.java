package com.br.erik5594.bo;

import com.br.erik5594.dao.RastreamentoDao;
import com.br.erik5594.dto.PedidoShopifyDto;
import com.br.erik5594.dto.RastreamentoDto;
import com.br.erik5594.model.PedidoShopify;
import com.br.erik5594.model.Rastreamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RastreamentoBo implements Serializable{

    private RastreamentoDao rastreamentoDao = new RastreamentoDao();

    public boolean salvarListaRastreamento(List<RastreamentoDto> rastreamentosDto){
        List<Rastreamento> rastreamentos = new ArrayList<>();
        for(RastreamentoDto rastreamentoDto : rastreamentosDto){
            Rastreamento rastreamento = new Rastreamento();
            rastreamento.setUrlImagemUltimoStatus(rastreamentoDto.getUrlImagemUltimoStatus());
            rastreamento.setReclamacaoCorreios(null);
            rastreamento.setEventos(null);
            rastreamento.setDataUltimaAtualizacao(rastreamentoDto.getDataUltimaAtualizacao());
            rastreamento.setCodigoRastreamento(rastreamentoDto.getCodigoRastreamento());
            rastreamentos.add(rastreamento);
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
            RastreamentoDto rastreamentoDto = new RastreamentoDto();
            rastreamentoDto.setUrlImagemUltimoStatus(rastreamento.getUrlImagemUltimoStatus());
            rastreamentoDto.setReclamacaoCorreios(null);
            rastreamentoDto.setEventos(null);
            rastreamentoDto.setDataUltimaAtualizacao(rastreamento.getDataUltimaAtualizacao());
            rastreamentoDto.setCodigoRastreamento(rastreamento.getCodigoRastreamento());
            rastreamentosDto.add(rastreamentoDto);
        }
        return rastreamentosDto;
    }

}
