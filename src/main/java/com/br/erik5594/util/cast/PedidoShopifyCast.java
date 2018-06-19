package com.br.erik5594.util.cast;

import com.br.erik5594.dto.ItemDto;
import com.br.erik5594.dto.PedidoShopifyDto;
import com.br.erik5594.model.Item;
import com.br.erik5594.model.PedidoShopify;

import java.util.ArrayList;
import java.util.List;

public class PedidoShopifyCast {
    public static PedidoShopifyDto castPedidoShopify(PedidoShopify pedidoShopify){
        if(pedidoShopify == null){
            return null;
        }
        PedidoShopifyDto pedidoShopifyDto = new PedidoShopifyDto();
        pedidoShopifyDto.setIdPedido(pedidoShopify.getIdPedido());
        pedidoShopifyDto.setDataCancelamento(pedidoShopify.getDataCancelamento());
        pedidoShopifyDto.setCliente(ClienteCast.castCliente(pedidoShopify.getCliente()));
        pedidoShopifyDto.setEnviado(pedidoShopify.isEnviado());
        pedidoShopifyDto.setValorTotal(pedidoShopify.getValorTotal());
        pedidoShopifyDto.setDataPedido(pedidoShopify.getDataPedido());
        pedidoShopifyDto.setNumeroPedido(pedidoShopify.getNumeroPedido());
        List<Item> itens = pedidoShopify.getItens();
        List<ItemDto> itensDto = new ArrayList<>();
        if(itens != null && !itens.isEmpty()){
            for(Item item : itens){
                itensDto.add(ItemCast.castItem(item));
            }
        }else{
            itensDto = null;
        }
        pedidoShopifyDto.setItens(itensDto);
        return pedidoShopifyDto;
    }

    public static PedidoShopify castPedidoShopifyDto(PedidoShopifyDto pedidoShopifyDto){
        if(pedidoShopifyDto == null){
            return null;
        }
        PedidoShopify pedidoShopify = new PedidoShopify();
        pedidoShopify.setIdPedido(pedidoShopifyDto.getIdPedido());
        pedidoShopify.setDataCancelamento(pedidoShopifyDto.getDataCancelamento());
        pedidoShopify.setCliente(ClienteCast.castClienteDto(pedidoShopifyDto.getCliente()));
        pedidoShopify.setEnviado(pedidoShopifyDto.isEnviado());
        pedidoShopify.setValorTotal(pedidoShopifyDto.getValorTotal());
        pedidoShopify.setDataPedido(pedidoShopifyDto.getDataPedido());
        pedidoShopify.setNumeroPedido(pedidoShopifyDto.getNumeroPedido());
        List<ItemDto> itensDto = pedidoShopifyDto.getItens();
        List<Item> itens = new ArrayList<>();
        if(itensDto != null && !itensDto.isEmpty()){
            for(ItemDto itemDto : itensDto){
                itens.add(ItemCast.castItemDto(itemDto));
            }
        }else{
            itens = null;
        }
        pedidoShopify.setItens(itens);
        return pedidoShopify;
    }
}
