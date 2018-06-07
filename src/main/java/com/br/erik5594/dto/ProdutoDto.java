package com.br.erik5594.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

public @Data class ProdutoDto implements Serializable{

    private String skuProduto;
    private String nomeProduto;
    private String varianteProduto;

    @Override
    public boolean equals(Object o) {
        if(this == null && o == null) return true;
        if(this != null && o == null) return false;
        if(this == null && o != null) return false;
        ProdutoDto produto = (ProdutoDto) o;
        return Objects.equals(skuProduto, produto.skuProduto);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), skuProduto);
    }
}
