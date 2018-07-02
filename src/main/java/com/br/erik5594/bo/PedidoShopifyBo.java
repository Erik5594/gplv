package com.br.erik5594.bo;

import java.io.BufferedReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.br.erik5594.dao.PedidoShopifyDao;
import com.br.erik5594.dto.ClienteDto;
import com.br.erik5594.dto.ItemDto;
import com.br.erik5594.dto.PedidoShopifyDto;
import com.br.erik5594.dto.ProdutoDto;
import com.br.erik5594.model.PedidoShopify;
import com.br.erik5594.util.Util;
import com.br.erik5594.util.cast.PedidoShopifyCast;
import com.br.erik5594.util.jpa.Transactional;

public class PedidoShopifyBo implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
    private PedidoShopifyDao pedidoShopifyDao;

    @Inject
    private ClienteBo clienteBo;

    @Inject
    private ProdutoBo produtoBo;

    @Transactional
    public void salvarListaPedidoShopify(List<PedidoShopifyDto> pedidosShopifyDto) throws Exception{
        List<PedidoShopify> pedidosShopify = new ArrayList<>();
        for(PedidoShopifyDto pedidoDto : pedidosShopifyDto){
            pedidosShopify.add(PedidoShopifyCast.castPedidoShopifyDto(pedidoDto));
        }
        pedidoShopifyDao.salvarListaPedidoShopify(pedidosShopify);
    }

    public PedidoShopifyDto getPedidoShopifyByNumeroPedido(int numeroPedido){
        return PedidoShopifyCast.castPedidoShopify(pedidoShopifyDao.buscarPedidoShopify(numeroPedido));
    }

    public List<PedidoShopifyDto> getListaDeObjetoDoArquivo(BufferedReader linhasArquivo, String separador) throws Exception {
        List<PedidoShopifyDto> pedidosDto = new ArrayList<>();
        String linha = linhasArquivo.readLine();
        linha = linhasArquivo.readLine();
        int numeroPedido = 0;
        PedidoShopifyDto pedido = new PedidoShopifyDto();
        while (linha != null) {
            String[] vetorObjeto = linha.split(separador);
            if(vetorObjeto.length >= 56){
                if(numeroPedido != Integer.parseInt(vetorObjeto[0].replaceAll("\\D",""))) {
                    pedido = obterObjetoPedido(vetorObjeto);
                    ClienteDto cliente = obterObjetoCliente(vetorObjeto);
                    pedido.setCliente(cliente);
                    numeroPedido = pedido.getNumeroPedido();
                    pedidosDto.add(pedido);
                }
                if(pedido.getItens() == null){
                    pedido.setItens(new ArrayList<ItemDto>());
                }
                pedido.getItens().add(obterObjetoItem(vetorObjeto, pedido));
                linha = linhasArquivo.readLine();
                continue;
            }
            linha = linhasArquivo.readLine();
        }
        linhasArquivo.close();
        return pedidosDto;
    }

    private PedidoShopifyDto obterObjetoPedido(String[] vetorObjeto) throws Exception {
        PedidoShopifyDto pedidoDto = new PedidoShopifyDto();
        pedidoDto.setNumeroPedido(Integer.parseInt(vetorObjeto[0].replaceAll("\\D","")));
        pedidoDto.setDataPedido(Util.formatarData(vetorObjeto[15].substring(0,10),"yyyy-MM-dd"));
        pedidoDto.setValorTotal(StringUtils.isBlank(vetorObjeto[11]) ? 0f:Float.parseFloat(vetorObjeto[11]));
        pedidoDto.setEnviado("fulfilled".equals(vetorObjeto[4]));
        pedidoDto.setIdPedido(vetorObjeto[55]);
        if(StringUtils.isNotBlank(vetorObjeto[46]) && vetorObjeto[46].length() > 10){
            pedidoDto.setDataCancelamento(Util.formatarData(vetorObjeto[46].substring(0,10),"yyyy-MM-dd"));
        }

        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setEmail(vetorObjeto[1].toLowerCase());
        clienteDto.setTelefone(vetorObjeto[33].replaceAll("\\D",""));
        pedidoDto.setCliente(clienteDto);

        return pedidoDto;
    }

    private ClienteDto obterObjetoCliente(String[] vetorObjeto) throws Exception {
        String email = StringUtils.isNotBlank(vetorObjeto[1]) ? vetorObjeto[1].toLowerCase():null;
        String telefone = StringUtils.isNotBlank(vetorObjeto[33]) ? vetorObjeto[33].replaceAll("\\D",""):null;
        ClienteDto clienteDto = clienteBo.buscarCliente(email, telefone);

        if(clienteDto == null){
            clienteDto = new ClienteDto();
            clienteDto.setTelefone(email);
            clienteDto.setTelefone(telefone);
            clienteDto.setPrimeiroNome(vetorObjeto[24]);
            clienteDto.setEstado(vetorObjeto[31]);
            clienteDto.setCidade(vetorObjeto[29]);
            clienteDto.setComplemento(vetorObjeto[27]);
            clienteDto.setLogradouro(vetorObjeto[26]);
            clienteDto.setCep(vetorObjeto[30]);
            clienteDto.setCpf(vetorObjeto[28]);
            clienteDto.setSobreNome(null);
            clienteBo.salvarCliente(clienteDto);
        }

        return clienteDto;
    }

    private ItemDto obterObjetoItem(String[] vetorObjeto, PedidoShopifyDto pedido) {
        ItemDto itemDto = new ItemDto();
        itemDto.setQuantidadeProduto(Integer.parseInt(vetorObjeto[16]));
        itemDto.setPedidoShopify(pedido);
        ProdutoDto produtoDto = produtoBo.buscarProduto(vetorObjeto[20]);
        if(produtoDto == null){
            produtoDto = new ProdutoDto();
            String[] produtoVetor = vetorObjeto[17].split(" - ");
            produtoDto.setSkuProduto(vetorObjeto[20]);
            if(produtoVetor.length >= 3){
                produtoDto.setNomeProduto(produtoVetor[0]+" "+produtoVetor[1]);
                produtoDto.setVarianteProduto(produtoVetor[2]);
            }else if(produtoVetor.length == 2){
                produtoDto.setNomeProduto(produtoVetor[0]);
                produtoDto.setVarianteProduto(produtoVetor[1]);
            }else if(produtoVetor.length == 1){
                produtoDto.setNomeProduto(produtoVetor[0]);
                produtoDto.setVarianteProduto(null);
            }else{
                produtoDto.setSkuProduto("nao-identificado");
                produtoDto.setNomeProduto(null);
                produtoDto.setVarianteProduto(null);
            }

            produtoBo.salvarProduto(produtoDto);
        }
        itemDto.setProduto(produtoDto);
        return itemDto;
    }

    public Long getTotalPedidosShopify(){
        return pedidoShopifyDao.totalPedidos();
    }

    public PedidoShopifyDto buscarPedidoByIdAliexpress(Long idAliexpress){
        return PedidoShopifyCast.castPedidoShopify(pedidoShopifyDao.buscarPedidoByIdAliexpress(idAliexpress));
    }
}
