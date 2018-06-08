package com.br.erik5594.dao;

import com.br.erik5594.constantes.Teste;
import com.br.erik5594.model.Item;
import com.br.erik5594.model.PedidoShopify;

import java.io.Serializable;
import java.util.List;

public class ItemDao implements Serializable{

    public boolean salvarListaItens(List<Item> itens){
        Teste.itens = itens;
        return true;
    }

    public List<Item> getTodosItens(){
        return Teste.itens;
    }
}