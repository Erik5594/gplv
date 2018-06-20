package com.br.erik5594.constantes;

import com.br.erik5594.model.*;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

@SessionScoped
public class Teste implements Serializable{
    public static List<PedidoShopify> pedidosShopify;
    public static List<Item> itens;
    public static List<Rastreamento> rastreamentos;
    public static List<Produto> produtos;
    public static List<PedidoAliexpress> pedidosAliexpress;
    public static List<Cliente> clientes;

}
