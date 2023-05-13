package com.zupedu.livraria.notificacao;

public class Notificacao {

    private String email;
    private String tipoDeNotificacao;
    private String titulo;
    private String mensagem;

    public Notificacao(String email, String tipoDeNotificacao, String titulo, String mensagem) {
        this.email = email;
        this.tipoDeNotificacao = tipoDeNotificacao;
        this.titulo = titulo;
        this.mensagem = mensagem;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoDeNotificacao() {
        return tipoDeNotificacao;
    }

    public void setTipoDeNotificacao(String tipoDeNotificacao) {
        this.tipoDeNotificacao = tipoDeNotificacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String toString() {
        return "Notificacao{" +
                ", tipo='" + tipoDeNotificacao + '\'' +
                ", titulo='" + titulo + '\'' +
                ", mensagem='" + mensagem + '\'' +
                '}';
    }
}
