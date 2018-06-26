package com.br.erik5594.controlador;

import com.br.erik5594.bo.ClienteBo;
import com.br.erik5594.dto.ClienteDto;
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
public @Data class ShopifyClienteCsvControlador implements Serializable {

    private List<ClienteDto> clientes = new ArrayList<>();
    @Inject
    private ClienteBo clienteBo;

    public void upload(FileUploadEvent evento) throws Exception {
        FacesMessage messagem;
        try {
            salvarClienteDefault();

            BufferedReader linhasArquivo = FileUtil.obterBufferReader(evento.getFile());
            clientes = clienteBo.getListaDeObjetoDoArquivo(linhasArquivo, ",");
            clienteBo.salvarListaClientes(clientes);
            messagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso:", "Arquivo carregado com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, messagem);
        } catch (Exception e) {
            messagem = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Ocorreu um erro:", "Erro interno contate o administrador do sistema!");
            FacesContext.getCurrentInstance().addMessage(null, messagem);
            e.printStackTrace();
        }
    }

    private void salvarClienteDefault() throws Exception {
        clientes.add(getClienteDefault());
        clienteBo.salvarListaClientes(clientes);
        clientes = new ArrayList<>();
    }

    private ClienteDto getClienteDefault(){
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setPrimeiroNome("Cliente não indentificado");
        return clienteDto;
    }
}
