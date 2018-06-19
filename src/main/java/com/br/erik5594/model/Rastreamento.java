package com.br.erik5594.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public @Data class Rastreamento implements Serializable{
    @Id
    @Column(name = "cod_rastreamento")
    private String codigoRastreamento;

    @Column(name = "url_imagem_ultimo_status")
    private String urlImagemUltimoStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_ultima_atualizacao")
    private Date dataUltimaAtualizacao;

    private boolean entregue;

    @Enumerated(EnumType.STRING)
    private StatusPedidoCorreios status;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "rastreamento", fetch = FetchType.LAZY)
    private ReclamacaoCorreios reclamacaoCorreios;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rastreamento")
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
