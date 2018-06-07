package com.br.erik5594.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public @Data class PedidoAliexpressDto implements Serializable {

    private BigDecimal idAliexpress = BigDecimal.ZERO;
    private Date dataLimiteDisputa;
    private StatusPedidoAliexpressDto statusPedidoAliexpress;

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
}
