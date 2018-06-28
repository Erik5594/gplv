package com.br.erik5594.client.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

public @Data class RetornoHubDados {
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "GMT-3")
    private Date data;
    private String local;
    private String retorno;
}
