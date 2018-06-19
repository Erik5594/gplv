package com.br.erik5594.dao;

import com.br.erik5594.constantes.Teste;
import com.br.erik5594.model.PedidoAliexpress;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PedidoAliexpressDao implements Serializable{

    public boolean salvarListaPedidoAliexpress(List<PedidoAliexpress> pedidosAliexpress){
        Teste.pedidosAliexpress = pedidosAliexpress;
        return true;
    }

    public List<PedidoAliexpress> getTodosPedidosAliexpress(){
        return Teste.pedidosAliexpress;
    }

    public boolean adicionarPedidoAliexpress(PedidoAliexpress pedidoAliexpress){
        if(Teste.pedidosAliexpress != null){
            if(!Teste.pedidosAliexpress.contains(pedidoAliexpress)){
                Teste.pedidosAliexpress.add(pedidoAliexpress);
                return true;
            }
        }else{
            Teste.pedidosAliexpress = new ArrayList<>();
            return adicionarPedidoAliexpress(pedidoAliexpress);
        }
        return false;
    }
}