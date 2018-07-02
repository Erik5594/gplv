package com.br.erik5594.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Table(name = "pedido_aliexpress")
public @Data class PedidoAliexpress implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id_pedido_aliexpress", columnDefinition = "numeric")
    private Long idAliexpress;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_limite_disputa")
    private Date dataLimiteDisputa;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pedido_aliexpress")
    private StatusPedidoAliexpress statusPedidoAliexpress;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "cod_rastreamento")
    private Rastreamento rastreamento;

    @Transient
    private int numeroPedidoShopify;

    @Transient
    private String skuProduto;

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
