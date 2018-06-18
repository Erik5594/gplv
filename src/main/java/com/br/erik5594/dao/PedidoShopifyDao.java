package com.br.erik5594.dao;

import com.br.erik5594.constantes.Teste;
import com.br.erik5594.model.Item;
import com.br.erik5594.model.PedidoAliexpress;
import com.br.erik5594.model.PedidoShopify;

import java.io.Serializable;
import java.util.List;

public class PedidoShopifyDao implements Serializable{

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

    public boolean vincularPedidoAliexpress(int numeroPedidoShopify, String skuProduto, PedidoAliexpress pedidoAliexpress){
        if(Teste.pedidosShopify != null && !Teste.pedidosShopify.isEmpty()) {
            for (PedidoShopify pedidoShopify : Teste.pedidosShopify) {
                if (pedidoShopify.getNumeroPedido() == numeroPedidoShopify
                        && pedidoShopify.getItens() != null
                        && !pedidoShopify.getItens().isEmpty()) {
                    for (Item item : pedidoShopify.getItens()) {
                        if (item.getProduto() != null && skuProduto.equals(item.getProduto().getSkuProduto())) {
                            item.setPedidoAliexpress(pedidoAliexpress);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}