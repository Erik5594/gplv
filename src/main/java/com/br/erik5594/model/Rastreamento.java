package com.br.erik5594.model;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

public @Data class Rastreamento {

    private String codigoRastreamento;
    private String urlImagemUltimoStatus;
    private Date dataUltimaAtualizacao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Rastreamento that = (Rastreamento) o;
        return Objects.equals(codigoRastreamento, that.codigoRastreamento);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), codigoRastreamento);
    }
}
