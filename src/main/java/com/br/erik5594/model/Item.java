package com.br.erik5594.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public @Data class Item implements Serializable{

    @EmbeddedId
    private ItemPk id;

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
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
