package com.br.erik5594.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
public @Data class Cliente implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue
    @Setter(value = AccessLevel.PRIVATE)
    private Long id;
    @Column(name = "email", length = 60)
    private String email;
    @Column(name = "primeiro_nome")
    private String primeiroNome;
    @Column(name = "sobre_nome", length = 90)
    private String sobreNome;
    @Column(name = "cpf_cnpj", length = 14)
    private String cpf;
    @Column(name = "logradouro")
    private String logradouro;
    @Column(name = "complemento")
    private String complemento;
    @Column(name = "cidade")
    private String cidade;
    @Column(name = "estado")
    private String estado;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "cep", length = 10)
    private String cep;

    @Override
    public boolean equals(Object o) {
        if(this == null && o == null) return true;
        if(this != null && o == null) return false;
        if(this == null && o != null) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(email, cliente.email) &&
                Objects.equals(telefone, cliente.telefone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), email, telefone);
    }
}
