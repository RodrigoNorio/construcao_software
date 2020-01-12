package com.example.trabalhocs.Model;

public class ModeloDestino {

    int coddestino;
    String descricao;
    int cod_pessoa;

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

    public int getCodpessoa() {
        return cod_pessoa;
    }

    public void setCod_pessoa(int cod_pessoa) {
        this.cod_pessoa = cod_pessoa;
    }

    @Override
    public String toString() {
        return "ModeloDestino{" +
                "coddestino=" + coddestino +
                "descricao=" + descricao +
                ", cod_pessoa='" + cod_pessoa + '\'' +
                '}';
    }

}
