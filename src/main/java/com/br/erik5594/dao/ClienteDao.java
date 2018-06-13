package com.br.erik5594.dao;

import com.br.erik5594.constantes.Teste;
import com.br.erik5594.model.Cliente;

import java.io.Serializable;
import java.util.List;

public class ClienteDao implements Serializable{

    public boolean salvarListaClientes(List<Cliente> clientes){
        Teste.clientes = clientes;
        return true;
    }

    public List<Cliente> getTodosClientes(){
        return Teste.clientes;
    }
}