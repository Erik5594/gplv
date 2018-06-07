package com.br.erik5594.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public @Data class RastreamentoDto implements Serializable{

    private String codigoRastreamento;
    private String urlImagemUltimoStatus;
    private Date dataUltimaAtualizacao;
    private ReclamacaoCorreiosDto reclamacaoCorreios;
    private List<RastreamentoEventoDto> eventos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RastreamentoDto that = (RastreamentoDto) o;
        return Objects.equals(codigoRastreamento, that.codigoRastreamento);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), codigoRastreamento);
    }
}
