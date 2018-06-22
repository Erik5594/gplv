package com.br.erik5594.controlador;

import com.br.erik5594.bo.HomeBo;
import lombok.Data;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public @Data class HomeControlador implements Serializable {

    @Inject
    private HomeBo homeBo;

    private Long quantidadePedidosShopify;
    private Long quantidadePedidosAliexpress;
    private Long quantidadeProdutos;
    private int quantidadePedidosAliexpressSemDataLimite;
    private int qtdePedidoAliexpressVencendoPrazo;
    private Long qtdeClientes;

    public void carregarDados(){
        quantidadePedidosShopify = homeBo.quantidadePedidosShopify();
        quantidadePedidosAliexpress = homeBo.quantidadePedidosAliexpress();
        quantidadeProdutos = homeBo.quantidadeProdutos();
        quantidadePedidosAliexpressSemDataLimite = homeBo.pedidosSemDataLimite();
        qtdePedidoAliexpressVencendoPrazo = homeBo.pedidosVencendoPrazo();
        qtdeClientes = homeBo.quantidadeClientes();
    }
}
