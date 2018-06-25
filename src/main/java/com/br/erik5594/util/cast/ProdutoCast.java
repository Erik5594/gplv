package com.br.erik5594.util.cast;

import com.br.erik5594.dto.ProdutoDto;
import com.br.erik5594.model.Produto;

import java.util.Objects;

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

    public static boolean adicionarAlteracoes(Produto produtoBanco, Produto produto){
        boolean retorno = false;
        if(produto != null && produtoBanco != null){
            if(!Objects.equals(produto.getNomeProduto(), produtoBanco.getNomeProduto())){
                produtoBanco.setNomeProduto(produto.getNomeProduto());
                retorno = true;
            }

            if(!Objects.equals(produto.getVarianteProduto(), produtoBanco.getVarianteProduto())){
                produtoBanco.setVarianteProduto(produto.getVarianteProduto());
                retorno = true;
            }
        }
        return retorno;
    }

}
