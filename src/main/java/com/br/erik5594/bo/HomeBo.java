package com.br.erik5594.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.br.erik5594.dto.PedidoAliexpressDto;

public class HomeBo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
    private PedidoShopifyBo pedidoShopifyBo;
    @Inject
    private PedidoAliexpressBo pedidoAliexpressBo;
    @Inject
    private ProdutoBo produtoBo;
    @Inject
    private ClienteBo clienteBo;

    public Long quantidadePedidosShopify(){
        return pedidoShopifyBo.getTotalPedidosShopify();
    }

    public Long quantidadePedidosAliexpress(){
        return pedidoAliexpressBo.getTotalPedidosAliexpress();
    }

    public Long quantidadeProdutos(){
        return produtoBo.getTotalProdutos();
    }

    public Long quantidadeClientes(){
        return clienteBo.getTotalClientes();
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
        return pedidoAliexpressBo.pedidosVencendoPrazo().size();
    }
}
