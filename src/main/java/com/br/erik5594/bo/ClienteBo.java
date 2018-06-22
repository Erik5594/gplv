package com.br.erik5594.bo;

import com.br.erik5594.dao.ClienteDao;
import com.br.erik5594.dto.ClienteDto;
import com.br.erik5594.model.Cliente;
import com.br.erik5594.util.cast.ClienteCast;
import com.br.erik5594.util.jpa.Transactional;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClienteBo implements Serializable{

    @Inject
    private ClienteDao clienteDao;

    @Transactional
    public void salvarListaClientes(List<ClienteDto> clientesDto) throws Exception{
        List<Cliente> clientes = new ArrayList<>();
        for(ClienteDto clienteDto : clientesDto){
            if(clienteDto != null){
                clientes.add(ClienteCast.castClienteDto(clienteDto));
            }
        }
        clienteDao.salvarListaClientes(clientes);
    }

    public List<ClienteDto> getTodosClientes(){
        List<ClienteDto> clientesDto = new ArrayList<>();
        List<Cliente> clientes = clienteDao.getTodosClientes();
        if(clientes == null){
            return clientesDto;
        }
        for(Cliente cliente : clientes){
            if(cliente != null){
                clientesDto.add(ClienteCast.castCliente(cliente));
            }
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

    public ClienteDto buscarCliente(String email, String telefone) throws Exception{
        if(StringUtils.isNotBlank(email) || StringUtils.isNotBlank(telefone)){
            return ClienteCast.castCliente(clienteDao.buscarCliente(email, telefone));
        }
        return null;
    }

    public Long getTotalClientes(){
        return clienteDao.totalCliente();
    }
}
