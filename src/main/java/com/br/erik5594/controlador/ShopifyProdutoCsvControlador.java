package com.br.erik5594.controlador;

import com.br.erik5594.bo.ProdutoBo;
import com.br.erik5594.dto.ProdutoDto;
import com.br.erik5594.util.arquivo.FileUtil;
import lombok.Data;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public @Data class ShopifyProdutoCsvControlador implements Serializable {

    private List<ProdutoDto> produtos = new ArrayList<>();
    private ProdutoBo produtoBo = new ProdutoBo();

    public void upload(FileUploadEvent evento) {
        FacesMessage messagem;
        try {
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
}
