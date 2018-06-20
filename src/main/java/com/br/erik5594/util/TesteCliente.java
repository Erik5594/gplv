package com.br.erik5594.util;

import com.br.erik5594.model.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TesteCliente {
    public static void main(String[] args){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidoPU");
        EntityManager entityManager = factory.createEntityManager();

        EntityTransaction trx = entityManager.getTransaction();
        trx.begin();

        Cliente cliente = new Cliente();
        cliente.setTelefone("62985023781");
        cliente.setEmail("teste@teste.com");
        cliente.setCep("74840250");
        cliente.setEstado("GO");
        cliente.setCidade("Goiania");
        cliente.setComplemento("Qd39 Lt08");
        cliente.setLogradouro("Rua Pocema - Parque Amazonia");
        cliente.setCpf("03554424188");
        cliente.setSobreNome("Queiroz");
        cliente.setPrimeiroNome("Erik");

        entityManager.persist(cliente);
        trx.commit();
        System.out.println("TesteCliente realizado");
    }
}
