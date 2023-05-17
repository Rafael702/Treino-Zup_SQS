package br.com.zup.edu.ingressoagora.service;

import br.com.zup.edu.ingressoagora.model.CategoriaEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class Notificacao {
    private String nome;

    private String email;

    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoria;

    private String nascimento;

    private String incluidoEm;

    private String atualizadoEm;

    public Notificacao() {
    }

    public Notificacao(String nome, String email, String nascimento, String incluidoEm, String atualizadoEmatualizadoEm, CategoriaEnum categoria) {
        this.nome = nome;
        this.email = email;
        this.nascimento = nascimento;
        this.incluidoEm = incluidoEm;
        this.atualizadoEm = atualizadoEmatualizadoEm;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getNascimento() {
        return nascimento;
    }

    public String getIncluidoEm() {
        return incluidoEm;
    }

    public String getAtualizadoEm() {
        return atualizadoEm;
    }

    public CategoriaEnum getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Notificacao{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", categoria=" + categoria +
                ", nascimento=" + nascimento +
                ", incluidoEm=" + incluidoEm +
                ", atualizadoEm=" + atualizadoEm +
                '}';
    }
}
