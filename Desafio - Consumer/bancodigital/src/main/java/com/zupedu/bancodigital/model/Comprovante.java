package com.zupedu.bancodigital.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Comprovante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private LocalDate data = LocalDate.now();

    public Comprovante(Long id, String descricao, LocalDate data) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
    }

    public Comprovante() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comprovante)) return false;
        Comprovante that = (Comprovante) o;
        return getId().equals(that.getId()) && getDescricao().equals(that.getDescricao()) && getData().equals(that.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescricao(), getData());
    }

    @Override
    public String toString() {
        return "Comprovante{" +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                '}';
    }
}
