package com.br.erik5594.dao;

import com.br.erik5594.model.Rastreamento;
import com.br.erik5594.model.RastreamentoEventos;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.Serializable;
import java.util.List;

public class RastreamentoEventosDao implements Serializable{
    @Inject
    private EntityManager manager;

    public List<RastreamentoEventos> buscarEventos(Rastreamento rastreamento){
        String hql = "from RastreamentoEventos where rastreamento = :rastreamento";
        try{
            return manager.createQuery(hql, RastreamentoEventos.class)
                    .setParameter("rastreamento", rastreamento)
                    .getResultList();
        }catch (NoResultException e){
            return null;
        }
    }

    public void salvarEvento(RastreamentoEventos evento){
        if(evento.getIdEvento() == null){
            manager.persist(evento);
        }
    }

}