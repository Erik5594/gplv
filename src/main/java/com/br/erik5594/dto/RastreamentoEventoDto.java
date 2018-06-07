package com.br.erik5594.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public @Data class RastreamentoEventoDto implements Serializable{

    private int idEvento;
    private RastreamentoDto rastreamento;
    private Date dataEvento;
    private String localEvento;
    private String mensagemEvento;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RastreamentoEventoDto that = (RastreamentoEventoDto) o;
        return idEvento == that.idEvento &&
                Objects.equals(rastreamento, that.rastreamento);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), idEvento, rastreamento);
    }
}
