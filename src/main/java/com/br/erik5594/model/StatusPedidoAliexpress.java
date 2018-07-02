package com.br.erik5594.model;

public enum StatusPedidoAliexpress {
    NORMAL(0, "Normal"), DISPUTA(1, "Disputa"), DISPUTA_NEGADA(2, "Disputa Negada"), REEMBOLSADO(3, "Reembolsado"), DISPUTA_CANCELADA(4, "Disputa Cancelada"), CONCLUIDO(5, "Concluído");

    private int codStatus;
    private String descricao;

    StatusPedidoAliexpress(int codStatus, String descricao){
        this.codStatus = codStatus;
        this.descricao = descricao;
    }

    public String getDescricao(){
        return this.descricao;
    }
    
    public int getCodStatus(){
        return this.codStatus;
    }

}
