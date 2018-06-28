package com.br.erik5594.dto;

import com.br.erik5594.model.StatusPedidoCorreios;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public @Data class RastreamentoDto implements Serializable{

    private String codigoRastreamento;
    private String urlImagemUltimoStatus;
    private Date dataUltimaAtualizacao;
    private StatusPedidoCorreios status;
    private ReclamacaoCorreiosDto reclamacaoCorreios;
    private List<RastreamentoEventoDto> eventos;

    @Override
    public boolean equals(Object o) {
        if(this == null && o == null) return true;
        if(this != null && o == null) return false;
        if(this == null && o != null) return false;
        RastreamentoDto that = (RastreamentoDto) o;
        return Objects.equals(codigoRastreamento, that.codigoRastreamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), codigoRastreamento);
    }

    public String getDataUltimaAtualizacaoFormatada(){
        if(dataUltimaAtualizacao == null) return null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return format.format(dataUltimaAtualizacao);
    }
}
