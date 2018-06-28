package com.br.erik5594.client;

import com.br.erik5594.client.domain.RetornoHubCorreios;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class RastreamentoClient {
    public RetornoHubCorreios atualizarEventosRastreamento(String codRastreamento){
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder uri = new StringBuilder();
        uri.append("https://ws.hubdodesenvolvedor.com.br/v2/correios/?")
                .append("servico=")
                .append("rastreamento")
                .append("&codigo_rastreamento=")
                .append(codRastreamento)
                .append("&token=")
                .append("12228140prkJDHPgKb22077536");

        RequestEntity<Void> request = RequestEntity.get(URI.create(uri.toString())).build();
        ResponseEntity<RetornoHubCorreios> response = restTemplate.exchange(request, RetornoHubCorreios.class);
        return response.getBody();
    }
}
