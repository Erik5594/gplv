package com.br.erik5594.bo;

import com.br.erik5594.dao.ItemDao;
import com.br.erik5594.dto.ItemDto;
import com.br.erik5594.dto.PedidoShopifyDto;

import java.io.Serializable;
import java.util.List;

public class ItemBo implements Serializable{

    private ItemDao itemDao = new ItemDao();

    public boolean salvarListaItens(List<ItemDto> itensDto){
        return false;
    }

    public List<ItemDto> getTodosItens(){
        return null;
    }

    public List<ItemDto> getListaItensByPedido(PedidoShopifyDto pedidoShopifyDto){
        return null;
    }

}
