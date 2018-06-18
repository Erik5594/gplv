package com.br.erik5594.bo;

import com.br.erik5594.dao.PedidoShopifyDao;
import com.br.erik5594.dto.*;
import com.br.erik5594.model.*;
import com.br.erik5594.util.Util;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PedidoShopifyBo implements Serializable{

    private PedidoShopifyDao pedidoShopifyDao = new PedidoShopifyDao();
    private ItemBo itemBo = new ItemBo();
    private ClienteBo clienteBo = new ClienteBo();
    private ProdutoBo produtoBo = new ProdutoBo();

    public boolean salvarListaPedidoShopify(List<PedidoShopifyDto> pedidosShopifyDto){
        List<PedidoShopify> pedidosShopify = new ArrayList<>();
        for(PedidoShopifyDto pedidoDto : pedidosShopifyDto){
            PedidoShopify pedido = new PedidoShopify();
            pedido.setNumeroPedido(pedidoDto.getNumeroPedido());
            pedido.setIdPedido(pedidoDto.getIdPedido());
            pedido.setDataPedido(pedidoDto.getDataPedido());
            pedido.setEnviado(pedidoDto.isEnviado());
            pedido.setValorTotal(pedidoDto.getValorTotal());
            pedido.setDataCancelamento(pedidoDto.getDataCancelamento());

            ClienteDto clienteDto = pedidoDto.getCliente();
            if(clienteDto != null) {
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
                pedido.setCliente(cliente);
            }else{
                pedido.setCliente(null);
            }

            List<Item> itens = new ArrayList<>();
            for(ItemDto itemDto : pedidoDto.getItens()){
                Item item = new Item();
                item.setPedidoShopify(pedido);
                item.setQuantidadeProduto(itemDto.getQuantidadeProduto());

                if(itemDto.getProduto() != null){
                    Produto produto = new Produto();
                    produto.setSkuProduto(itemDto.getProduto().getSkuProduto());
                    produto.setVarianteProduto(itemDto.getProduto().getVarianteProduto());
                    produto.setNomeProduto(itemDto.getProduto().getNomeProduto());
                    item.setProduto(produto);
                }else{
                    item.setProduto(null);
                }
                itens.add(item);
            }
            pedido.setItens(itens);


            pedidosShopify.add(pedido);
        }
        return pedidoShopifyDao.salvarListaPedidoShopify(pedidosShopify);
    }

    public List<PedidoShopifyDto> getTodosPedidosShopify(){
        List<PedidoShopifyDto> pedidosShopifyDto = new ArrayList<>();
        List<PedidoShopify> pedidosShopify = pedidoShopifyDao.getTodosPedidosShopify();
        if(pedidosShopify == null){
            return pedidosShopifyDto;
        }
        for(PedidoShopify pedido : pedidosShopify){
            PedidoShopifyDto pedidoDto = new PedidoShopifyDto();
            pedidoDto.setNumeroPedido(pedido.getNumeroPedido());
            pedidoDto.setIdPedido(pedido.getIdPedido());
            pedidoDto.setDataPedido(pedido.getDataPedido());
            pedidoDto.setEnviado(pedido.isEnviado());
            pedidoDto.setValorTotal(pedido.getValorTotal());
            pedidoDto.setDataCancelamento(pedido.getDataCancelamento());

            Cliente cliente = pedido.getCliente();
            if(cliente != null) {
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
                pedidoDto.setCliente(clienteDto);
            }else{
                pedidoDto.setCliente(null);
            }

            List<ItemDto> itensDto = new ArrayList<>();
            List<Item> itens = pedido.getItens();
            if(itens != null && !itens.isEmpty()){
                for(Item item : itens) {
                    ItemDto itemDto = new ItemDto();
                    itemDto.setPedidoShopify(pedidoDto);
                    itemDto.setQuantidadeProduto(item.getQuantidadeProduto());

                    Produto produto = item.getProduto();
                    if (produto != null) {
                        ProdutoDto produtoDto = new ProdutoDto();
                        produtoDto.setSkuProduto(produto.getSkuProduto());
                        produtoDto.setVarianteProduto(produto.getVarianteProduto());
                        produtoDto.setNomeProduto(produto.getNomeProduto());
                        itemDto.setProduto(produtoDto);
                    } else {
                        itemDto.setProduto(null);
                    }
                    PedidoAliexpress pedidoAliexpress = item.getPedidoAliexpress();
                    if(pedidoAliexpress != null){
                        PedidoAliexpressDto pedidoAliexpressDto = new PedidoAliexpressDto();
                        pedidoAliexpressDto.setIdAliexpress(pedidoAliexpress.getIdAliexpress());
                        pedidoAliexpressDto.setDataLimiteDisputa(pedidoAliexpress.getDataLimiteDisputa());
                        pedidoAliexpressDto.setStatusPedidoAliexpress(pedidoAliexpress.getStatusPedidoAliexpress());

                        Rastreamento rastreamento = pedidoAliexpress.getRastreamento();
                        if(rastreamento != null){
                            RastreamentoDto rastreamentoDto = new RastreamentoDto();
                            rastreamentoDto.setDataUltimaAtualizacao(rastreamento.getDataUltimaAtualizacao());
                            rastreamentoDto.setCodigoRastreamento(rastreamento.getCodigoRastreamento());
                            pedidoAliexpressDto.setRastreamento(rastreamentoDto);
                        }else{
                            pedidoAliexpressDto.setRastreamento(null);
                        }
                        itemDto.setPedidoAliexpress(pedidoAliexpressDto);
                    }else{
                        itemDto.setPedidoAliexpress(null);
                    }
                    itensDto.add(itemDto);
                }
            }else{
                itensDto = null;
            }
            pedidoDto.setItens(itensDto);


            pedidosShopifyDto.add(pedidoDto);
        }
        return pedidosShopifyDto;
    }

    public PedidoShopifyDto getPedidoShopifyByNumeroPedido(int numeroPedido){
        List<PedidoShopifyDto> todosPedidos = getTodosPedidosShopify();
        PedidoShopifyDto pedidoRetorno = null;
        if(todosPedidos != null && !todosPedidos.isEmpty()){
            for(PedidoShopifyDto pedido : todosPedidos){
                if(numeroPedido == pedido.getNumeroPedido()){
                    pedidoRetorno = pedido;
                    break;
                }
            }
        }
        return pedidoRetorno;
    }

    public List<PedidoShopifyDto> getListaDeObjetoDoArquivo(BufferedReader linhasArquivo, String separador) throws IOException, ParseException {
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

    private PedidoShopifyDto obterObjetoPedido(String[] vetorObjeto) throws ParseException {
        PedidoShopifyDto pedidoDto = new PedidoShopifyDto();
        ClienteDto clienteDto;
        pedidoDto.setNumeroPedido(Integer.parseInt(vetorObjeto[0].replaceAll("\\D","")));
        pedidoDto.setDataPedido(Util.formatarData(vetorObjeto[15].substring(0,10),"yyyy-MM-dd"));
        pedidoDto.setValorTotal(StringUtils.isBlank(vetorObjeto[11]) ? 0f:Float.parseFloat(vetorObjeto[11]));
        pedidoDto.setEnviado("fulfilled".equals(vetorObjeto[4]));
        pedidoDto.setIdPedido(vetorObjeto[55]);
        if(vetorObjeto.length >= 8){
            clienteDto = clienteBo.buscarCliente(vetorObjeto[1].toLowerCase(), vetorObjeto[33].replaceAll("\\D",""));
        }else{
            clienteDto = clienteBo.buscarCliente(vetorObjeto[1].toLowerCase(), null);
        }
        pedidoDto.setCliente(clienteDto);

        if(vetorObjeto.length >= 47){
            if(StringUtils.isNotBlank(vetorObjeto[46]) && vetorObjeto[46].length() > 10){
                pedidoDto.setDataCancelamento(Util.formatarData(vetorObjeto[46].substring(0,10),"yyyy-MM-dd"));
            }
        }
        return pedidoDto;
    }

    private ItemDto obterObjetoItem(String[] vetorObjeto, PedidoShopifyDto pedido) throws ParseException {
        ItemDto itemDto = new ItemDto();
        itemDto.setQuantidadeProduto(Integer.parseInt(vetorObjeto[16]));
        itemDto.setProduto(produtoBo.buscarProduto(vetorObjeto[20]));
        itemDto.setPedidoShopify(pedido);
        return itemDto;
    }

    public boolean vincularPedidoAliexpress(int numeroPedidoShopify, String skuProduto, PedidoAliexpressDto pedidoAliexpressDto){
        PedidoAliexpress pedidoAliexpress = new PedidoAliexpress();
        pedidoAliexpress.setStatusPedidoAliexpress(pedidoAliexpressDto.getStatusPedidoAliexpress());
        pedidoAliexpress.setIdAliexpress(pedidoAliexpressDto.getIdAliexpress());
        pedidoAliexpress.setDataLimiteDisputa(pedidoAliexpressDto.getDataLimiteDisputa());

        Rastreamento rastreamento = new Rastreamento();
        rastreamento.setUrlImagemUltimoStatus(pedidoAliexpressDto.getRastreamento().getUrlImagemUltimoStatus());
        rastreamento.setReclamacaoCorreios(null);
        rastreamento.setEventos(null);
        rastreamento.setDataUltimaAtualizacao(pedidoAliexpressDto.getRastreamento().getDataUltimaAtualizacao());
        rastreamento.setCodigoRastreamento(pedidoAliexpressDto.getRastreamento().getCodigoRastreamento());
        pedidoAliexpress.setRastreamento(rastreamento);

        return pedidoShopifyDao.vincularPedidoAliexpress(numeroPedidoShopify, skuProduto, pedidoAliexpress);
    }

}
