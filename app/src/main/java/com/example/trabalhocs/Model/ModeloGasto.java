package com.example.trabalhocs.Model;

public class ModeloGasto {

    int codgasto;
    float valor;
    String date;
    int cod_destino;
    int cod_pessoa;


    public int getCodgasto() {
        return codgasto;
    }

    public void setCodgasto(int codgasto) {
        this.codgasto = codgasto;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCod_destino() {
        return cod_destino;
    }

    public void setCod_destino(int cod_destino) {
        this.cod_destino = cod_destino;
    }


    @Override
    public String toString() {
        return "ModeloGasto{" +
                "codgasto=" + codgasto +
                ", gasto'" + valor +
                ", data='" + date +
                ", cod_destino='" + cod_destino +
                ", cod_pessoa='"+ cod_pessoa+ '\'' +
                '}';
    }






}
