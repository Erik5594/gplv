package com.br.erik5594.controlador;

import com.br.erik5594.bo.PedidoAliexpressBo;
import com.br.erik5594.dto.PedidoAliexpressDto;
import lombok.Data;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public @Data class AliexpressPedidoDataLimiteControlador implements Serializable {

    @Inject
    private PedidoAliexpressBo pedidoAliexpressBo;
    private List<PedidoAliexpressDto> pedidosAliexpress;

    public void carregarPedidosVencendoPrazo(){
        pedidosAliexpress = pedidoAliexpressBo.pedidosVencendoPrazo();
    }
}
