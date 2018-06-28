package com.br.erik5594.dao;

import com.br.erik5594.model.PedidoAliexpress;
import com.br.erik5594.model.PedidoShopify;
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
    @Inject
    private ItemDao itemDao;
    @Inject
    private PedidoShopifyDao pedidoShopifyDao;

    public List<PedidoAliexpress> getTodosPedidosAliexpress(){
        return manager.createQuery("from PedidoAliexpress", PedidoAliexpress.class).getResultList();
    }

    public void salvarListaPedidoAliexpress(List<PedidoAliexpress> listaPedidoAliexpress){
        for(PedidoAliexpress pedidoAliexpress : listaPedidoAliexpress){
            PedidoAliexpress pedidoAliexpressBanco = buscarPedidoAliexpress(pedidoAliexpress.getIdAliexpress());
            if(pedidoAliexpressBanco != null){
                setarRastreamento(pedidoAliexpress, pedidoAliexpressBanco);
                setarDataLimite(pedidoAliexpressBanco, pedidoAliexpress.getNumeroPedidoShopify());
                manager.merge(pedidoAliexpressBanco);
            }else{
                if(pedidoAliexpress.getRastreamento() != null && StringUtils.isNotBlank(pedidoAliexpress.getRastreamento().getCodigoRastreamento())){
                 Rastreamento rastreamento = rastreamentoDao.buscarRastreamento(pedidoAliexpress.getRastreamento().getCodigoRastreamento());
                 pedidoAliexpress.setRastreamento(rastreamento);
                }
                setarDataLimite(pedidoAliexpress, pedidoAliexpress.getNumeroPedidoShopify());
                manager.persist(pedidoAliexpress);
            }
            itemDao.atualizarIdAliexpress(pedidoAliexpress.getNumeroPedidoShopify(), pedidoAliexpress.getSkuProduto(), pedidoAliexpress);
        }
    }

    public void atualizarStatusPedidosAliexpress(List<PedidoAliexpress> listaPedidoAliexpress){
        for(PedidoAliexpress pedidoAliexpress : listaPedidoAliexpress){
            PedidoAliexpress pedidoAliexpressBanco = buscarPedidoAliexpress(pedidoAliexpress.getIdAliexpress());
            if(pedidoAliexpressBanco != null){
                pedidoAliexpressBanco.setStatusPedidoAliexpress(pedidoAliexpress.getStatusPedidoAliexpress());
                manager.merge(pedidoAliexpressBanco);
            }
        }
    }

    private void setarRastreamento(PedidoAliexpress pedidoAliexpress, PedidoAliexpress pedidoAliexpressBanco) {
        if(pedidoAliexpressBanco.getRastreamento() == null
                && pedidoAliexpress.getRastreamento() != null
                && StringUtils.isNotBlank(pedidoAliexpress.getRastreamento().getCodigoRastreamento())){
            Rastreamento rastreamento = rastreamentoDao.buscarRastreamento(pedidoAliexpress.getRastreamento().getCodigoRastreamento());
            if(rastreamento != null){
                pedidoAliexpressBanco.setRastreamento(rastreamento);
            }
        }
    }

    private void setarDataLimite(PedidoAliexpress pedidoAliexpressBanco, int numeroPedidoShopify) {
        if(pedidoAliexpressBanco.getDataLimiteDisputa() == null){
            PedidoShopify pedidoShopify = pedidoShopifyDao.buscarPedidoShopify(numeroPedidoShopify);
            if(pedidoShopify != null && pedidoShopify.getDataPedido() != null){
                Calendar data = Calendar.getInstance();
                data.setTime(pedidoShopify.getDataPedido());
                data.add(Calendar.DAY_OF_YEAR, 90);
                pedidoAliexpressBanco.setDataLimiteDisputa(data.getTime());
            }
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
        status.add(StatusPedidoAliexpress.REEMBOLSADO);
        status.add(StatusPedidoAliexpress.DISPUTA);
        String hql = "from PedidoAliexpress where dataLimiteDisputa < :dataMaxima and statusPedidoAliexpress not in (:status)";

        return manager.createQuery(hql, PedidoAliexpress.class)
                .setParameter("dataMaxima",data, TemporalType.DATE)
                .setParameter("status",status)
                .getResultList();
    }
}