package com.br.erik5594.util.cast;

import com.br.erik5594.dto.PedidoAliexpressDto;
import com.br.erik5594.model.PedidoAliexpress;

public class PedidoAliexpressCast {
    public static PedidoAliexpress castPedidoAliexpressDto(PedidoAliexpressDto pedidoAliexpressDto){
        if(pedidoAliexpressDto == null){
            return null;
        }
        PedidoAliexpress pedidoAliexpress = new PedidoAliexpress();
        pedidoAliexpress.setPedidoShopify(PedidoShopifyCast.castPedidoShopifyDto(pedidoAliexpressDto.getPedidoShopify()));
        pedidoAliexpress.setRastreamento(RastreamentoCast.castRastreamentoDto(pedidoAliexpressDto.getRastreamento()));
        pedidoAliexpress.setStatusPedidoAliexpress(pedidoAliexpressDto.getStatusPedidoAliexpress());
        pedidoAliexpress.setIdAliexpress(pedidoAliexpressDto.getIdAliexpress());
        pedidoAliexpress.setDataLimiteDisputa(pedidoAliexpressDto.getDataLimiteDisputa());
        return pedidoAliexpress;
    }

    public static PedidoAliexpressDto castPedidoAliexpress(PedidoAliexpress pedidoAliexpress){
        if(pedidoAliexpress == null){
            return null;
        }
        PedidoAliexpressDto pedidoAliexpressDto = new PedidoAliexpressDto();
        pedidoAliexpressDto.setPedidoShopify(PedidoShopifyCast.castPedidoShopify(pedidoAliexpress.getPedidoShopify()));
        pedidoAliexpressDto.setRastreamento(RastreamentoCast.castRastreamento(pedidoAliexpress.getRastreamento()));
        pedidoAliexpressDto.setStatusPedidoAliexpress(pedidoAliexpress.getStatusPedidoAliexpress());
        pedidoAliexpressDto.setIdAliexpress(pedidoAliexpress.getIdAliexpress());
        pedidoAliexpressDto.setDataLimiteDisputa(pedidoAliexpress.getDataLimiteDisputa());
        return pedidoAliexpressDto;
    }
}
