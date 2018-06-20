package com.br.erik5594.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public @Data class Item implements Serializable{

    @Id
    @Column(name = "id")
    @GeneratedValue
    @Setter(value = AccessLevel.PRIVATE)
    private int idItem;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_shopify")
    private PedidoShopify pedidoShopify;

    @OneToOne
    @JoinColumn(name = "sku_produto")
    private Produto produto;

    @Column(name = "quantidade")
    private int quantidadeProduto;

    @OneToOne
    @JoinColumn(name = "pedido_aliexpress")
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
