package com.br.erik5594.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
public @Data class Rastreamento implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "cod_rastreamento")
    private String codigoRastreamento;

    @Column(name = "url_imagem_ultimo_status")
    private String urlImagemUltimoStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_ultima_atualizacao")
    private Date dataUltimaAtualizacao;

    @Enumerated(EnumType.STRING)
    private StatusPedidoCorreios status;

    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "rastreamento", fetch = FetchType.LAZY)
    private ReclamacaoCorreios reclamacaoCorreios;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "rastreamento")
    private List<RastreamentoEventos> eventos;

    @Override
    public boolean equals(Object o) {
        if(this == null && o == null) return true;
        if(this != null && o == null) return false;
        if(this == null && o != null) return false;
        Rastreamento that = (Rastreamento) o;
        return Objects.equals(codigoRastreamento, that.codigoRastreamento);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), codigoRastreamento);
    }
}
