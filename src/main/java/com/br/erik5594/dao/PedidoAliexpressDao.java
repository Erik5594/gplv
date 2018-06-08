package com.br.erik5594.dao;

import com.br.erik5594.constantes.Teste;
import com.br.erik5594.dto.PedidoAliexpressDto;
import com.br.erik5594.model.PedidoAliexpress;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class PedidoAliexpressDao implements Serializable{

    public boolean salvarListaPedidoAliexpress(List<PedidoAliexpress> pedidosAliexpress){
        Teste.pedidosAliexpress = pedidosAliexpress;
        return true;
    }

    public List<PedidoAliexpress> getTodosPedidosAliexpress(){
        return Teste.pedidosAliexpress;
    }
}