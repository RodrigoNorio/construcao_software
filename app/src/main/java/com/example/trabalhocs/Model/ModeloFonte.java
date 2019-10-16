package com.example.trabalhocs.Model;

public class ModeloFonte {

    int codfonte;
    String descricao;

    public ModeloFonte() {
    }

    public int getCodfonte() {
        return codfonte;
    }

    public void setCodfonte(int codfonte) {
        this.codfonte = codfonte;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "ModeloFonte{" +
                "codfonte=" + codfonte +
                ", descricao='" + descricao + '\'' +
                '}';
    }

}
