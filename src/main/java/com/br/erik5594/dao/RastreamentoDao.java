package com.br.erik5594.dao;

import com.br.erik5594.constantes.Teste;
import com.br.erik5594.model.Rastreamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RastreamentoDao implements Serializable{

    public boolean salvarListaRastreamento(List<Rastreamento> rastreamentos){
        Teste.rastreamentos = rastreamentos;
        return true;
    }

    public boolean adicionarRastreamento(Rastreamento rastreamento){
        if(Teste.rastreamentos != null){
            if(!Teste.rastreamentos.contains(rastreamento)){
                Teste.rastreamentos.add(rastreamento);
                return true;
            }
        }else{
            Teste.rastreamentos = new ArrayList<>();
            return adicionarRastreamento(rastreamento);
        }
        return false;
    }

    public List<Rastreamento> getTodosRastreamentos(){
        return Teste.rastreamentos;
    }
}