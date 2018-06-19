package com.br.erik5594.controlador;

import com.br.erik5594.bo.PedidoAliexpressBo;
import com.br.erik5594.dto.PedidoAliexpressDto;
import com.br.erik5594.util.arquivo.FileUtil;
import lombok.Data;
import org.primefaces.event.FileUploadEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.BufferedReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public @Data class AliexpressPedidoDataLimiteControlador implements Serializable {

    private PedidoAliexpressBo pedidoAliexpressBo = new PedidoAliexpressBo();
    private List<PedidoAliexpressDto> pedidosAliexpress = new ArrayList<>();

    public void upload(FileUploadEvent evento) {
        FacesMessage messagem;
        try {
            BufferedReader linhasArquivo = FileUtil.obterBufferReader(evento.getFile());
            pedidoAliexpressBo.vinculaPedidoAliexpress(linhasArquivo, ",");
            messagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso:", "Arquivo carregado com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, messagem);
        } catch (Exception e) {
            messagem = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Ocorreu um erro:", "Erro interno contate o administrador do sistema!");
            FacesContext.getCurrentInstance().addMessage(null, messagem);
            e.printStackTrace();
        }
    }
}
