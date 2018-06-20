package com.br.erik5594.util;

import com.br.erik5594.model.Cliente;
import com.br.erik5594.model.Item;
import com.br.erik5594.model.PedidoShopify;
import com.br.erik5594.model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestePedido {
    public static void main(String[] args){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidoPU");
        EntityManager manager = factory.createEntityManager();

        EntityTransaction trx = manager.getTransaction();
        trx.begin();

        Produto produto = new Produto();
        produto.setSkuProduto("123456");
        produto.setNomeProduto("Cinta");
        produto.setVarianteProduto("M");

        Produto produto1 = new Produto();
        produto1.setSkuProduto("123457");
        produto1.setNomeProduto("Camisa");
        produto1.setVarianteProduto("P");

        manager.persist(produto);
        manager.persist(produto1);

        Cliente cliente = new Cliente();
        cliente.setTelefone("62985023781");
        cliente.setEmail("teste@teste.com");
        cliente.setCep("74840250");
        cliente.setEstado("GO");
        cliente.setCidade("Goiania");
        cliente.setComplemento("Qd39 Lt08");
        cliente.setLogradouro("Rua Pocema - Parque Amazonia");
        cliente.setCpf("03554424188");
        cliente.setSobreNome("Queiroz");
        cliente.setPrimeiroNome("Erik");

        manager.persist(cliente);

        PedidoShopify pedido = new PedidoShopify();

        pedido.setDataPedido(new Date());
        pedido.setNumeroPedido(5586);
        pedido.setEnviado(true);
        pedido.setValorTotal(119.8f);
        pedido.setDataCancelamento(null);

        pedido.setCliente(cliente);

        List<Item> itens = new ArrayList<>();

        Item item1 = new Item();
        item1.setPedidoAliexpress(null);
        item1.setPedidoShopify(pedido);
        item1.setQuantidadeProduto(1);
        item1.setProduto(produto);



        Item item2 = new Item();
        item2.setPedidoAliexpress(null);
        item2.setPedidoShopify(pedido);
        item2.setQuantidadeProduto(1);
        item2.setProduto(produto1);

        itens.add(item1);
        itens.add(item2);
        pedido.setItens(itens);

        manager.persist(pedido);
        trx.commit();
        System.out.println("TesteCliente realizado");
    }
}
