package com.br.erik5594.controlador;

import com.br.erik5594.bo.PedidoAliexpressBo;
import com.br.erik5594.dto.PedidoAliexpressDto;
import lombok.Data;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public @Data class AliexpressPedidoDataLimiteControlador implements Serializable {

    private PedidoAliexpressBo pedidoAliexpressBo = new PedidoAliexpressBo();
    private List<PedidoAliexpressDto> pedidosAliexpress;

    public AliexpressPedidoDataLimiteControlador(){
        pedidosAliexpress = pedidoAliexpressBo.getTodosPedidosAliexpress();
    }

}
