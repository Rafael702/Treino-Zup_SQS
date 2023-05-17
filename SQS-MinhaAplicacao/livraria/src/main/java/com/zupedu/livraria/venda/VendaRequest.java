package com.zupedu.livraria.venda;

import com.zupedu.livraria.estoque.Estoque;
import com.zupedu.livraria.livro.Livro;

public class VendaRequest {

    private Long idLivro;

    private TipoPagamento tipoPagamento;

    private String emailCliente;

    public VendaRequest() {
    }

    public Venda toModel(Livro livro, Estoque estoque) {
        if (estoque.estaVazio()) {
            throw new ImpossibilidadeVendaException();
        }

        return new Venda(livro, estoque.getValorUnitario(), this.tipoPagamento, this.emailCliente);
    }

    public void setIdLivro(Long idLivro) {
        this.idLivro = idLivro;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Long getIdLivro() {
        return idLivro;
    }

    public String getEmailCliente() {
        return emailCliente;
    }
}
