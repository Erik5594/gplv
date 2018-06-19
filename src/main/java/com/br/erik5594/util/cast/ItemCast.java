package com.br.erik5594.util.cast;

import com.br.erik5594.dto.ItemDto;
import com.br.erik5594.model.Item;

public class ItemCast {
    public static Item castItemDto(ItemDto itemDto){
        if(itemDto == null){
            return null;
        }
        Item item = new Item();
        item.setProduto(ProdutoCast.castProdutoDto(itemDto.getProduto()));
        item.setQuantidadeProduto(itemDto.getQuantidadeProduto());
        item.setPedidoShopify(PedidoShopifyCast.castPedidoShopifyDto(itemDto.getPedidoShopify()));
        item.setPedidoAliexpress(PedidoAliexpressCast.castPedidoAliexpressDto(itemDto.getPedidoAliexpress()));
        item.setIdItem(itemDto.getIdItem());
        return item;
    }

    public static ItemDto castItem(Item item){
        if(item == null){
            return null;
        }
        ItemDto itemDto = new ItemDto();
        itemDto.setProduto(ProdutoCast.castProduto(item.getProduto()));
        itemDto.setQuantidadeProduto(item.getQuantidadeProduto());
        itemDto.setPedidoShopify(PedidoShopifyCast.castPedidoShopify(item.getPedidoShopify()));
        itemDto.setPedidoAliexpress(PedidoAliexpressCast.castPedidoAliexpress(item.getPedidoAliexpress()));
        itemDto.setIdItem(item.getIdItem());
        return itemDto;
    }
}
