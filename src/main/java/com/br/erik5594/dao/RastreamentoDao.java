package com.br.erik5594.dao;

import com.br.erik5594.model.Rastreamento;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.Serializable;
import java.util.List;

public class RastreamentoDao implements Serializable{
    @Inject
    private EntityManager manager;

    public void adicionarRastreamento(Rastreamento rastreamento){
        if(buscarRastreamento(rastreamento.getCodigoRastreamento()) == null){
            manager.persist(rastreamento);
        }
    }

    public List<Rastreamento> getTodosRastreamentos(){
        return manager.createQuery("from Rastreamento", Rastreamento.class)
                .getResultList();
    }

    public Rastreamento buscarRastreamento(String codRastreamento){
        try{
            return manager.createQuery("from Rastreamento where upper(codigoRastreamento) = :codRastreamento", Rastreamento.class)
                    .setParameter("codRastreamento", codRastreamento.toUpperCase())
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

}