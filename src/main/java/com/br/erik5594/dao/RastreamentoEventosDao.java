package com.br.erik5594.dao;

import com.br.erik5594.constantes.Teste;
import com.br.erik5594.model.PedidoShopify;

import java.io.Serializable;
import java.util.List;

public class RastreamentoEventosDao implements Serializable{

    public boolean salvarListaPedidoShopify(List<PedidoShopify> pedidosShopify){
        Teste.pedidosShopify = pedidosShopify;
        return true;
    }

    public boolean adicionarPedidoShopify(PedidoShopify pedidoShopify){
        if(Teste.pedidosShopify != null && !Teste.pedidosShopify.contains(pedidoShopify)){
            Teste.pedidosShopify.add(pedidoShopify);
            return true;
        }else{
            return false;
        }
    }

    public List<PedidoShopify> getTodosPedidosShopify(){
        return Teste.pedidosShopify;
    }

    public PedidoShopify getPedidoShopifyByNumeroPedido(int numeroPedido){
        if(Teste.pedidosShopify != null && !Teste.pedidosShopify.isEmpty()){
            for(PedidoShopify pedido : Teste.pedidosShopify){
                if(numeroPedido == pedido.getNumeroPedido()){
                    return pedido;
                }
            }
        }
        return null;
    }
}