package com.br.erik5594.model;

import lombok.Data;

import java.io.Serializable;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PedidoShopify pedido = (PedidoShopify) o;
        return numeroPedido == pedido.numeroPedido;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), numeroPedido);
    }
}
