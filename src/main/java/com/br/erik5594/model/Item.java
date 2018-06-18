package com.br.erik5594.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

public @Data class Item implements Serializable{

    private int idItem;
    private PedidoShopify pedidoShopify;
    private Produto produto;
    private int quantidadeProduto;
    private PedidoAliexpress pedidoAliexpress;

    @Override
    public boolean equals(Object o) {
        if(this == null && o == null) return true;
        if(this != null && o == null) return false;
        if(this == null && o != null) return false;
        Item item = (Item) o;
        return Objects.equals(pedidoShopify, item.pedidoShopify) &&
                Objects.equals(produto, item.produto);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), pedidoShopify, produto);
    }
}
