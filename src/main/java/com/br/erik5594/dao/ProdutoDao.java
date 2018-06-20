package com.br.erik5594.dao;

import com.br.erik5594.constantes.Teste;
import com.br.erik5594.model.Produto;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class ProdutoDao implements Serializable{

    @Inject
    private EntityManager manager;

    public void salvarListaProdutos(List<Produto> produtos){
        for(Produto produto : produtos){
            manager.persist(produto);
        }
    }

    public List<Produto> getTodosProdutos(){
        return Teste.produtos;
    }

    public Produto buscarProduto(String skuProduto){
        Produto produto = new Produto();
        produto.setSkuProduto(skuProduto);
        int indexProduto = Teste.produtos.indexOf(produto);
        if(indexProduto < 0){
            return null;
        }
        return Teste.produtos.get(indexProduto);
    }
}