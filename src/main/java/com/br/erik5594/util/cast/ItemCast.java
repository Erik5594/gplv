package com.br.erik5594.util.cast;

import com.br.erik5594.dto.ItemDto;
import com.br.erik5594.dto.PedidoShopifyDto;
import com.br.erik5594.dto.ProdutoDto;
import com.br.erik5594.model.Item;
import com.br.erik5594.model.ItemPk;
import com.br.erik5594.model.PedidoShopify;
import com.br.erik5594.model.Produto;

public class ItemCast {
    public static Item castItemDto(ItemDto itemDto, PedidoShopify pedidoShopify){
        if(itemDto == null){
            return null;
        }
        ItemPk itemPk = new ItemPk();
        Produto produto = ProdutoCast.castProdutoDto(itemDto.getProduto());

        itemPk.setPedidoShopify(pedidoShopify);
        itemPk.setProduto(produto);

        Item item = new Item();
        item.setId(itemPk);
        item.setQuantidadeProduto(itemDto.getQuantidadeProduto());
        item.setPedidoAliexpress(PedidoAliexpressCast.castPedidoAliexpressDto(itemDto.getPedidoAliexpress()));

        return item;
    }

    public static ItemDto castItem(Item item, PedidoShopifyDto pedidoShopifyDto){
        if(item == null){
            return null;
        }
        ItemDto itemDto = new ItemDto();
        itemDto.setProduto(ProdutoCast.castProduto(item.getId().getProduto()));
        itemDto.setQuantidadeProduto(item.getQuantidadeProduto());
        itemDto.setPedidoShopify(pedidoShopifyDto);
        itemDto.setPedidoAliexpress(PedidoAliexpressCast.castPedidoAliexpress(item.getPedidoAliexpress()));
        return itemDto;
    }
}
