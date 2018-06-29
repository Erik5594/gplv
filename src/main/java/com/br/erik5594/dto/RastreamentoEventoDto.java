package com.br.erik5594.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public @Data class RastreamentoEventoDto implements Serializable{

    private Long idEvento;
    private RastreamentoDto rastreamento;
    private Date dataEvento;
    private String localEvento;
    private String mensagemEvento;

    @Override
    public boolean equals(Object o) {
        if(this == null && o == null) return true;
        if(this != null && o == null) return false;
        if(this == null && o != null) return false;
        RastreamentoEventoDto that = (RastreamentoEventoDto) o;
        return idEvento == that.idEvento &&
                Objects.equals(rastreamento, that.rastreamento);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), idEvento, rastreamento);
    }
}
