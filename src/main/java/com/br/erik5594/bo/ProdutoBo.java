package com.br.erik5594.bo;

import com.br.erik5594.dao.ProdutoDao;
import com.br.erik5594.dto.ProdutoDto;
import com.br.erik5594.model.Produto;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
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

    public List<ProdutoDto> getListaDeObjetoDoArquivo(BufferedReader linhasArquivo, String separador) throws IOException {
        List<ProdutoDto> produtos = new ArrayList<>();
        String linha = linhasArquivo.readLine();
        linha = linhasArquivo.readLine();
        String nomeProduto = null;
        while (linha != null) {
            String[] vetorObjeto = linha.split(separador);
            if(vetorObjeto.length < 13 || (vetorObjeto.length >= 13 && StringUtils.isBlank(vetorObjeto[12]))){
                linha = linhasArquivo.readLine();
                continue;
            }
            if(StringUtils.isNotBlank(vetorObjeto[1])){
                nomeProduto = vetorObjeto[1];
            }
            ProdutoDto produtoDto = (ProdutoDto) obterObjetoProduto(vetorObjeto, nomeProduto);
            if (produtoDto != null && !produtos.contains(produtoDto)) {
                produtos.add(produtoDto);
            }
            linha = linhasArquivo.readLine();
        }
        linhasArquivo.close();
        return produtos;
    }

    private Object obterObjetoProduto(String[] vetorObjeto, String nomeProduto) {
        ProdutoDto produtoDto = new ProdutoDto();
        produtoDto.setSkuProduto(vetorObjeto[12]);
        produtoDto.setNomeProduto(nomeProduto);
        String variante = null;
        if(StringUtils.isNotBlank(vetorObjeto[7])){
            variante = vetorObjeto[7];
        }
        if(StringUtils.isNotBlank(vetorObjeto[9])){
            if(StringUtils.isNotBlank(variante)){
                variante = variante+"/"+vetorObjeto[9];
            }else{
                variante = vetorObjeto[9];
            }
        }
        if(StringUtils.isNotBlank(vetorObjeto[11])){
            if(StringUtils.isNotBlank(variante)){
                variante=variante+"/"+vetorObjeto[11];
            }else{
                variante = vetorObjeto[11];
            }
        }
        produtoDto.setVarianteProduto(variante);

        return produtoDto;
    }

    public ProdutoDto buscarProduto(String skuProduto){
        Produto produto = produtoDao.buscarProduto(skuProduto);
        if(produto == null){
            return null;
        }
        ProdutoDto produtoDto = new ProdutoDto();
        produtoDto.setVarianteProduto(produto.getVarianteProduto());
        produtoDto.setNomeProduto(produto.getNomeProduto());
        produtoDto.setSkuProduto(produto.getSkuProduto());
        return produtoDto;
    }

}
