package com.zupedu.bancodigital.rest.dto;

import com.sun.istack.NotNull;
import com.zupedu.bancodigital.model.enums.TipoPagamento;

public class BancoUpdateDTO {
    @NotNull
    private Double valor;

    @NotNull
    private TipoPagamento tipoPagamento;

    private String origem = null;

    public Double getValor() {
        return valor;
    }

    public String getOrigem() {
        return origem;
    }
    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }
}
