package com.br.erik5594.dto;

import com.br.erik5594.util.Util;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public @Data class PedidoShopifyDto implements Serializable{

    private int numeroPedido;
    private String idPedido;
    private Date dataPedido;
    private ClienteDto cliente;
    private List<ItemDto> itens;
    private float valorTotal;
    private boolean enviado;
    private Date dataCancelamento;

    @Override
    public boolean equals(Object o) {
        if(this == null && o == null) return true;
        if(this != null && o == null) return false;
        if(this == null && o != null) return false;
        PedidoShopifyDto pedido = (PedidoShopifyDto) o;
        return numeroPedido == pedido.numeroPedido;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numeroPedido);
    }

    public Date getDataPedido(){
        return new Date(dataPedido.getTime());
    }

    public void setDataPedido(Date dataPedido){
        this.dataPedido = new Date(dataPedido.getTime());
    }

    public String getDataFormatada(){
        if(dataPedido == null) return null;
        return Util.formatarData(this.dataPedido, "dd/MM/yyyy");
    }

    public String getDataCancelamentoFormatada(){
        if(dataCancelamento == null) return null;
        return Util.formatarData(this.dataCancelamento, "dd/MM/yyyy");
    }
    public boolean isPedidoCancelado(){
        return this.dataCancelamento != null;
    }

    public String getLinkPedidoShopify(){
        return "https://poison-dress.myshopify.com/admin/orders/"+idPedido;
    }
}
