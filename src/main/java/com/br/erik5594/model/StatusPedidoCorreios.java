package com.br.erik5594.model;

public enum StatusPedidoCorreios {
    INDEFINIDO(0,"Indefinido"),
    CORREIOS(1,"Está no Correios"),
    FISCALIZACAO(2,"Está na fiscalização"),
    DEVOLVIDO(3,"Foi devolvido ao remetente"),
    POSTADO(4, "Foi postado"),
    PENDENTE(5, "Está pendente"),
    AGUARDANDO_RETIRADA(6, "Aguardando a retirada no Correios"),
    ENTREGUE(7, "Foi entregue"),
    ATRASADO(8, "Está atrasado"),
    NAO_ENCONTRADO(9, "Não encontrado"),
    EXTRAVIADO(10, "Foi extraviado");

    private int codStatus;
    private String descricao;

    StatusPedidoCorreios(int codStatus, String descricao){
        this.codStatus = codStatus;
        this.descricao = descricao;
    }

    public int getCodStatus() {
        return codStatus;
    }

    public String getDescricao() {
        return descricao;
    }
}
