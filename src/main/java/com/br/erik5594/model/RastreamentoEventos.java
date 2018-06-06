package com.br.erik5594.model;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

public @Data class RastreamentoEventos {

    private int idEvento;
    private Rastreamento rastreamento;
    private Date dataEvento;
    private String localEvento;
    private String mensagemEvento;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RastreamentoEventos that = (RastreamentoEventos) o;
        return idEvento == that.idEvento &&
                Objects.equals(rastreamento, that.rastreamento);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), idEvento, rastreamento);
    }
}
