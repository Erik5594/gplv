package com.br.erik5594.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "eventos_rastreamento")
public @Data class RastreamentoEventos implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    @Column(name = "id_evento")
    private Long idEvento;

    @ManyToOne
    @JoinColumn(name = "cod_rastreamento")
    private Rastreamento rastreamento;

    @Column(name = "data_evento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEvento;

    @Column(name = "local_evento")
    private String localEvento;

    @Column(name = "mensagem_evento")
    private String mensagemEvento;

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
