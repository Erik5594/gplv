package com.br.erik5594.bo;

import com.br.erik5594.dao.PedidoShopifyDao;
import com.br.erik5594.dto.ItemDto;
import com.br.erik5594.dto.PedidoAliexpressDto;
import com.br.erik5594.dto.PedidoShopifyDto;
import com.br.erik5594.model.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PedidoShopifyBo implements Serializable{

    private PedidoShopifyDao pedidoShopifyDao = new PedidoShopifyDao();
    private ItemBo itemBo = new ItemBo();

    public boolean salvarListaPedidoShopify(List<PedidoShopifyDto> pedidosShopifyDto){
        List<PedidoShopify> pedidosShopify = new ArrayList<>();
        for(PedidoShopifyDto pedidoDto : pedidosShopifyDto){
            PedidoShopify pedido = new PedidoShopify();
            pedido.setNumeroPedido(pedidoDto.getNumeroPedido());
            pedido.setDataPedido(pedidoDto.getDataPedido());
            pedido.setNomeCliente(pedidoDto.getNomeCliente());
            pedido.setNomeCidadeCliente(pedidoDto.getNomeCidadeCliente());
            pedido.setNomeEstadoCliente(pedidoDto.getNomeEstadoCliente());
            pedido.setCepCliente(pedidoDto.getCepCliente());
            pedido.setItens(null);
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
        for(PedidoShopify pedidoShopify : pedidosShopify){
            PedidoShopifyDto pedidoShopifyDto = new PedidoShopifyDto();
            pedidoShopifyDto.setNumeroPedido(pedidoShopify.getNumeroPedido());
            pedidoShopifyDto.setDataPedido(pedidoShopify.getDataPedido());
            pedidoShopifyDto.setNomeCliente(pedidoShopify.getNomeCliente());
            pedidoShopifyDto.setNomeCidadeCliente(pedidoShopify.getNomeCidadeCliente());
            pedidoShopifyDto.setNomeEstadoCliente(pedidoShopify.getNomeEstadoCliente());
            pedidoShopifyDto.setCepCliente(pedidoShopify.getCepCliente());
            pedidoShopifyDto.setItens(null);
            pedidosShopifyDto.add(pedidoShopifyDto);

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
            List<ItemDto> itens = itemBo.getListaItensByPedido(pedidoRetorno);
            pedidoRetorno.setItens(itens);
        }
        return pedidoRetorno;
    }
}
