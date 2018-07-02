package com.br.erik5594.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
public @Data class Produto implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "sku_produto")
    private String skuProduto;

    @Column(name = "nome_produto")
    private String nomeProduto;

    @Column(name = "variante_produto")
    private String varianteProduto;

    @Override
    public boolean equals(Object o) {
        if(this == null && o == null) return true;
        if(this != null && o == null) return false;
        if(this == null && o != null) return false;
        Produto produto = (Produto) o;
        return Objects.equals(skuProduto, produto.skuProduto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), skuProduto);
    }
}
