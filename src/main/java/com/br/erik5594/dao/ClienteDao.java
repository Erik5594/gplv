package com.br.erik5594.dao;

import com.br.erik5594.model.Cliente;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.io.Serializable;
import java.util.List;

public class ClienteDao implements Serializable{

    @Inject
    private EntityManager manager;

    public void salvarListaClientes(List<Cliente> clientes) throws Exception{
        for(Cliente cliente : clientes){
            if(buscarCliente(cliente.getEmail(), cliente.getTelefone()) == null){
                manager.persist(cliente);
            }
        }
    }

    public List<Cliente> getTodosClientes(){
        return manager.createQuery("from Cliente", Cliente.class).getResultList();
    }

    public Cliente buscarCliente(String email, String telefone) throws Exception{
        String hql = "from Cliente where (:email is null or upper(email) = :email) " +
                "and (:telefone is null or telefone like :telefone)";
        String telefoneConsulta = StringUtils.isNotBlank(telefone) ? "%"+telefone:null;
        String emailConsulta = StringUtils.isNotBlank(email) ? email.toUpperCase():null;
        try{
            List<Cliente> clientes = manager.createQuery(hql, Cliente.class)
                    .setParameter("email", emailConsulta)
                    .setParameter("telefone", telefoneConsulta)
                    .getResultList();
            if(clientes != null && !clientes.isEmpty()){
                return clientes.get(0);
            }
            return null;
        }catch (NoResultException e){
            return null;
        } catch (NonUniqueResultException e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    public Long totalCliente(){
        String hql = "select count(*) from Cliente";
        return (Long)manager.createQuery(hql).getSingleResult();
    }
}