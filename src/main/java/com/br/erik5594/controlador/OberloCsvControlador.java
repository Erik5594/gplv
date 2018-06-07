package com.br.erik5594.controlador;

import com.br.erik5594.dto.*;
import com.br.erik5594.model.PedidoAliexpress;
import lombok.Data;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Named
@ViewScoped
public @Data class OberloCsvControlador implements Serializable {

    private List<PedidoShopifyDto> pedidoShopifies = new ArrayList<>();
    private List<ProdutoDto> produtos = new ArrayList<>();
    private List<PedidoAliexpressDto> pedidosAliexpress = new ArrayList<>();
    private List<RastreamentoDto> rastreamentosDto = new ArrayList<>();
    private List<ItemDto> itens = new ArrayList<>();

    public void upload(FileUploadEvent evento) {
        FacesMessage messagem;
        try {
            BufferedReader linhasArquivo = obterBufferReader(evento.getFile());
            getListaDeObjetoDoArquivo(linhasArquivo, ",");
            messagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso:", "Arquivo carregado com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, messagem);
        } catch (Exception e) {
            messagem = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Ocorreu um erro:", "Erro interno contate o administrador do sistema!");
            FacesContext.getCurrentInstance().addMessage(null, messagem);
            e.printStackTrace();
        }
    }

    private void getListaDeObjetoDoArquivo(BufferedReader linhasArquivo, String separador) throws IOException, ParseException {
        String linha = linhasArquivo.readLine();
        linha = linhasArquivo.readLine();
        pedidoShopifies = new ArrayList<>();
        while (linha != null) {
            PedidoShopifyDto objOberlo = (PedidoShopifyDto) obterObjetoPedido(linha.split(separador));
            ProdutoDto produtoDto = (ProdutoDto) obterObjetoProduto(linha.split(separador));
            PedidoAliexpressDto pedidoAliexpressDto = (PedidoAliexpressDto) obterObjetoPedidoAliexpress(linha.split(separador));
            RastreamentoDto rastreamento = (RastreamentoDto) obterObjetoRastreamento(linha.split(separador));
            ItemDto itemDto = (ItemDto) obterObjetoItem(linha.split(separador), pedidoAliexpressDto, objOberlo, rastreamento, produtoDto);

            if (objOberlo != null && !pedidoShopifies.contains(objOberlo)) {
                pedidoShopifies.add(objOberlo);
            }

            if (produtoDto != null && !produtos.contains(produtoDto)) {
                produtos.add(produtoDto);
            }

            if (pedidoAliexpressDto != null && !pedidosAliexpress.contains(pedidoAliexpressDto)) {
                pedidosAliexpress.add(pedidoAliexpressDto);
            }

            if (rastreamento != null && !rastreamentosDto.contains(rastreamento)) {
                rastreamentosDto.add(rastreamento);
            }

            if (itemDto != null && !itens.contains(itemDto)) {
                itens.add(itemDto);
            }

            linha = linhasArquivo.readLine();
        }
        linhasArquivo.close();
    }

    private BufferedReader obterBufferReader(UploadedFile arquivo) throws IOException {
        InputStream inputStream = arquivo.getInputstream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        return new BufferedReader(inputStreamReader);
    }

    private Object obterObjetoPedido(String[] vetorObjeto) throws ParseException {
        PedidoShopifyDto pedidoShopify = new PedidoShopifyDto();
        pedidoShopify.setNumeroPedido(Integer.parseInt(vetorObjeto[0].replaceAll("\\D","")));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        pedidoShopify.setDataPedido(format.parse(vetorObjeto[1]));
        pedidoShopify.setNomeCliente(vetorObjeto[13]);
        pedidoShopify.setNomeCidadeCliente(vetorObjeto[16]);
        pedidoShopify.setNomeEstadoCliente(vetorObjeto[19]);
        pedidoShopify.setCepCliente(vetorObjeto[17]);

        return pedidoShopify;
    }

    private Object obterObjetoProduto(String[] vetorObjeto) {
        ProdutoDto produtoDto = new ProdutoDto();
        produtoDto.setSkuProduto(vetorObjeto[7]);
        produtoDto.setNomeProduto(vetorObjeto[8]);
        produtoDto.setVarianteProduto(vetorObjeto[9]);

        return produtoDto;
    }

    private Object obterObjetoPedidoAliexpress(String[] vetorObjeto) {
        PedidoAliexpressDto pedidoAliexpressDto = new PedidoAliexpressDto();
        pedidoAliexpressDto.setIdAliexpress(new BigDecimal(vetorObjeto[12]));
        Calendar data = Calendar.getInstance();
        data.add(Calendar.DAY_OF_YEAR, 60);
        pedidoAliexpressDto.setDataLimiteDisputa(data.getTime());
        pedidoAliexpressDto.setStatusPedidoAliexpress(StatusPedidoAliexpressDto.NORMAL);

        return pedidoAliexpressDto;
    }

    private Object obterObjetoRastreamento(String[] vetorObjeto) {
        RastreamentoDto rastreamentoDto = new RastreamentoDto();

        rastreamentoDto.setCodigoRastreamento(vetorObjeto[11]);

        return rastreamentoDto;
    }

    private Object obterObjetoItem(String[] vetorObjeto, PedidoAliexpressDto pedidoAliexpressDto, PedidoShopifyDto pedidoShopifyDto, RastreamentoDto rastreamentoDto, ProdutoDto produtoDto) {
        ItemDto itemDto = new ItemDto();
        itemDto.setPedidoAliexpress(pedidoAliexpressDto);
        itemDto.setPedidoShopify(pedidoShopifyDto);
        itemDto.setProduto(produtoDto);
        itemDto.setRastreamento(rastreamentoDto);
        itemDto.setQuantidadeProduto(Integer.parseInt(vetorObjeto[10]));

        return itemDto;
    }
}
