package com.br.erik5594.dao;

import com.br.erik5594.model.Item;
import com.br.erik5594.model.PedidoShopify;
import com.br.erik5594.model.Produto;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class ItemDao {
    @Inject
    private EntityManager manager;

    public Item buscarItem(PedidoShopify pedidoShopify, Produto produto){
        try{

            return manager.createQuery("from Item where pedidoShopify = :pedidoShopify and produto = :produto", Item.class)
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
}
