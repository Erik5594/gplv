package com.br.erik5594.dao;

import com.br.erik5594.model.Item;
import com.br.erik5594.model.PedidoAliexpress;
import com.br.erik5594.model.PedidoShopify;
import com.br.erik5594.model.Produto;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class ItemDao {
    @Inject
    private EntityManager manager;

    public Item buscarItem(int pedidoShopify, String produto){
        try{
            String hql = "from Item where id.pedidoShopify.numeroPedido = :pedidoShopify and id.produto.skuProduto = :produto and pedidoAliexpress is null";
            return manager.createQuery(hql, Item.class)
                    .setParameter("pedidoShopify", pedidoShopify)
                    .setParameter("produto", produto)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public void editarItem(Item item){
        manager.merge(item);
    }

    public void atualizarIdAliexpress(int pedidoShopify, String skuProduto, PedidoAliexpress pedidoAliexpress){
        Item item = buscarItem(pedidoShopify, skuProduto);
        if(item != null){
            item.setPedidoAliexpress(pedidoAliexpress);
            editarItem(item);
        }
    }
}
