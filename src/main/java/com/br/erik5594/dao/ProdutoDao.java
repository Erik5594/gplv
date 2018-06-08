package com.br.erik5594.dao;

import com.br.erik5594.constantes.Teste;
import com.br.erik5594.model.Produto;

import java.io.Serializable;
import java.util.List;

public class ProdutoDao implements Serializable{

    public boolean salvarListaProdutos(List<Produto> produtos){
        Teste.produtos = produtos;
        return true;
    }

    public List<Produto> getTodosProdutos(){
        return Teste.produtos;
    }
}