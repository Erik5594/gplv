package com.br.erik5594.dao;

import com.br.erik5594.model.Produto;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.Serializable;
import java.util.List;

public class ProdutoDao implements Serializable{

    @Inject
    private EntityManager manager;

    public void salvarListaProdutos(List<Produto> produtos){
        for(Produto produto : produtos){
            if(buscarProduto(produto.getSkuProduto()) == null){
                manager.persist(produto);
            }
        }
    }

    public List<Produto> getTodosProdutos(){
        return manager.createQuery("from Produto", Produto.class).getResultList();
    }

    public Produto buscarProduto(String skuProduto){
        try{
            return manager.createQuery("from Produto where upper(skuProduto) = :sku", Produto.class)
                    .setParameter("sku", skuProduto.toUpperCase())
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public Long totalPrdutos(){
        String hql = "select count(*) from Produto";
        return (Long)manager.createQuery(hql).getSingleResult();
    }
}