package com.example.trabalhocs.Model;

import androidx.annotation.NonNull;

public class ModeloProduto {

    private int id_produto;
    private String nome;
    private String descricao;
    private int inventario;
    private double valorUnitario;

    public ModeloProduto() {
    }

    @NonNull
    @Override
    public String toString() {
        String retorno = "";

        retorno += "Produto # " + id_produto + " " + nome + "\n";
        retorno += "Em inventário: " + inventario + "\n";
        retorno += "Valor Unitário: R$" + valorUnitario;

        return retorno;
    }

    public int getId_produto() {
        return id_produto;
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

    public int getInventario() {
        return inventario;
    }

    public void setInventario(int inventario) {
        this.inventario = inventario;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
