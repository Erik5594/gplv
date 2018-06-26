package com.br.erik5594.bo;

import com.br.erik5594.dao.PedidoAliexpressDao;
import com.br.erik5594.dto.PedidoAliexpressDto;
import com.br.erik5594.dto.RastreamentoDto;
import com.br.erik5594.model.PedidoAliexpress;
import com.br.erik5594.model.StatusPedidoAliexpress;
import com.br.erik5594.util.cast.PedidoAliexpressCast;
import com.br.erik5594.util.jpa.Transactional;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PedidoAliexpressBo implements Serializable{

    @Inject
    private PedidoAliexpressDao pedidoAliexpressDao;
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

    public PedidoAliexpressDto getPedidoAliexpressById(Long id){
        PedidoAliexpress pedidoAliexpress = pedidoAliexpressDao.buscarPedidoAliexpress(id);
        if(pedidoAliexpress != null){
            return PedidoAliexpressCast.castPedidoAliexpress(pedidoAliexpress);
        }
        return null;
    }

    public List<PedidoAliexpressDto> getListaDeObjetoDoArquivo(BufferedReader linhasArquivo, String separador) throws IOException{
        List<PedidoAliexpressDto> pedidosAliexpressDto = new ArrayList<>();
        String linha = linhasArquivo.readLine();
        linha = linhasArquivo.readLine();
        String numeroPedido = "";
        PedidoAliexpressDto pedidoAliexpress;
        while (linha != null) {
            String[] vetorObjeto = linha.split(separador);
            if(vetorObjeto.length >= 13){
                if(!numeroPedido.equals(vetorObjeto[12])) {
                    pedidoAliexpress = obterObjetoPedido(vetorObjeto);
                    RastreamentoDto rastreamentoDto = obterObjetoRastreamento(vetorObjeto[11]);
                    pedidoAliexpress.setRastreamento(rastreamentoDto);
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
        pedidoAliexpressDto.setIdAliexpress(new Long(vetorObjeto[12]));
        pedidoAliexpressDto.setStatusPedidoAliexpress(StatusPedidoAliexpress.NORMAL);
        return pedidoAliexpressDto;
    }

    private RastreamentoDto obterObjetoRastreamento(String codigoRastreamento) {
        RastreamentoDto rastreamentoDto = rastreamentoBo.buscarRastreamento(codigoRastreamento);
        if(rastreamentoDto == null){
            rastreamentoDto = new RastreamentoDto();
            rastreamentoDto.setCodigoRastreamento(codigoRastreamento);
            rastreamentoBo.adicionarRastreamento(rastreamentoDto);
        }
        return rastreamentoDto;
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
            pedidosAliexpressDto.add(PedidoAliexpressCast.castPedidoAliexpress(pedidoAliexpress));
        }
        return pedidosAliexpressDto;
    }

    @Transactional
    public void salvarListaPedidoAliexpress(List<PedidoAliexpressDto> pedidosAliexpressDto){
        List<PedidoAliexpress> pedidosAliexpress = new ArrayList<>();
        for(PedidoAliexpressDto pedidoAliexpressDto : pedidosAliexpressDto){
            pedidosAliexpress.add(PedidoAliexpressCast.castPedidoAliexpressDto(pedidoAliexpressDto));
        }
        pedidoAliexpressDao.salvarListaPedidoAliexpress(pedidosAliexpress);
    }
}
