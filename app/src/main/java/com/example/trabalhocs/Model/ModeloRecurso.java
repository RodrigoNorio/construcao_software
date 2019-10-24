package com.example.trabalhocs.Model;

import androidx.annotation.NonNull;

public class ModeloRecurso {

    private int id_recurso;
    private String nome;
    private String descricao;
    private int inventario;

    public ModeloRecurso() {
    }

    @NonNull
    @Override
    public String toString() {
        String retorno = "";

        retorno += "Recurso # " + id_recurso + " " + nome + "\n";
        retorno += "Em inventário: " + inventario;

        return retorno;
    }

    public int getId_recurso() {
        return id_recurso;
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
}
