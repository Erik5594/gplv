package com.br.erik5594.conversores;

import com.br.erik5594.model.StatusPedidoAliexpress;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.Objects;

@FacesConverter(forClass = StatusPedidoAliexpress.class, value = "statusPedidoAliexpressConverter")
public class StatusPedidoAliexpressConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s != null){
            StatusPedidoAliexpress[] statusPedidoAliexpresses = StatusPedidoAliexpress.values();
            for(int x = 0; x < statusPedidoAliexpresses.length; x++){
                if(Objects.equals(statusPedidoAliexpresses[x].getDescricao().toLowerCase(), s.toLowerCase())){
                    return statusPedidoAliexpresses[x];
                }
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if(o != null){
            return ((StatusPedidoAliexpress)o).getDescricao();
        }
        return null;
    }
}
