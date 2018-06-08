package com.br.erik5594.bo;

import com.br.erik5594.dao.ProdutoDao;
import com.br.erik5594.dto.ProdutoDto;
import com.br.erik5594.model.Produto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProdutoBo implements Serializable{

    private ProdutoDao produtoDao = new ProdutoDao();

    public boolean salvarListaProdutos(List<ProdutoDto> produtosDto){
        List<Produto> produtos = new ArrayList<>();
        for(ProdutoDto produtoDto : produtosDto){
            Produto produto = new Produto();
            produto.setVarianteProduto(produtoDto.getVarianteProduto());
            produto.setSkuProduto(produtoDto.getSkuProduto());
            produto.setNomeProduto(produtoDto.getNomeProduto());
            produtos.add(produto);
        }
        return produtoDao.salvarListaProdutos(produtos);
    }

    public List<ProdutoDto> getTodosProdutos(){
        List<ProdutoDto> produtosDto = new ArrayList<>();
        List<Produto> produtos = produtoDao.getTodosProdutos();
        if(produtos == null){
            return produtosDto;
        }
        for(Produto produto : produtos){
            ProdutoDto produtoDto = new ProdutoDto();
            produtoDto.setVarianteProduto(produto.getVarianteProduto());
            produtoDto.setSkuProduto(produto.getSkuProduto());
            produtoDto.setNomeProduto(produto.getNomeProduto());
            produtosDto.add(produtoDto);
        }
        return produtosDto;
    }

}
