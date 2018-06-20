package com.br.erik5594.controlador;

import com.br.erik5594.dto.PedidoAliexpressDto;
import lombok.Data;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public @Data class DetalhaPedidoAliexpressControlador implements Serializable{
    private PedidoAliexpressDto pedidoAliexpressDto;
}
