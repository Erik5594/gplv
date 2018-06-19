package com.br.erik5594.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity(name = "pedido_shopify")
public @Data class PedidoShopify implements Serializable{
    @Id
    @Column(name = "numero_pedido")
    private int numeroPedido;

    @Column(name = "id_pedido_shopify")
    private String idPedido;

    @Column(name = "data_pedido")
    @Temporal(TemporalType.DATE)
    private Date dataPedido;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidoShopify")
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
        return new Date(dataPedido.getTime());
    }

    public void setDataPedido(Date dataPedido){
        this.dataPedido = new Date(dataPedido.getTime());
    }
}
