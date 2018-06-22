package com.br.erik5594.util.cast;

import com.br.erik5594.dto.ClienteDto;
import com.br.erik5594.model.Cliente;
import org.apache.commons.lang3.StringUtils;

public class ClienteCast {
    public static ClienteDto castCliente(Cliente cliente){
        if(cliente == null){
            return null;
        }
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setEmail(cliente.getEmail());
        clienteDto.setPrimeiroNome(cliente.getPrimeiroNome());
        clienteDto.setSobreNome(cliente.getSobreNome());
        clienteDto.setCpf(cliente.getCpf());
        clienteDto.setTelefone(cliente.getTelefone());
        clienteDto.setCep(cliente.getCep());
        clienteDto.setLogradouro(cliente.getLogradouro());
        clienteDto.setComplemento(cliente.getComplemento());
        clienteDto.setCidade(cliente.getCidade());
        clienteDto.setEstado(cliente.getEstado());
        return clienteDto;
    }

    public static Cliente castClienteDto(ClienteDto clienteDto){
        if(clienteDto == null){
            return null;
        }
        Cliente cliente = new Cliente();
        cliente.setEmail(clienteDto.getEmail());
        cliente.setPrimeiroNome(clienteDto.getPrimeiroNome());
        cliente.setSobreNome(clienteDto.getSobreNome());
        cliente.setCpf(clienteDto.getCpf());
        cliente.setTelefone(clienteDto.getTelefone());
        cliente.setLogradouro(clienteDto.getLogradouro());
        cliente.setComplemento(clienteDto.getComplemento());
        cliente.setCidade(clienteDto.getCidade());
        cliente.setEstado(clienteDto.getEstado());
        if(StringUtils.isNotBlank(clienteDto.getCep()) && clienteDto.getCep().length() > 10){
            cliente.setCep(clienteDto.getCep().substring(0,10));
        }else{
            cliente.setCep(clienteDto.getCep());
        }
        return cliente;
    }
}
