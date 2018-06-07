package com.br.erik5594.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

public @Data class Produto implements Serializable{

    private String skuProduto;
    private String nomeProduto;
    private String varianteProduto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Produto produto = (Produto) o;
        return Objects.equals(skuProduto, produto.skuProduto);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), skuProduto);
    }
}
