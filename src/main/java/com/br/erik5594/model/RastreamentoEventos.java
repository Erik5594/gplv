package com.br.erik5594.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public @Data class RastreamentoEventos implements Serializable{

    private int idEvento;
    private Rastreamento rastreamento;
    private Date dataEvento;
    private String localEvento;
    private String mensagemEvento;
    private StatusPedidoCorreios status;

    @Override
    public boolean equals(Object o) {
        if(this == null && o == null) return true;
        if(this != null && o == null) return false;
        if(this == null && o != null) return false;
        RastreamentoEventos that = (RastreamentoEventos) o;
        return idEvento == that.idEvento &&
                Objects.equals(rastreamento, that.rastreamento);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), idEvento, rastreamento);
    }
}
