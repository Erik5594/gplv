package com.br.erik5594.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

public @Data class Cliente implements Serializable{
    private String email;
    private String primeiroNome;
    private String sobreNome;
    private String cpf;
    private String logradouro;
    private String complemento;
    private String cidade;
    private String estado;
    private String telefone;
    private String cep;

    @Override
    public boolean equals(Object o) {
        if(this == null && o == null) return true;
        if(this != null && o == null) return false;
        if(this == null && o != null) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(email, cliente.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), email);
    }
}
