package com.br.erik5594.controlador;

import com.br.erik5594.bo.PedidoAliexpressBo;
import com.br.erik5594.bo.PedidoShopifyBo;
import com.br.erik5594.dto.PedidoAliexpressDto;
import com.br.erik5594.dto.PedidoShopifyDto;
import com.br.erik5594.model.StatusPedidoAliexpress;
import lombok.Data;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public @Data class AliexpressPedidoDataLimiteControlador implements Serializable {

    @Inject
    private PedidoAliexpressBo pedidoAliexpressBo;
    private List<PedidoAliexpressDto> pedidosAliexpress;
    private List<PedidoAliexpressDto> pedidosAliexpressSelecionados;
    private StatusPedidoAliexpress statusPedidoAliexpressSelecionado;
    @Inject
    private PedidoShopifyBo pedidoShopifyBo;

    public void carregarPedidosVencendoPrazo(){
        if(pedidosAliexpress == null || pedidosAliexpress.isEmpty()){
            consultarPedidosVencendoPrazo();
        }
    }

    public void atualizarStatus(){
        if(pedidosAliexpressSelecionados != null && !pedidosAliexpressSelecionados.isEmpty()){
            setarNovoStatus();
            atualizarPedidoBanco();
            consultarPedidosVencendoPrazo();
            statusPedidoAliexpressSelecionado = null;
            pedidosAliexpressSelecionados = new ArrayList<>();
        }
    }

    public StatusPedidoAliexpress[] getStatusPedidoAliexpress(){
        return StatusPedidoAliexpress.values();
    }

    private void setarNovoStatus(){
        if(statusPedidoAliexpressSelecionado != null){
            for(PedidoAliexpressDto pedidoAliexpressDto : pedidosAliexpressSelecionados){
                pedidoAliexpressDto.setStatusPedidoAliexpress(statusPedidoAliexpressSelecionado);
            }
        }
    }

    private void atualizarPedidoBanco(){
        pedidoAliexpressBo.atualizarStatusPedidosAliexpress(pedidosAliexpressSelecionados);
    }

    private void consultarPedidosVencendoPrazo(){
        pedidosAliexpress = pedidoAliexpressBo.pedidosVencendoPrazo();
    }

    public int buscarNumeroPedidoShopify(Long idPedidoAliexpress){
        PedidoShopifyDto pedidoShopifyDto = pedidoShopifyBo.buscarPedidoByIdAliexpress(idPedidoAliexpress);
        if(pedidoShopifyDto != null){
            return pedidoShopifyDto.getNumeroPedido();
        }
        return 0;
    }
}
