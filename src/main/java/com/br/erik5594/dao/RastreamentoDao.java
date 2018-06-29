package com.br.erik5594.dao;

import com.br.erik5594.model.Rastreamento;
import com.br.erik5594.model.StatusPedidoCorreios;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
        Calendar data = Calendar.getInstance();
        data.add(Calendar.HOUR_OF_DAY, -8);

        List<StatusPedidoCorreios> status = new ArrayList<>();
        status.add(StatusPedidoCorreios.ENTREGUE);
        status.add(StatusPedidoCorreios.DEVOLVIDO);
        status.add(StatusPedidoCorreios.EXTRAVIADO);
        String hql = "from Rastreamento " +
                "where (dataUltimaAtualizacao < :dataUltimaAtualizacao or dataUltimaAtualizacao is null) " +
                "and (status not in (:status) or status is null)";

        return manager.createQuery(hql, Rastreamento.class)
                .setParameter("dataUltimaAtualizacao",data, TemporalType.DATE)
                .setParameter("status",status)
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

    public Rastreamento atualizarRastreamento(Rastreamento rastreamento){
        manager.detach(rastreamento);
        Rastreamento rastreamentoBanco = buscarRastreamento(rastreamento.getCodigoRastreamento());
        if(rastreamentoBanco != null){
            rastreamentoBanco.setDataUltimaAtualizacao(rastreamento.getDataUltimaAtualizacao());
            rastreamentoBanco.setUrlImagemUltimoStatus(rastreamento.getUrlImagemUltimoStatus());
            rastreamentoBanco.setStatus(rastreamento.getStatus());
            return manager.merge(rastreamentoBanco);
        }
        return null;
    }



}