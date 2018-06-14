package com.br.erik5594.bo;

import com.br.erik5594.constantes.Teste;
import com.br.erik5594.dao.ClienteDao;
import com.br.erik5594.dto.ClienteDto;
import com.br.erik5594.model.Cliente;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClienteBo implements Serializable{

    private ClienteDao clienteDao = new ClienteDao();

    public boolean salvarListaClientes(List<ClienteDto> clientesDto){
        List<Cliente> clientes = new ArrayList<>();
        for(ClienteDto clienteDto : clientesDto){
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
            cliente.setCep(clienteDto.getCep());
            clientes.add(cliente);
        }
        return clienteDao.salvarListaClientes(clientes);
    }

    public List<ClienteDto> getTodosClientes(){
        List<ClienteDto> clientesDto = new ArrayList<>();
        List<Cliente> clientes = clienteDao.getTodosClientes();
        if(clientes == null){
            return clientesDto;
        }
        for(Cliente cliente : clientes){
            ClienteDto clienteDto = new ClienteDto();
            clienteDto.setEmail(cliente.getEmail());
            clienteDto.setPrimeiroNome(cliente.getPrimeiroNome());
            clienteDto.setSobreNome(cliente.getSobreNome());
            clienteDto.setCpf(cliente.getCpf());
            clienteDto.setTelefone(cliente.getTelefone());
            clienteDto.setLogradouro(cliente.getLogradouro());
            clienteDto.setComplemento(cliente.getComplemento());
            clienteDto.setCidade(cliente.getCidade());
            clienteDto.setEstado(cliente.getEstado());
            clienteDto.setCep(cliente.getCep());
            clientesDto.add(clienteDto);
        }
        return clientesDto;
    }

    public List<ClienteDto> getListaDeObjetoDoArquivo(BufferedReader linhasArquivo, String separador) throws IOException {
        List<ClienteDto> clientesDto = new ArrayList<>();
        String linha = linhasArquivo.readLine();
        linha = linhasArquivo.readLine();
        while (linha != null) {
            String[] vetorObjeto = linha.split(separador);
            if(vetorObjeto.length >= 3){
                ClienteDto clienteDto = obterObjetoCliente(vetorObjeto);
                if (clienteDto != null
                        && (StringUtils.isNotBlank(clienteDto.getEmail()) || StringUtils.isNotBlank(clienteDto.getTelefone()))
                        && !clientesDto.contains(clienteDto)) {
                    clientesDto.add(clienteDto);
                }
                linha = linhasArquivo.readLine();
                continue;
            }
            linha = linhasArquivo.readLine();
        }
        linhasArquivo.close();
        return clientesDto;
    }

    private ClienteDto obterObjetoCliente(String[] vetorObjeto) {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setEmail(vetorObjeto[2].toLowerCase());
        clienteDto.setPrimeiroNome(vetorObjeto[0]);
        clienteDto.setSobreNome(vetorObjeto[1]);
        if(vetorObjeto.length >= 4){
            clienteDto.setCpf(vetorObjeto[3].replaceAll("\\D",""));
        }
        if(vetorObjeto.length >= 13){
            clienteDto.setTelefone(vetorObjeto[12].replaceAll("\\D",""));
        }
        if(vetorObjeto.length >= 5){
            clienteDto.setLogradouro(vetorObjeto[4]);
        }
        if(vetorObjeto.length >= 6){
            clienteDto.setComplemento(vetorObjeto[5]);
        }
        if(vetorObjeto.length >= 7){
            clienteDto.setCidade(vetorObjeto[6]);
        }
        if(vetorObjeto.length >= 9){
            clienteDto.setEstado(vetorObjeto[8]);
        }
        if(vetorObjeto.length >= 12){
            clienteDto.setCep(vetorObjeto[11].replaceAll("\\D",""));
        }
        return clienteDto;
    }

    public ClienteDto buscarCliente(String email, String telefone){
        Cliente cliente = new Cliente();
        cliente.setEmail(email);
        cliente.setTelefone(telefone);

        ClienteDto clienteDto = new ClienteDto();
        if(StringUtils.isNotBlank(email)){
            int indexCliente = Teste.clientes.indexOf(cliente);
            if(indexCliente < 0){
                return null;
            }
            cliente = Teste.clientes.get(indexCliente);
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
        }
        return clienteDto;
    }

}
