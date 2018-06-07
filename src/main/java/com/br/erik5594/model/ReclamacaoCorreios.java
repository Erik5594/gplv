package com.br.erik5594.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public @Data class ReclamacaoCorreios implements Serializable{

    private BigDecimal idReclamacao = BigDecimal.ZERO;
    private Rastreamento rastreamento;
    private Date dataReclamacao;
    private String retornoCorreios;
    private Date dataRetornoCorreios;

    @Override
    public boolean equals(Object o) {
        if(this == null && o == null) return true;
        if(this != null && o == null) return false;
        if(this == null && o != null) return false;
        ReclamacaoCorreios that = (ReclamacaoCorreios) o;
        return Objects.equals(idReclamacao, that.idReclamacao);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), idReclamacao);
    }
}
