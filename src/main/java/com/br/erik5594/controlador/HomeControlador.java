package com.br.erik5594.controlador;

import com.br.erik5594.constantes.Teste;
import com.br.erik5594.dto.PedidoShopifyDto;
import lombok.Data;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public @Data class HomeControlador implements Serializable {
    private List<PedidoShopifyDto> pedidosShopifyDto;

    public HomeControlador(){
        pedidosShopifyDto = Teste.pedidosShopifyDto;
    }
}
