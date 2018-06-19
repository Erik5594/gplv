package com.br.erik5594.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity(name = "eventos_rastreamento")
public @Data class RastreamentoEventos implements Serializable{

    @Id
    @GeneratedValue
    private int idEvento;

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

    @Enumerated(value = EnumType.STRING)
    private StatusPedidoCorreios status;

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
