package com.br.erik5594.util.cast;

import com.br.erik5594.dto.ProdutoDto;
import com.br.erik5594.model.Produto;

public class ProdutoCast {
    public static ProdutoDto castProduto(Produto produto){
        if(produto == null){
            return null;
        }
        ProdutoDto produtoDto = new ProdutoDto();
        produtoDto.setVarianteProduto(produto.getVarianteProduto());
        produtoDto.setSkuProduto(produto.getSkuProduto());
        produtoDto.setNomeProduto(produto.getNomeProduto());
        return produtoDto;
    }

    public static Produto castProdutoDto(ProdutoDto produtoDto){
        if(produtoDto == null){
            return null;
        }
        Produto produto = new Produto();
        produto.setVarianteProduto(produtoDto.getVarianteProduto());
        produto.setSkuProduto(produtoDto.getSkuProduto());
        produto.setNomeProduto(produtoDto.getNomeProduto());
        return produto;
    }
}
