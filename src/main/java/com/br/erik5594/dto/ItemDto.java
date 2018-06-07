package com.br.erik5594.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

public @Data class ItemDto implements Serializable{

    private int idItem;
    private PedidoShopifyDto pedidoShopify;
    private ProdutoDto produto;
    private int quantidadeProduto;
    private RastreamentoDto rastreamento;
    private PedidoAliexpressDto pedidoAliexpress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ItemDto item = (ItemDto) o;
        return Objects.equals(pedidoShopify, item.pedidoShopify) &&
                Objects.equals(produto, item.produto);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), pedidoShopify, produto);
    }
}
