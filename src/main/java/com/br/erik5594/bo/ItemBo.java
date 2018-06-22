package com.br.erik5594.bo;

import com.br.erik5594.dao.ItemDao;
import com.br.erik5594.dao.PedidoAliexpressDao;
import com.br.erik5594.dao.PedidoShopifyDao;
import com.br.erik5594.dao.ProdutoDao;
import com.br.erik5594.model.Item;
import com.br.erik5594.model.PedidoAliexpress;
import com.br.erik5594.model.PedidoShopify;
import com.br.erik5594.model.Produto;

import javax.inject.Inject;
import java.math.BigDecimal;

public class ItemBo {
    @Inject
    private ItemDao itemDao;
    @Inject
    private PedidoShopifyDao pedidoShopifyDao;
    @Inject
    private ProdutoDao produtoDao;
    @Inject
    private PedidoAliexpressDao pedidoAliexpressDao;

    public void vincularPedidoAliexpress(int pedidoShopify, String skuProduto, Long idAliexpress){
        PedidoShopify pedido = pedidoShopifyDao.buscarPedidoShopify(pedidoShopify);
        Produto produto = produtoDao.buscarProduto(skuProduto);
        PedidoAliexpress pedidoAliexpress = pedidoAliexpressDao.buscarPedidoAliexpress(idAliexpress);
        Item item = itemDao.buscarItem(pedido, produto);
        if(item != null){
            item.setPedidoAliexpress(pedidoAliexpress);
            itemDao.editarItem(item);
        }
    }
}
