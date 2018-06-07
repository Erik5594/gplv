package com.br.erik5594.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public @Data class PedidoAliexpress implements Serializable {

    private BigDecimal idAliexpress = BigDecimal.ZERO;
    private Date dataLimiteDisputa;
    private StatusPedidoAliexpress statusPedidoAliexpress;

    @Override
    public boolean equals(Object o) {
        if(this == null && o == null) return true;
        if(this != null && o == null) return false;
        if(this == null && o != null) return false;
        PedidoAliexpress that = (PedidoAliexpress) o;
        return Objects.equals(idAliexpress, that.idAliexpress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idAliexpress);
    }
}
