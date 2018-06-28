package com.br.erik5594.controlador;

import com.br.erik5594.bo.RastreamentoBo;
import com.br.erik5594.dto.RastreamentoDto;
import lombok.Data;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public @Data class RastreamentoControlador implements Serializable{

    private List<RastreamentoDto> rastreamentosDto;

    @Inject
    private RastreamentoBo rastreamentoBo;

    public void buscarCodigoRastreamento(){
        rastreamentosDto = rastreamentoBo.getTodosRastreamentos();
    }

}
