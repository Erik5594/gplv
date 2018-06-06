package com.br.erik5594.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public @Data class PedidoAliexpress {

    private BigDecimal idAliexpress = BigDecimal.ZERO;
    private Date dataLimiteDisputa;
    private StatusPedidoAliexpress statusPedidoAliexpress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PedidoAliexpress that = (PedidoAliexpress) o;
        return Objects.equals(idAliexpress, that.idAliexpress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idAliexpress);
    }
}
