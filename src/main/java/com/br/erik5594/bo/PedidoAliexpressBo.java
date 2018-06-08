package com.br.erik5594.bo;

import com.br.erik5594.dao.PedidoAliexpressDao;
import com.br.erik5594.dto.PedidoAliexpressDto;
import com.br.erik5594.dto.PedidoShopifyDto;
import com.br.erik5594.model.PedidoAliexpress;
import com.br.erik5594.model.PedidoShopify;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PedidoAliexpressBo implements Serializable{

    private PedidoAliexpressDao pedidoAliexpressDao = new PedidoAliexpressDao();

    public boolean salvarListaPedidoAliexpress(List<PedidoAliexpressDto> pedidosAliexpressDto){
        List<PedidoAliexpress> pedidosAliexpress = new ArrayList<>();
        for(PedidoAliexpressDto pedidoAliexpressDto : pedidosAliexpressDto){
            PedidoAliexpress pedido = new PedidoAliexpress();
            pedido.setStatusPedidoAliexpress(pedidoAliexpressDto.getStatusPedidoAliexpress());
            pedido.setIdAliexpress(pedidoAliexpressDto.getIdAliexpress());
            pedido.setDataLimiteDisputa(pedidoAliexpressDto.getDataLimiteDisputa());
            pedidosAliexpress.add(pedido);
        }
        return pedidoAliexpressDao.salvarListaPedidoAliexpress(pedidosAliexpress);
    }

    public List<PedidoAliexpressDto> getTodosPedidosAliexpress(){
        List<PedidoAliexpressDto> pedidosAliexpressDto = new ArrayList<>();
        List<PedidoAliexpress> pedidosAliexpress = pedidoAliexpressDao.getTodosPedidosAliexpress();
        if(pedidosAliexpress == null){
            return pedidosAliexpressDto;
        }
        for(PedidoAliexpress pedidoAliexpress : pedidosAliexpress){
            PedidoAliexpressDto pedidoAliexpressDto = new PedidoAliexpressDto();
            pedidoAliexpressDto.setStatusPedidoAliexpress(pedidoAliexpress.getStatusPedidoAliexpress());
            pedidoAliexpressDto.setIdAliexpress(pedidoAliexpress.getIdAliexpress());
            pedidoAliexpressDto.setDataLimiteDisputa(pedidoAliexpress.getDataLimiteDisputa());
            pedidosAliexpressDto.add(pedidoAliexpressDto);
        }
        return pedidosAliexpressDto;
    }

}
