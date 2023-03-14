package com.zupedu.livraria.venda;

import com.zupedu.livraria.livro.Livro;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Livro livro;

    private LocalDateTime compradoEm;

    private BigDecimal valor;

    private String emailCliente;

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;

    public Venda() {
    }

    public Venda(Livro livro, BigDecimal valor, TipoPagamento tipoPagamento, String email) {
        this.livro = livro;
        this.valor = valor;
        this.tipoPagamento = tipoPagamento;
        this.compradoEm = LocalDateTime.now();
        this.emailCliente = email;
    }

    public Long getId() {
        return id;
    }

    public Livro getLivro() {
        return livro;
    }

    public LocalDateTime getCompradoEm() {
        return compradoEm;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public boolean realizaNotificacao() {
        return this.emailCliente != null || !this.emailCliente.isBlank();
    }

    public String getTituloNotificacao() {
        return "Aviso de compra de Livro";
    }

    public String getMensagemNotificacao() {
        return "Você comprou o Livro " + livro.getTitulo() +
                " , data: " + LocalDate.now() +
                " , codigo da compra: " + id +
                " , caso queira trocar, procure a loja mais próxima com esse e-mail";
    }
}
