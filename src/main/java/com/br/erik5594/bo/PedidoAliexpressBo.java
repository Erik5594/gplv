package com.br.erik5594.bo;

import com.br.erik5594.dao.PedidoAliexpressDao;
import com.br.erik5594.dto.PedidoAliexpressDto;
import com.br.erik5594.dto.RastreamentoDto;
import com.br.erik5594.model.PedidoAliexpress;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PedidoAliexpressBo implements Serializable{

    private PedidoAliexpressDao pedidoAliexpressDao = new PedidoAliexpressDao();
    private PedidoShopifyBo pedidoShopifyBo = new PedidoShopifyBo();

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

    public PedidoAliexpressDto getPedidoAliexpressById(BigDecimal id){
        List<PedidoAliexpressDto> todosPedidos = getTodosPedidosAliexpress();
        PedidoAliexpressDto retorno = null;
        for(PedidoAliexpressDto pedidoAliexpressDto : todosPedidos){
            if(Objects.equals(pedidoAliexpressDto.getIdAliexpress(), id)){
                retorno = pedidoAliexpressDto;
                break;
            }
        }
        return retorno;
    }

    public void vinculaPedidoAliexpress(BufferedReader linhasArquivo, String separador) throws IOException, ParseException {
        String linha = linhasArquivo.readLine();
        linha = linhasArquivo.readLine();
        String numeroPedido = "";
        PedidoAliexpressDto pedidoAliexpress = new PedidoAliexpressDto();
        while (linha != null) {
            String[] vetorObjeto = linha.split(separador);
            if(vetorObjeto.length >= 13){
                String skuProduto = vetorObjeto[7];
                int numeroPedidoShopify = Integer.parseInt(vetorObjeto[0].replaceAll("\\D",""));
                if(!numeroPedido.equals(vetorObjeto[12])) {
                    pedidoAliexpress = obterObjetoPedido(vetorObjeto);
                    numeroPedido = pedidoAliexpress.getIdAliexpress().toString();
                }
                pedidoShopifyBo.vincularPedidoAliexpress(numeroPedidoShopify, skuProduto, pedidoAliexpress);
                linha = linhasArquivo.readLine();
                continue;
            }
            linha = linhasArquivo.readLine();
        }
        linhasArquivo.close();
    }

    private PedidoAliexpressDto obterObjetoPedido(String[] vetorObjeto) throws ParseException {
        PedidoAliexpressDto pedidoAliexpressDto = new PedidoAliexpressDto();
        RastreamentoDto rastreamentoDto = new RastreamentoDto();
        rastreamentoDto.setCodigoRastreamento(vetorObjeto[11]);
        pedidoAliexpressDto.setRastreamento(rastreamentoDto);
        pedidoAliexpressDto.setIdAliexpress(new BigDecimal(vetorObjeto[12]));
        return pedidoAliexpressDto;
    }

}
