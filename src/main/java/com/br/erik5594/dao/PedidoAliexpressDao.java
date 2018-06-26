package com.br.erik5594.dao;

import com.br.erik5594.model.PedidoAliexpress;
import com.br.erik5594.model.Rastreamento;
import com.br.erik5594.model.StatusPedidoAliexpress;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PedidoAliexpressDao implements Serializable{

    @Inject
    private EntityManager manager;
    @Inject
    private RastreamentoDao rastreamentoDao;

    public List<PedidoAliexpress> getTodosPedidosAliexpress(){
        return manager.createQuery("from PedidoAliexpress", PedidoAliexpress.class).getResultList();
    }

    public void salvarListaPedidoAliexpress(List<PedidoAliexpress> listaPedidoAliexpress){
        for(PedidoAliexpress pedidoAliexpress : listaPedidoAliexpress){
            PedidoAliexpress pedidoAliexpressBanco = buscarPedidoAliexpress(pedidoAliexpress.getIdAliexpress());
            if(pedidoAliexpressBanco != null){
                if(pedidoAliexpressBanco.getRastreamento() == null
                        && pedidoAliexpress.getRastreamento() != null
                        && StringUtils.isNotBlank(pedidoAliexpress.getRastreamento().getCodigoRastreamento())){
                    Rastreamento rastreamento = rastreamentoDao.buscarRastreamento(pedidoAliexpress.getRastreamento().getCodigoRastreamento());
                    if(rastreamento != null){
                        pedidoAliexpressBanco.setRastreamento(rastreamento);
                    }
                    manager.merge(pedidoAliexpress);
                }
            }else{
                if(pedidoAliexpress.getRastreamento() != null && StringUtils.isNotBlank(pedidoAliexpress.getRastreamento().getCodigoRastreamento())){
                 Rastreamento rastreamento = rastreamentoDao.buscarRastreamento(pedidoAliexpress.getRastreamento().getCodigoRastreamento());
                 pedidoAliexpress.setRastreamento(rastreamento);
                }
                manager.persist(pedidoAliexpress);
            }
            manager.flush();
        }
    }

    public PedidoAliexpress buscarPedidoAliexpress(Long idAliexpress){
        try{
            return  manager.find(PedidoAliexpress.class, idAliexpress);
        }catch (NoResultException e){
            return null;
        }
    }

    public Long totalPedidosAliexpress(){
        String hql = "select count(*) from PedidoAliexpress";
        return (Long)manager.createQuery(hql).getSingleResult();
    }

    public List<PedidoAliexpress> pedidosVencendoDataLimite(){
        Calendar data = Calendar.getInstance();
        data.add(Calendar.DAY_OF_YEAR, 5);

        List<StatusPedidoAliexpress> status = new ArrayList<>();
        status.add(StatusPedidoAliexpress.CONCLUIDO);
        status.add(StatusPedidoAliexpress.DISPUTA);
        String hql = "from PedidoAliexpress where dataLimiteDisputa < :dataMaxima and statusPedidoAliexpress not in (:status)";

        return manager.createQuery(hql, PedidoAliexpress.class)
                .setParameter("dataMaxima",data, TemporalType.DATE)
                .setParameter("status",status)
                .getResultList();
    }
}