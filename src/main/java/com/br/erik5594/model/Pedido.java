package com.br.erik5594.model;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

public @Data class Pedido {

    private int numeroPedido;
    private Date dataPedido;
    private String nomeCliente;
    private String nomeCidadeCliente;
    private String nomeEstadoCliente;
    private String cepCliente;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pedido pedido = (Pedido) o;
        return numeroPedido == pedido.numeroPedido;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), numeroPedido);
    }
}
