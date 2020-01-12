package com.example.trabalhocs.Model;

import androidx.annotation.NonNull;

import com.example.trabalhocs.Utils.Utilidades;
import com.orm.SugarRecord;

public class ModeloProduto extends SugarRecord {

    private String nome;
    private String descricao;
    private int estoque;
    private double valorUnitario;

    public ModeloProduto() {
    }

    public ModeloProduto(String nome, String descricao, int estoque, double valorUnitario) {
        this.nome = nome;
        this.descricao = descricao;
        this.estoque = estoque;
        this.valorUnitario = valorUnitario;
    }

    @NonNull
    @Override
    public String toString() {
        String retorno = "";

        retorno += "Produto # " + getId()  + " " + nome + "\n";
        retorno += "Estoque: " + estoque + "\n";
        retorno += "Valor Unitário: R$" + valorUnitario;

        return retorno;
    }

    public String getValorUnitarioText() {
        return Utilidades.formataReais(valorUnitario);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
