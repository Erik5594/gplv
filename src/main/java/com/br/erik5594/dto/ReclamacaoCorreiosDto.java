package com.br.erik5594.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public @Data class ReclamacaoCorreiosDto implements Serializable{

    private BigDecimal idReclamacao = BigDecimal.ZERO;
    private RastreamentoDto rastreamento;
    private Date dataReclamacao;
    private String retornoCorreios;
    private Date dataRetornoCorreios;

    @Override
    public boolean equals(Object o) {
        if(this == null && o == null) return true;
        if(this != null && o == null) return false;
        if(this == null && o != null) return false;
        ReclamacaoCorreiosDto that = (ReclamacaoCorreiosDto) o;
        return Objects.equals(idReclamacao, that.idReclamacao);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), idReclamacao);
    }
}
