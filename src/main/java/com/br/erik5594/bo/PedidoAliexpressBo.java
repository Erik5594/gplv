package com.br.erik5594.bo;

import com.br.erik5594.dao.PedidoAliexpressDao;
import com.br.erik5594.dto.PedidoAliexpressDto;
import com.br.erik5594.dto.PedidoShopifyDto;
import com.br.erik5594.dto.RastreamentoDto;
import com.br.erik5594.model.PedidoAliexpress;
import com.br.erik5594.util.cast.PedidoAliexpressCast;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PedidoAliexpressBo implements Serializable{

    @Inject
    private PedidoAliexpressDao pedidoAliexpressDao;
    @Inject
    private PedidoShopifyBo pedidoShopifyBo;
    @Inject
    private RastreamentoBo rastreamentoBo;

    public List<PedidoAliexpressDto> getTodosPedidosAliexpress(){
        List<PedidoAliexpressDto> pedidosAliexpressDto = new ArrayList<>();
        List<PedidoAliexpress> pedidosAliexpress = pedidoAliexpressDao.getTodosPedidosAliexpress();
        if(pedidosAliexpress == null){
            return pedidosAliexpressDto;
        }
        for(PedidoAliexpress pedidoAliexpress : pedidosAliexpress){
            pedidosAliexpressDto.add(PedidoAliexpressCast.castPedidoAliexpress(pedidoAliexpress));
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
                adicionarPedidoAliexpress(pedidoAliexpress);
                if(pedidoAliexpress.getRastreamento() != null){
                    rastreamentoBo.adicionarRastreamento(pedidoAliexpress.getRastreamento());
                }
                linha = linhasArquivo.readLine();
                continue;
            }
            linha = linhasArquivo.readLine();
        }
        linhasArquivo.close();
    }

    private PedidoAliexpressDto obterObjetoPedido(String[] vetorObjeto) throws ParseException {
        PedidoAliexpressDto pedidoAliexpressDto = new PedidoAliexpressDto();
        PedidoShopifyDto pedidoShopifyDto = pedidoShopifyBo.getPedidoShopifyByNumeroPedido(Integer.parseInt(vetorObjeto[0].replaceAll("\\D","")));
        RastreamentoDto rastreamentoDto = new RastreamentoDto();
        rastreamentoDto.setCodigoRastreamento(vetorObjeto[11]);
        pedidoAliexpressDto.setRastreamento(rastreamentoDto);
        pedidoAliexpressDto.setIdAliexpress(new BigDecimal(vetorObjeto[12]));
        pedidoAliexpressDto.setPedidoShopify(pedidoShopifyDto);
        return pedidoAliexpressDto;
    }

    public boolean adicionarPedidoAliexpress(PedidoAliexpressDto pedidoAliexpressDto){
        return pedidoAliexpressDao.adicionarPedidoAliexpress(PedidoAliexpressCast.castPedidoAliexpressDto(pedidoAliexpressDto));
    }

}
