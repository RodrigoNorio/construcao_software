package com.example.trabalhocs.Model;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.trabalhocs.Utils.Utilidades;

public class ModeloRecurso {

    private int id_recurso;
    private String nome;
    private String descricao;
    private int tipoMedida;
    private int inventario;

    public ModeloRecurso(int id_recurso, String nome, String descricao, int tipoMedida, int inventario) {
        this.id_recurso = id_recurso;
        this.nome = nome;
        this.descricao = descricao;
        this.tipoMedida = tipoMedida;
        this.inventario = inventario;
    }

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

    public String getTextoEstoqueAbrev(Context context) {
        return inventario + " " + Utilidades.getMedidaAbrev(context, tipoMedida);
    }

    public String getTextoEstoque(Context context) {
        return inventario + " " + Utilidades.getMedidaText(context, tipoMedida);
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

    public int getTipoMedida() {
        return tipoMedida;
    }
}
