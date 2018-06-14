package com.br.erik5594.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

public @Data class ItemDto implements Serializable{

    private int idItem;
    private PedidoShopifyDto pedidoShopify;
    private ProdutoDto produto;
    private int quantidadeProduto;
    private PedidoAliexpressDto pedidoAliexpress;

    @Override
    public boolean equals(Object o) {
        if(this == null && o == null) return true;
        if(this != null && o == null) return false;
        if(this == null && o != null) return false;
        ItemDto item = (ItemDto) o;
        return Objects.equals(pedidoShopify, item.pedidoShopify) &&
                Objects.equals(produto, item.produto);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), pedidoShopify, produto);
    }
}
