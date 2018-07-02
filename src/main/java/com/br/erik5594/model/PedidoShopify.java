package com.br.erik5594.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "pedido_shopify")
public @Data class PedidoShopify implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "numero_pedido")
    private int numeroPedido;

    @Column(name = "id_pedido_shopify")
    private String idPedido;

    @Column(name = "data_pedido")
    @Temporal(TemporalType.DATE)
    private Date dataPedido;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id.pedidoShopify", fetch = FetchType.LAZY)
    private List<Item> itens;

    @Column(name = "valor_total")
    private float valorTotal;

    private boolean enviado;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_cancelamento")
    private Date dataCancelamento;

    @Override
    public boolean equals(Object o) {
        if(this == null && o == null) return true;
        if(this != null && o == null) return false;
        if(this == null && o != null) return false;
        PedidoShopify pedido = (PedidoShopify) o;
        return numeroPedido == pedido.numeroPedido;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numeroPedido);
    }

    public Date getDataPedido(){
        if(dataPedido == null){
            return null;
        }
        return new Date(dataPedido.getTime());
    }

    public void setDataPedido(Date dataPedido){
        if(dataPedido == null){
            this.dataPedido = null;
        }else {
            this.dataPedido = new Date(dataPedido.getTime());
        }
    }
}
