package com.br.erik5594.bo;

import com.br.erik5594.dto.PedidoAliexpressDto;
import com.br.erik5594.dto.PedidoShopifyDto;
import com.br.erik5594.model.StatusPedidoAliexpress;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeBo implements Serializable{
    private PedidoShopifyBo pedidoShopifyBo = new PedidoShopifyBo();
    private ItemBo itemBo = new ItemBo();
    private PedidoAliexpressBo pedidoAliexpressBo = new PedidoAliexpressBo();
    private ProdutoBo produtoBo = new ProdutoBo();
    private RastreamentoBo rastreamentoBo = new RastreamentoBo();
    private ClienteBo clienteBo = new ClienteBo();

    public int quantidadePedidosShopify(){
        return pedidoShopifyBo.getTodosPedidosShopify().size();
    }

    public int quantidadePedidosAliexpress(){
        return pedidoAliexpressBo.getTodosPedidosAliexpress().size();
    }

    public int quantidadeProdutos(){
        return produtoBo.getTodosProdutos().size();
    }

    public int quantidadeItens(){
        return 0;
    }

    public int quantidadeRastreaemnto(){
        return rastreamentoBo.getTodosRastreamentos().size();
    }

    public int quantidadeClientes(){
        return clienteBo.getTodosClientes().size();
    }

    public int pedidosSemDataLimite(){
        List<PedidoAliexpressDto> semDatalimite = new ArrayList<>();
        List<PedidoAliexpressDto> todosPedidosAliexpress = pedidoAliexpressBo.getTodosPedidosAliexpress();
        for(PedidoAliexpressDto pedidoAliexpressDto : todosPedidosAliexpress){
            if(pedidoAliexpressDto.getDataLimiteDisputa() == null){
                semDatalimite.add(pedidoAliexpressDto);
            }
        }
        return semDatalimite.size();
    }

    public int pedidosVencendoPrazo(){
        List<PedidoAliexpressDto> vencendoPrazo = new ArrayList<>();
        List<PedidoAliexpressDto> todosPedidosAliexpress = pedidoAliexpressBo.getTodosPedidosAliexpress();
        for(PedidoAliexpressDto pedidoAliexpressDto : todosPedidosAliexpress){
            Calendar data = Calendar.getInstance();
            data.add(Calendar.DAY_OF_YEAR, 5);
            if(pedidoAliexpressDto.getDataLimiteDisputa() != null
                    && data.getTime().after(pedidoAliexpressDto.getDataLimiteDisputa())
                    && !pedidoAliexpressDto.getStatusPedidoAliexpress().equals(StatusPedidoAliexpress.CONCLUIDO)){
                vencendoPrazo.add(pedidoAliexpressDto);
            }
        }
        return vencendoPrazo.size();
    }
}
