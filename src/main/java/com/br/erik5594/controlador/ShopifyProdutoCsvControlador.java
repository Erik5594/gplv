package com.br.erik5594.controlador;

import com.br.erik5594.bo.ProdutoBo;
import com.br.erik5594.dto.ProdutoDto;
import com.br.erik5594.util.arquivo.FileUtil;
import lombok.Data;
import org.primefaces.event.FileUploadEvent;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.BufferedReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public @Data class ShopifyProdutoCsvControlador implements Serializable {

    @Inject
    private ProdutoBo produtoBo;
    private List<ProdutoDto> produtos = new ArrayList<>();

    public void upload(FileUploadEvent evento) {
        FacesMessage messagem;
        try {
            salvarProdutoDefault();
            BufferedReader linhasArquivo = FileUtil.obterBufferReader(evento.getFile());
            produtos = produtoBo.getListaDeObjetoDoArquivo(linhasArquivo, ",");
            produtoBo.salvarListaProdutos(produtos);
            messagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso:", "Arquivo carregado com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, messagem);
        } catch (Exception e) {
            messagem = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Ocorreu um erro:", "Erro interno contate o administrador do sistema!");
            FacesContext.getCurrentInstance().addMessage(null, messagem);
            e.printStackTrace();
        }
    }

    private void salvarProdutoDefault() {
        produtos.add(getProdutoDefault());
        produtoBo.salvarListaProdutos(produtos);
        produtos = new ArrayList<>();
    }

    private ProdutoDto getProdutoDefault() {
        ProdutoDto produtoDto = new ProdutoDto();
        produtoDto.setSkuProduto("nao-identificado");
        produtoDto.setNomeProduto("Produto não identificado");
        return produtoDto;
    }
}
