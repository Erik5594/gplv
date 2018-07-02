package com.br.erik5594.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity(name = "reclamacao_correios")
public @Data class ReclamacaoCorreios implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id_reclamacao")
    private BigDecimal idReclamacao = BigDecimal.ZERO;

    @OneToOne
    @JoinColumn(name = "cod_rastreamento")
    private Rastreamento rastreamento;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_reclamacao")
    private Date dataReclamacao;

    @Column(name = "retorno_correios")
    private String retornoCorreios;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_retorno_correios")
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
