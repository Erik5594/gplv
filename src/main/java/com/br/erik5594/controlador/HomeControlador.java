package com.br.erik5594.controlador;

import com.br.erik5594.bo.HomeBo;
import lombok.Data;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public @Data class HomeControlador implements Serializable {

    private HomeBo homeBo = new HomeBo();

    private int quantidadePedidosShopify;
    private int quantidadePedidosAliexpress;
    private int quantidadeProdutos;
    private int quantidadeItens;
    private int quantidadeRastreamentos;
    private int quantidadePedidosAliexpressSemDataLimite;
    private int qtdePedidoAliexpressVencendoPrazo;
    private int qtdeClientes;

    public HomeControlador(){
        quantidadePedidosShopify = homeBo.quantidadePedidosShopify();
        quantidadePedidosAliexpress = homeBo.quantidadePedidosAliexpress();
        quantidadeItens = homeBo.quantidadeItens();
        quantidadeProdutos = homeBo.quantidadeProdutos();
        quantidadeRastreamentos = homeBo.quantidadeRastreaemnto();
        quantidadePedidosAliexpressSemDataLimite = homeBo.pedidosSemDataLimite();
        qtdePedidoAliexpressVencendoPrazo = homeBo.pedidosVencendoPrazo();
        qtdeClientes = homeBo.quantidadeClientes();
    }
}
