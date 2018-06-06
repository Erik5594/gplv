package com.br.erik5594.controlador;

import lombok.Data;
import org.primefaces.event.FileUploadEvent;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public @Data class oberloCsvControlador implements Serializable{

    public void upload(FileUploadEvent evento){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", evento.getFile().getFileName() + "foi carregado");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void teste(){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Foi carregado");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
