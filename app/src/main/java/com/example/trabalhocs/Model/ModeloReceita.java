package com.example.trabalhocs.Model;

public class ModeloReceita {

    int codreceita;
    float valor;
    String date;
    int cod_fonte;
    int cod_pessoa;

    public int getCodreceita() {
        return codreceita;
    }

    public void setCodreceita(int codreceita) {
        this.codreceita = codreceita;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float receita) {
        this.valor = receita;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCodfonte() {
        return cod_fonte;
    }

    public void setCodfonte(int codfonte) {
        this.cod_fonte = codfonte;
    }

    public int getCodpessoa() {
        return cod_pessoa;
    }

    public void setCod_pessoa(int cod_pessoa) {
        this.cod_pessoa = cod_pessoa;
    }

    @Override
    public String toString() {
        return "ModeloReceita{" +
                "codreceita=" + codreceita +
                ", receita'" + valor +
                ", data='" + date +
                ", cod_fonte='" + cod_fonte +
                ", cod_pessoa='" + cod_pessoa + '\'' +
                '}';
    }
}
