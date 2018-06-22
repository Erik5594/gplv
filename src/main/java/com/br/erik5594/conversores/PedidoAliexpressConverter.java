package com.br.erik5594.conversores;

import com.br.erik5594.bo.PedidoAliexpressBo;
import com.br.erik5594.dto.PedidoAliexpressDto;
import com.br.erik5594.util.cdi.CDIServiceLocator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = PedidoAliexpressDto.class)
public class PedidoAliexpressConverter implements Converter{
    //@Inject
    private PedidoAliexpressBo pedidoAliexpressBo;

    public PedidoAliexpressConverter(){
        pedidoAliexpressBo = CDIServiceLocator.getBean(PedidoAliexpressBo.class);
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        PedidoAliexpressDto retorno = null;
        if(s != null){
            Long idPedido = new Long(s);
            retorno = pedidoAliexpressBo.getPedidoAliexpressById(idPedido);
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if(o != null){
            PedidoAliexpressDto pedidoAliexpressDto = (PedidoAliexpressDto) o;
            return pedidoAliexpressDto.getIdAliexpress() == null ? null:pedidoAliexpressDto.getIdAliexpress().toString();
        }
        return null;
    }
}
