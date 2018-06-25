package com.br.erik5594.util.cast;

import com.br.erik5594.dto.ItemDto;
import com.br.erik5594.dto.PedidoShopifyDto;
import com.br.erik5594.model.Item;
import com.br.erik5594.model.PedidoShopify;

public class ItemCast {
    public static Item castItemDto(ItemDto itemDto, PedidoShopify pedidoShopify){
        if(itemDto == null){
            return null;
        }
        Item item = new Item();
        item.setProduto(ProdutoCast.castProdutoDto(itemDto.getProduto()));
        item.setQuantidadeProduto(itemDto.getQuantidadeProduto());
        item.setPedidoShopify(pedidoShopify);
        item.setPedidoAliexpress(PedidoAliexpressCast.castPedidoAliexpressDto(itemDto.getPedidoAliexpress()));
        return item;
    }

    public static ItemDto castItem(Item item, PedidoShopifyDto pedidoShopifyDto){
        if(item == null){
            return null;
        }
        ItemDto itemDto = new ItemDto();
        itemDto.setProduto(ProdutoCast.castProduto(item.getProduto()));
        itemDto.setQuantidadeProduto(item.getQuantidadeProduto());
        itemDto.setPedidoShopify(pedidoShopifyDto);
        itemDto.setPedidoAliexpress(PedidoAliexpressCast.castPedidoAliexpress(item.getPedidoAliexpress()));
        itemDto.setIdItem(item.getIdItem());
        return itemDto;
    }
}
