package com.br.erik5594.dao;

import com.br.erik5594.model.*;
import com.br.erik5594.util.cast.PedidoShopifyCast;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.Serializable;
import java.util.List;

public class PedidoShopifyDao implements Serializable{

    @Inject
    private EntityManager manager;
    @Inject
    private ProdutoDao produtoDao;
    @Inject
    private ClienteDao clienteDao;

    public void salvarListaPedidoShopify(List<PedidoShopify> pedidosShopify) throws Exception{
        for(PedidoShopify pedidoShopify : pedidosShopify){
            PedidoShopify pedidoShopifyBanco = buscarPedidoShopify(pedidoShopify.getNumeroPedido());
            if(pedidoShopifyBanco == null){

                if(pedidoShopify.getCliente() != null){
                    String telefone = pedidoShopify.getCliente().getTelefone();
                    String email = pedidoShopify.getCliente().getEmail();
                    Cliente cliente = clienteDao.buscarCliente(email, telefone);
                    pedidoShopify.setCliente(cliente);
                }else{
                    Cliente cliente = clienteDao.buscarCliente(null, null);
                    pedidoShopify.setCliente(cliente);
                }

                if(pedidoShopify.getItens() != null && !pedidoShopify.getItens().isEmpty()){
                    for(Item item : pedidoShopify.getItens()){
                        ItemPk itemPk = new ItemPk();
                        Produto produto = produtoDao.buscarProduto(item.getId().getProduto().getSkuProduto());
                        if(produto == null){
                            produto = produtoDao.buscarProduto("nao-identificado");
                        }
                        itemPk.setProduto(produto);
                        itemPk.setPedidoShopify(pedidoShopify);
                        item.setId(itemPk);
                    }
                }

                manager.persist(pedidoShopify);
            }else if(PedidoShopifyCast.adicionarAlteracoes(pedidoShopifyBanco, pedidoShopify)){
                manager.merge(pedidoShopifyBanco);
            }
        }
    }

    public PedidoShopify buscarPedidoShopify(int numeroPedido){
        try {
            return manager.createQuery("from PedidoShopify where numeroPedido = :numeroPedido", PedidoShopify.class)
                    .setParameter("numeroPedido", numeroPedido)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public Long totalPedidos(){
        String hql = "select count(*) from PedidoShopify";
        return (Long)manager.createQuery(hql).getSingleResult();
    }
}