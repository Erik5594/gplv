package com.br.erik5594.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public @Data class Rastreamento implements Serializable{

    private String codigoRastreamento;
    private String urlImagemUltimoStatus;
    private Date dataUltimaAtualizacao;
    private ReclamacaoCorreios reclamacaoCorreios;
    private List<RastreamentoEventos> eventos;

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
