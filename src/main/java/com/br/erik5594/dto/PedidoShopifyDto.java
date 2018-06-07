package com.br.erik5594.dto;

import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public @Data class PedidoShopifyDto implements Serializable{

    private int numeroPedido;
    private Date dataPedido;
    private String nomeCliente;
    private String nomeCidadeCliente;
    private String nomeEstadoCliente;
    private String cepCliente;
    private List<ItemDto> itens;

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
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(dataPedido);
    }
}
