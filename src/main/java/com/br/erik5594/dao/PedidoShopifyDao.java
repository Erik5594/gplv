package com.br.erik5594.dao;

import com.br.erik5594.model.Cliente;
import com.br.erik5594.model.Item;
import com.br.erik5594.model.PedidoShopify;
import com.br.erik5594.model.Produto;
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
                }
                if(pedidoShopify.getItens() != null && !pedidoShopify.getItens().isEmpty()){
                    for(Item item : pedidoShopify.getItens()){
                        if(item.getProduto() != null && StringUtils.isNotBlank(item.getProduto().getSkuProduto())){
                            Produto produto = produtoDao.buscarProduto(item.getProduto().getSkuProduto());
                            item.setProduto(produto);
                        }else{
                            item.setProduto(null);
                        }
                    }
                }
                manager.persist(pedidoShopify);
            }else if(PedidoShopifyCast.adicionarAlteracoes(pedidoShopifyBanco, pedidoShopify)){
                manager.merge(pedidoShopifyBanco);
            }
        }
    }

    public List<PedidoShopify> getTodosPedidosShopify(){
        return manager.createQuery("from PedidoShopify", PedidoShopify.class)
                .getResultList();
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