package com.br.erik5594.dto;

import com.br.erik5594.util.Util;
import lombok.Data;

import java.util.Objects;

public @Data class ClienteDto {
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
        ClienteDto that = (ClienteDto) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(telefone, that.telefone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), email, telefone);
    }

    public String getNumeroTelefoneFormatado(){
        return Util.formatarNumeroTelefone(this.telefone);
    }

    public String getCpfCnpjFormatado(){
        return Util.formataCpfCnpj(this.cpf);
    }
}
