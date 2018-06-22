package com.br.erik5594.conversores;

import com.br.erik5594.bo.PedidoShopifyBo;
import com.br.erik5594.dto.PedidoShopifyDto;
import com.br.erik5594.util.cdi.CDIServiceLocator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = PedidoShopifyDto.class)
public class PedidoShopifyConverter implements Converter{

    //@Inject
    private PedidoShopifyBo pedidoShopifyBo;

    public PedidoShopifyConverter(){
        pedidoShopifyBo = CDIServiceLocator.getBean(PedidoShopifyBo.class);
    }


    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        PedidoShopifyDto retorno = null;
        if(s != null){
            int numeroPedido = Integer.parseInt(s);
            retorno = pedidoShopifyBo.getPedidoShopifyByNumeroPedido(numeroPedido);
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if(o != null){
            PedidoShopifyDto pedidoShopifyDto = (PedidoShopifyDto) o;
            return String.valueOf(pedidoShopifyDto.getNumeroPedido());
        }
        return null;
    }
}
