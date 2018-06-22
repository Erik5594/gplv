package com.br.erik5594.controlador;

import com.br.erik5594.bo.PedidoShopifyBo;
import com.br.erik5594.dto.PedidoShopifyDto;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public @Data class ConsultaPedidoControlador implements Serializable{
    private List<PedidoShopifyDto> pedidosShopifyDto;
    private String numeroPedido;
    @Inject
    private PedidoShopifyBo pedidoShopifyBo;

    public void buscarPedido(){
        if(StringUtils.isNotBlank(numeroPedido)){
            pedidosShopifyDto = new ArrayList<>();
            int nrmPedido = Integer.parseInt(numeroPedido.replaceAll("\\D",""));
            PedidoShopifyDto pedido = pedidoShopifyBo.getPedidoShopifyByNumeroPedido(nrmPedido);
            if(pedido == null){
                pedidosShopifyDto = null;
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Consulta Pedido:", "Nenhum pedido encontrado!");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }else{
                pedidosShopifyDto.add(pedido);
            }
        }
    }

}
