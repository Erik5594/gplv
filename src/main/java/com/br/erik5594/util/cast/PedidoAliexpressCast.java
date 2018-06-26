package com.br.erik5594.util.cast;

import com.br.erik5594.dto.PedidoAliexpressDto;
import com.br.erik5594.model.PedidoAliexpress;

public class PedidoAliexpressCast {
    public static PedidoAliexpress castPedidoAliexpressDto(PedidoAliexpressDto pedidoAliexpressDto){
        if(pedidoAliexpressDto == null){
            return null;
        }
        PedidoAliexpress pedidoAliexpress = new PedidoAliexpress();
        pedidoAliexpress.setRastreamento(RastreamentoCast.castRastreamentoDto(pedidoAliexpressDto.getRastreamento()));
        pedidoAliexpress.setStatusPedidoAliexpress(pedidoAliexpressDto.getStatusPedidoAliexpress());
        pedidoAliexpress.setIdAliexpress(pedidoAliexpressDto.getIdAliexpress());
        pedidoAliexpress.setDataLimiteDisputa(pedidoAliexpressDto.getDataLimiteDisputa());
        pedidoAliexpress.setSkuProduto(pedidoAliexpressDto.getSkuProduto());
        pedidoAliexpress.setNumeroPedidoShopify(pedidoAliexpressDto.getNumeroPedidoShopify());
        return pedidoAliexpress;
    }

    public static PedidoAliexpressDto castPedidoAliexpress(PedidoAliexpress pedidoAliexpress){
        if(pedidoAliexpress == null){
            return null;
        }
        PedidoAliexpressDto pedidoAliexpressDto = new PedidoAliexpressDto();
        pedidoAliexpressDto.setRastreamento(RastreamentoCast.castRastreamento(pedidoAliexpress.getRastreamento()));
        pedidoAliexpressDto.setStatusPedidoAliexpress(pedidoAliexpress.getStatusPedidoAliexpress());
        pedidoAliexpressDto.setIdAliexpress(pedidoAliexpress.getIdAliexpress());
        pedidoAliexpressDto.setDataLimiteDisputa(pedidoAliexpress.getDataLimiteDisputa());
        pedidoAliexpressDto.setSkuProduto(pedidoAliexpress.getSkuProduto());
        pedidoAliexpressDto.setNumeroPedidoShopify(pedidoAliexpress.getNumeroPedidoShopify());
        return pedidoAliexpressDto;
    }
}
