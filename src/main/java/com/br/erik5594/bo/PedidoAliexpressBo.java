package com.br.erik5594.bo;

import com.br.erik5594.dao.PedidoAliexpressDao;
import com.br.erik5594.dao.RastreamentoDao;
import com.br.erik5594.dto.*;
import com.br.erik5594.model.*;
import com.br.erik5594.util.cast.ItemCast;
import com.br.erik5594.util.cast.PedidoAliexpressCast;
import com.br.erik5594.util.cast.PedidoShopifyCast;
import com.br.erik5594.util.cast.RastreamentoCast;
import com.br.erik5594.util.jpa.Transactional;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PedidoAliexpressBo implements Serializable{

    @Inject
    private PedidoAliexpressDao pedidoAliexpressDao;

    public List<PedidoAliexpressDto> getTodosPedidosAliexpress(){
        List<PedidoAliexpressDto> pedidosAliexpressDto = new ArrayList<>();
        List<PedidoAliexpress> pedidosAliexpress = pedidoAliexpressDao.getTodosPedidosAliexpress();
        if(pedidosAliexpress == null){
            return pedidosAliexpressDto;
        }
        for(PedidoAliexpress pedidoAliexpress : pedidosAliexpress){
            PedidoShopifyDto pedidoShopifyDto = PedidoShopifyCast.castPedidoShopify(pedidoAliexpress.getPedidoShopify());
            pedidosAliexpressDto.add(PedidoAliexpressCast.castPedidoAliexpress(pedidoAliexpress, pedidoShopifyDto));
        }
        return pedidosAliexpressDto;
    }

    public PedidoAliexpressDto getPedidoAliexpressById(Long id){
        PedidoAliexpress pedidoAliexpress = pedidoAliexpressDao.buscarPedidoAliexpress(id);
        if(pedidoAliexpress != null){
            PedidoShopifyDto pedidoShopifyDto = PedidoShopifyCast.castPedidoShopify(pedidoAliexpress.getPedidoShopify());
            return PedidoAliexpressCast.castPedidoAliexpress(pedidoAliexpress, pedidoShopifyDto);
        }
        return null;
    }

    public List<PedidoAliexpressDto> getListaDeObjetoDoArquivo(BufferedReader linhasArquivo, String separador) throws IOException{
        List<PedidoAliexpressDto> pedidosAliexpressDto = new ArrayList<>();
        String linha = linhasArquivo.readLine();
        linha = linhasArquivo.readLine();
        String numeroPedido = "";
        PedidoAliexpressDto pedidoAliexpress = new PedidoAliexpressDto();
        while (linha != null) {
            String[] vetorObjeto = linha.split(separador);
            if(vetorObjeto.length >= 13){
                if(!numeroPedido.equals(vetorObjeto[12])) {
                    pedidoAliexpress = obterObjetoPedido(vetorObjeto);
                    numeroPedido = pedidoAliexpress.getIdAliexpress().toString();
                    pedidosAliexpressDto.add(pedidoAliexpress);
                }
                linha = linhasArquivo.readLine();
                continue;
            }
            linha = linhasArquivo.readLine();
        }
        linhasArquivo.close();
        return pedidosAliexpressDto;
    }

    private PedidoAliexpressDto obterObjetoPedido(String[] vetorObjeto) {
        PedidoAliexpressDto pedidoAliexpressDto = new PedidoAliexpressDto();

        RastreamentoDto rastreamentoDto = new RastreamentoDto();
        rastreamentoDto.setCodigoRastreamento(vetorObjeto[11]);

        pedidoAliexpressDto.setRastreamento(rastreamentoDto);
        pedidoAliexpressDto.setIdAliexpress(new Long(vetorObjeto[12]));
        pedidoAliexpressDto.setStatusPedidoAliexpress(StatusPedidoAliexpress.NORMAL);
        return pedidoAliexpressDto;
    }

    public Long getTotalPedidosAliexpress(){
        return pedidoAliexpressDao.totalPedidosAliexpress();
    }

    public List<PedidoAliexpressDto> pedidosVencendoPrazo(){
        List<PedidoAliexpressDto> pedidosAliexpressDto = new ArrayList<>();
        List<PedidoAliexpress> pedidosAliexpress = pedidoAliexpressDao.pedidosVencendoDataLimite();
        if(pedidosAliexpress == null){
            return pedidosAliexpressDto;
        }
        for(PedidoAliexpress pedidoAliexpress : pedidosAliexpress){
            PedidoShopifyDto pedidoShopifyDto = PedidoShopifyCast.castPedidoShopify(pedidoAliexpress.getPedidoShopify());
            pedidosAliexpressDto.add(PedidoAliexpressCast.castPedidoAliexpress(pedidoAliexpress, pedidoShopifyDto));
        }
        return pedidosAliexpressDto;
    }

    public void salvarListaPedidoAliexpress(List<PedidoAliexpressDto> pedidosAliexpressDto){
        List<PedidoAliexpress> pedidosAliexpress = new ArrayList<>();
        for(PedidoAliexpressDto pedidoAliexpressDto : pedidosAliexpressDto){
            pedidosAliexpress.add(PedidoAliexpressCast.castPedidoAliexpressDto(pedidoAliexpressDto));
        }
        pedidoAliexpressDao.salvarListaPedidoAliexpress(pedidosAliexpress);
    }
}
