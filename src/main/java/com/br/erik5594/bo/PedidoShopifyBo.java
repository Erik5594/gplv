package com.br.erik5594.bo;

import com.br.erik5594.dao.PedidoShopifyDao;
import com.br.erik5594.dto.ClienteDto;
import com.br.erik5594.dto.ItemDto;
import com.br.erik5594.dto.PedidoAliexpressDto;
import com.br.erik5594.dto.PedidoShopifyDto;
import com.br.erik5594.model.PedidoAliexpress;
import com.br.erik5594.model.PedidoShopify;
import com.br.erik5594.model.Rastreamento;
import com.br.erik5594.util.Util;
import com.br.erik5594.util.cast.PedidoShopifyCast;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PedidoShopifyBo implements Serializable{

    @Inject
    private PedidoShopifyDao pedidoShopifyDao;
    @Inject
    private ClienteBo clienteBo;
    @Inject
    private ProdutoBo produtoBo;

    public boolean salvarListaPedidoShopify(List<PedidoShopifyDto> pedidosShopifyDto){
        List<PedidoShopify> pedidosShopify = new ArrayList<>();
        for(PedidoShopifyDto pedidoDto : pedidosShopifyDto){
            pedidosShopify.add(PedidoShopifyCast.castPedidoShopifyDto(pedidoDto));
        }
        return pedidoShopifyDao.salvarListaPedidoShopify(pedidosShopify);
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
        List<PedidoShopifyDto> todosPedidos = getTodosPedidosShopify();
        PedidoShopifyDto pedidoRetorno = null;
        if(todosPedidos != null && !todosPedidos.isEmpty()){
            for(PedidoShopifyDto pedido : todosPedidos){
                if(numeroPedido == pedido.getNumeroPedido()){
                    pedidoRetorno = pedido;
                    break;
                }
            }
        }
        return pedidoRetorno;
    }

    public List<PedidoShopifyDto> getListaDeObjetoDoArquivo(BufferedReader linhasArquivo, String separador) throws IOException, ParseException {
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

    private PedidoShopifyDto obterObjetoPedido(String[] vetorObjeto) throws ParseException {
        PedidoShopifyDto pedidoDto = new PedidoShopifyDto();
        ClienteDto clienteDto;
        pedidoDto.setNumeroPedido(Integer.parseInt(vetorObjeto[0].replaceAll("\\D","")));
        pedidoDto.setDataPedido(Util.formatarData(vetorObjeto[15].substring(0,10),"yyyy-MM-dd"));
        pedidoDto.setValorTotal(StringUtils.isBlank(vetorObjeto[11]) ? 0f:Float.parseFloat(vetorObjeto[11]));
        pedidoDto.setEnviado("fulfilled".equals(vetorObjeto[4]));
        pedidoDto.setIdPedido(vetorObjeto[55]);
        if(vetorObjeto.length >= 8){
            clienteDto = clienteBo.buscarCliente(vetorObjeto[1].toLowerCase(), vetorObjeto[33].replaceAll("\\D",""));
        }else{
            clienteDto = clienteBo.buscarCliente(vetorObjeto[1].toLowerCase(), null);
        }
        pedidoDto.setCliente(clienteDto);

        if(vetorObjeto.length >= 47){
            if(StringUtils.isNotBlank(vetorObjeto[46]) && vetorObjeto[46].length() > 10){
                pedidoDto.setDataCancelamento(Util.formatarData(vetorObjeto[46].substring(0,10),"yyyy-MM-dd"));
            }
        }
        return pedidoDto;
    }

    private ItemDto obterObjetoItem(String[] vetorObjeto, PedidoShopifyDto pedido) throws ParseException {
        ItemDto itemDto = new ItemDto();
        itemDto.setQuantidadeProduto(Integer.parseInt(vetorObjeto[16]));
        itemDto.setProduto(produtoBo.buscarProduto(vetorObjeto[20]));
        itemDto.setPedidoShopify(pedido);
        return itemDto;
    }

    public boolean vincularPedidoAliexpress(int numeroPedidoShopify, String skuProduto, PedidoAliexpressDto pedidoAliexpressDto){
        PedidoAliexpress pedidoAliexpress = new PedidoAliexpress();
        pedidoAliexpress.setStatusPedidoAliexpress(pedidoAliexpressDto.getStatusPedidoAliexpress());
        pedidoAliexpress.setIdAliexpress(pedidoAliexpressDto.getIdAliexpress());
        pedidoAliexpress.setDataLimiteDisputa(pedidoAliexpressDto.getDataLimiteDisputa());

        Rastreamento rastreamento = new Rastreamento();
        rastreamento.setUrlImagemUltimoStatus(pedidoAliexpressDto.getRastreamento().getUrlImagemUltimoStatus());
        rastreamento.setReclamacaoCorreios(null);
        rastreamento.setEventos(null);
        rastreamento.setDataUltimaAtualizacao(pedidoAliexpressDto.getRastreamento().getDataUltimaAtualizacao());
        rastreamento.setCodigoRastreamento(pedidoAliexpressDto.getRastreamento().getCodigoRastreamento());
        pedidoAliexpress.setRastreamento(rastreamento);

        return pedidoShopifyDao.vincularPedidoAliexpress(numeroPedidoShopify, skuProduto, pedidoAliexpress);
    }

}
