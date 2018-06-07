package com.br.erik5594.model;

import lombok.Data;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public @Data class PedidoShopify implements Serializable{

    private int numeroPedido;
    private Date dataPedido;
    private String nomeCliente;
    private String nomeCidadeCliente;
    private String nomeEstadoCliente;
    private String cepCliente;
    private List<Item> itens;

    @Override
    public boolean equals(Object o) {
        if(this == null && o == null) return true;
        if(this != null && o == null) return false;
        if(this == null && o != null) return false;
        PedidoShopify pedido = (PedidoShopify) o;
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
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(dataPedido);
    }
}
