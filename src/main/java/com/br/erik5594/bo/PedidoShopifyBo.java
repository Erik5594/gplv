package com.br.erik5594.bo;

import com.br.erik5594.dao.PedidoShopifyDao;
import com.br.erik5594.dto.ClienteDto;
import com.br.erik5594.dto.ItemDto;
import com.br.erik5594.dto.PedidoShopifyDto;
import com.br.erik5594.dto.ProdutoDto;
import com.br.erik5594.model.PedidoShopify;
import com.br.erik5594.util.Util;
import com.br.erik5594.util.cast.PedidoShopifyCast;
import com.br.erik5594.util.jpa.Transactional;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PedidoShopifyBo implements Serializable{

    @Inject
    private PedidoShopifyDao pedidoShopifyDao;

    @Transactional
    public void salvarListaPedidoShopify(List<PedidoShopifyDto> pedidosShopifyDto) throws Exception{
        List<PedidoShopify> pedidosShopify = new ArrayList<>();
        for(PedidoShopifyDto pedidoDto : pedidosShopifyDto){
            pedidosShopify.add(PedidoShopifyCast.castPedidoShopifyDto(pedidoDto));
        }
        pedidoShopifyDao.salvarListaPedidoShopify(pedidosShopify);
    }

    public List<PedidoShopifyDto> getTodosPedidosShopify(){
        List<PedidoShopifyDto> pedidosShopifyDto = new ArrayList<>();
        List<PedidoShopify> pedidosShopify = pedidoShopifyDao.getTodosPedidosShopify();
        if(pedidosShopify == null){
            return pedidosShopifyDto;
        }
        for(PedidoShopify pedido : pedidosShopify){
            pedidosShopifyDto.add(PedidoShopifyCast.castPedidoShopify(pedido));
        }
        return pedidosShopifyDto;
    }

    public PedidoShopifyDto getPedidoShopifyByNumeroPedido(int numeroPedido){
        return PedidoShopifyCast.castPedidoShopify(pedidoShopifyDao.buscarPedidoShopify(numeroPedido));
    }

    public List<PedidoShopifyDto> getListaDeObjetoDoArquivo(BufferedReader linhasArquivo, String separador) throws Exception {
        List<PedidoShopifyDto> pedidosDto = new ArrayList<>();
        String linha = linhasArquivo.readLine();
        linha = linhasArquivo.readLine();
        int numeroPedido = 0;
        PedidoShopifyDto pedido = new PedidoShopifyDto();
        while (linha != null) {
            String[] vetorObjeto = linha.split(separador);
            if(vetorObjeto.length >= 56){
                if(numeroPedido != Integer.parseInt(vetorObjeto[0].replaceAll("\\D",""))) {
                    pedido = obterObjetoPedido(vetorObjeto);
                    numeroPedido = pedido.getNumeroPedido();
                    pedidosDto.add(pedido);
                }
                if(pedido.getItens() == null){
                    pedido.setItens(new ArrayList<ItemDto>());
                }
                pedido.getItens().add(obterObjetoItem(vetorObjeto, pedido));
                linha = linhasArquivo.readLine();
                continue;
            }
            linha = linhasArquivo.readLine();
        }
        linhasArquivo.close();
        return pedidosDto;
    }

    private PedidoShopifyDto obterObjetoPedido(String[] vetorObjeto) throws Exception {
        PedidoShopifyDto pedidoDto = new PedidoShopifyDto();
        pedidoDto.setNumeroPedido(Integer.parseInt(vetorObjeto[0].replaceAll("\\D","")));
        pedidoDto.setDataPedido(Util.formatarData(vetorObjeto[15].substring(0,10),"yyyy-MM-dd"));
        pedidoDto.setValorTotal(StringUtils.isBlank(vetorObjeto[11]) ? 0f:Float.parseFloat(vetorObjeto[11]));
        pedidoDto.setEnviado("fulfilled".equals(vetorObjeto[4]));
        pedidoDto.setIdPedido(vetorObjeto[55]);
        if(StringUtils.isNotBlank(vetorObjeto[46]) && vetorObjeto[46].length() > 10){
            pedidoDto.setDataCancelamento(Util.formatarData(vetorObjeto[46].substring(0,10),"yyyy-MM-dd"));
        }

        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setEmail(vetorObjeto[1].toLowerCase());
        clienteDto.setTelefone(vetorObjeto[33].replaceAll("\\D",""));
        pedidoDto.setCliente(clienteDto);

        return pedidoDto;
    }

    private ItemDto obterObjetoItem(String[] vetorObjeto, PedidoShopifyDto pedido) throws ParseException {
        ItemDto itemDto = new ItemDto();
        itemDto.setQuantidadeProduto(Integer.parseInt(vetorObjeto[16]));
        ProdutoDto produtoDto = new ProdutoDto();
        produtoDto.setSkuProduto(vetorObjeto[20]);
        itemDto.setProduto(produtoDto);
        itemDto.setPedidoShopify(pedido);
        return itemDto;
    }

    public Long getTotalPedidosShopify(){
        return pedidoShopifyDao.totalPedidos();
    }
}
