package com.br.erik5594.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity(name = "pedido_aliexpress")
public @Data class PedidoAliexpress implements Serializable {

    @Id
    @Column(name = "id_pedido_aliexpress")
    private BigDecimal idAliexpress = BigDecimal.ZERO;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_limite_disputa")
    private Date dataLimiteDisputa;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pedido_aliexpress")
    private StatusPedidoAliexpress statusPedidoAliexpress;

    @OneToOne
    @JoinColumn(name = "cod_rastreamento")
    private Rastreamento rastreamento;

    @ManyToOne
    @JoinColumn(name = "pedido_shopify")
    private PedidoShopify pedidoShopify;

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
