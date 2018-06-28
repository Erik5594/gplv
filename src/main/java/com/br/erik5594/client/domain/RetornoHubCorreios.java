package com.br.erik5594.client.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

public @Data class RetornoHubCorreios {
    private String erro;
    private boolean status;
    @JsonProperty("return")
    private String retorno;
    @JsonProperty("message")
    private String mensagem;
    @JsonProperty("imagem_status")
    private String imagem_status;
    private List<RetornoHubDados> result;
}
