package com.br.erik5594.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public @Data class ItemPk implements Serializable{

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_shopify")
    private PedidoShopify pedidoShopify;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sku_produto")
    private Produto produto;

    @Override
    public boolean equals(Object o) {
        if(this == null && o == null) return true;
        if(this != null && o == null) return false;
        if(this == null && o != null) return false;
        ItemPk itemPk = (ItemPk) o;
        return Objects.equals(pedidoShopify, itemPk.pedidoShopify) &&
                Objects.equals(produto, itemPk.produto);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), pedidoShopify, produto);
    }
}
