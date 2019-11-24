package com.example.trabalhocs.Model;

public class ModeloFonte {

    int codfonte;
    String descricao;
    int cod_pessoa;

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

    public int getCodpessoa() {
        return cod_pessoa;
    }

    public void setCod_pessoa(int cod_pessoa) {
        this.cod_pessoa = cod_pessoa;
    }

    @Override
    public String toString() {
        return "ModeloFonte{" +
                "codfonte=" + codfonte +
                "descricao=" + descricao +
                ", cod_pessoa='" + cod_pessoa + '\'' +
                '}';
    }

}
