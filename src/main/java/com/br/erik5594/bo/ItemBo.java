package com.br.erik5594.bo;

import com.br.erik5594.dao.ItemDao;
import com.br.erik5594.dto.*;
import com.br.erik5594.model.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ItemBo implements Serializable{

    private ItemDao itemDao = new ItemDao();

    public boolean salvarListaItens(List<ItemDto> itensDto){
        List<Item> itens = new ArrayList<>();
        for(ItemDto itemDto : itensDto){
            Item item = new Item();

            PedidoAliexpress pedidoAliexpress = new PedidoAliexpress();
            pedidoAliexpress.setDataLimiteDisputa(itemDto.getPedidoAliexpress().getDataLimiteDisputa());
            pedidoAliexpress.setIdAliexpress(itemDto.getPedidoAliexpress().getIdAliexpress());
            pedidoAliexpress.setStatusPedidoAliexpress(itemDto.getPedidoAliexpress().getStatusPedidoAliexpress());
            item.setPedidoAliexpress(pedidoAliexpress);

            PedidoShopify pedidoShopify = new PedidoShopify();
            pedidoShopify.setCepCliente(itemDto.getPedidoShopify().getCepCliente());
            pedidoShopify.setNomeEstadoCliente(itemDto.getPedidoShopify().getNomeEstadoCliente());
            pedidoShopify.setNomeCidadeCliente(itemDto.getPedidoShopify().getNomeCidadeCliente());
            pedidoShopify.setNomeCliente(itemDto.getPedidoShopify().getNomeCliente());
            pedidoShopify.setDataPedido(itemDto.getPedidoShopify().getDataPedido());
            pedidoShopify.setNumeroPedido(itemDto.getPedidoShopify().getNumeroPedido());
            pedidoShopify.setItens(null);
            item.setPedidoShopify(pedidoShopify);

            Rastreamento rastreamento = new Rastreamento();
            rastreamento.setCodigoRastreamento(itemDto.getRastreamento().getCodigoRastreamento());
            rastreamento.setDataUltimaAtualizacao(itemDto.getRastreamento().getDataUltimaAtualizacao());
            rastreamento.setEventos(null);
            rastreamento.setReclamacaoCorreios(null);
            rastreamento.setUrlImagemUltimoStatus(itemDto.getRastreamento().getUrlImagemUltimoStatus());
            item.setRastreamento(rastreamento);

            Produto produto = new Produto();
            produto.setNomeProduto(itemDto.getProduto().getNomeProduto());
            produto.setSkuProduto(itemDto.getProduto().getSkuProduto());
            produto.setVarianteProduto(itemDto.getProduto().getVarianteProduto());
            item.setProduto(produto);

            item.setQuantidadeProduto(itemDto.getQuantidadeProduto());

            itens.add(item);
        }
        return itemDao.salvarListaItens(itens);
    }

    public List<ItemDto> getTodosItens(){
        List<ItemDto> itemDtos = new ArrayList<>();
        List<Item> itens = itemDao.getTodosItens();
        if(itens == null){
            return itemDtos;
        }
        for(Item item : itens){
            ItemDto itemDto = new ItemDto();

            PedidoAliexpressDto pedidoAliexpress = new PedidoAliexpressDto();
            pedidoAliexpress.setDataLimiteDisputa(item.getPedidoAliexpress().getDataLimiteDisputa());
            pedidoAliexpress.setIdAliexpress(item.getPedidoAliexpress().getIdAliexpress());
            pedidoAliexpress.setStatusPedidoAliexpress(item.getPedidoAliexpress().getStatusPedidoAliexpress());
            itemDto.setPedidoAliexpress(pedidoAliexpress);

            PedidoShopifyDto pedidoShopify = new PedidoShopifyDto();
            pedidoShopify.setCepCliente(item.getPedidoShopify().getCepCliente());
            pedidoShopify.setNomeEstadoCliente(item.getPedidoShopify().getNomeEstadoCliente());
            pedidoShopify.setNomeCidadeCliente(item.getPedidoShopify().getNomeCidadeCliente());
            pedidoShopify.setNomeCliente(item.getPedidoShopify().getNomeCliente());
            pedidoShopify.setDataPedido(item.getPedidoShopify().getDataPedido());
            pedidoShopify.setNumeroPedido(item.getPedidoShopify().getNumeroPedido());
            pedidoShopify.setItens(null);
            itemDto.setPedidoShopify(pedidoShopify);

            RastreamentoDto rastreamento = new RastreamentoDto();
            rastreamento.setCodigoRastreamento(item.getRastreamento().getCodigoRastreamento());
            rastreamento.setDataUltimaAtualizacao(item.getRastreamento().getDataUltimaAtualizacao());
            rastreamento.setEventos(null);
            rastreamento.setReclamacaoCorreios(null);
            rastreamento.setUrlImagemUltimoStatus(item.getRastreamento().getUrlImagemUltimoStatus());
            itemDto.setRastreamento(rastreamento);

            ProdutoDto produto = new ProdutoDto();
            produto.setNomeProduto(item.getProduto().getNomeProduto());
            produto.setSkuProduto(item.getProduto().getSkuProduto());
            produto.setVarianteProduto(item.getProduto().getVarianteProduto());
            itemDto.setProduto(produto);

            itemDto.setQuantidadeProduto(item.getQuantidadeProduto());

            itemDtos.add(itemDto);
        }
        return itemDtos;
    }

    public List<ItemDto> getListaItensByPedido(PedidoShopifyDto pedidoShopifyDto){
        List<ItemDto> todosItens = getTodosItens();
        List<ItemDto> retorno = new ArrayList<>();
        if(todosItens != null && !todosItens.isEmpty() ){
            for(ItemDto itemDto : todosItens){
                if(itemDto.getPedidoShopify() != null
                        && pedidoShopifyDto != null
                        && pedidoShopifyDto.equals(itemDto.getPedidoShopify())){
                    retorno.add(itemDto);
                }
            }
        }
        return retorno;
    }

}
