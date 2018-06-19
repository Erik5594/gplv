package com.br.erik5594.dto;

import com.br.erik5594.model.StatusPedidoAliexpress;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public @Data class PedidoAliexpressDto implements Serializable {

    private BigDecimal idAliexpress = BigDecimal.ZERO;
    private Date dataLimiteDisputa;
    private StatusPedidoAliexpress statusPedidoAliexpress;
    private RastreamentoDto rastreamento;
    private PedidoShopifyDto pedidoShopify;

    @Override
    public boolean equals(Object o) {
        if(this == null && o == null) return true;
        if(this != null && o == null) return false;
        if(this == null && o != null) return false;
        PedidoAliexpressDto that = (PedidoAliexpressDto) o;
        return Objects.equals(idAliexpress, that.idAliexpress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idAliexpress);
    }

    public String getDataLimiteDisputaFormatada(){
        if(dataLimiteDisputa == null) return null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(dataLimiteDisputa);
    }

    public String getLinkPedidoAliexpress(){
        return "https://trade.aliexpress.com/order_detail.htm?spm=a2g0s.9042311.0.0.3da2b90a3aiSTY&orderId="+idAliexpress.toString();
    }
}
