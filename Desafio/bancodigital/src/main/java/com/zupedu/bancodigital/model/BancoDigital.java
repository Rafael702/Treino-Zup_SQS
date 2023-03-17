package com.zupedu.bancodigital.model;

import com.zupedu.bancodigital.model.enums.TipoPagamento;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BancoDigital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valor;
    private Integer numeroConta;
    private Integer agenciaConta;
    private String origem;

    private TipoPagamento tipoPagamento;

    public BancoDigital() {
    }

    public BancoDigital(Long id, Double valor) {
        this.id = id;
        this.valor = valor;
    }

    public BancoDigital(Long id, Double valor, Integer numeroConta, Integer agenciaConta, String origem, TipoPagamento tipoPagamento) {
        this.id = id;
        this.valor = valor;
        this.numeroConta = numeroConta;
        this.agenciaConta = agenciaConta;
        this.origem = origem;
        this.tipoPagamento = tipoPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
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

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

}
