package com.example.trabalhocs.Model;

public class ModeloDestino {

    int coddestino;
    String descricao;

    public ModeloDestino() {
    }

    public int getCoddestino() {
        return coddestino;
    }

    public void setCoddestino(int coddestino) {
        this.coddestino = coddestino;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "ModeloDestino{" +
                "coddestino=" + coddestino +
                ", descricao='" + descricao + '\'' +
                '}';
    }

}
