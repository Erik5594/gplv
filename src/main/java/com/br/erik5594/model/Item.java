package com.br.erik5594.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
public @Data class Item implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
    private ItemPk id;

    @Column(name = "quantidade")
    private int quantidadeProduto;

    @ManyToOne
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
