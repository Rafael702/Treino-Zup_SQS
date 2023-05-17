package com.zupedu.bancodigital.infra.sqs.model;

public class Notificacao {
    private Double valor;
    private String tipoPagamento;
    private Integer numeroConta;
    private Integer agenciaConta;

    private String origem;

    public Notificacao(Double valor, String tipoDeNotificacao, Integer numeroConta, Integer agenciaConta, String origem) {
        this.valor = valor;
        this.tipoPagamento = tipoDeNotificacao;
        this.numeroConta = numeroConta;
        this.agenciaConta = agenciaConta;
        this.origem = origem;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Integer getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Integer numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Integer getAgenciaConta() {
        return agenciaConta;
    }

    public void setAgenciaConta(Integer agenciaConta) {
        this.agenciaConta = agenciaConta;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }
}
