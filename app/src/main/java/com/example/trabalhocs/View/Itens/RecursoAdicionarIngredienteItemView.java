package com.example.trabalhocs.View.Itens;

import android.content.Context;

import com.example.trabalhocs.Model.ModeloRecurso;
import com.example.trabalhocs.Utils.Utilidades;

public class RecursoAdicionarIngredienteItemView {

    private ModeloRecurso recurso;
    private int quantidade;

    public RecursoAdicionarIngredienteItemView(ModeloRecurso recurso, int quantidade) {
        this.recurso = recurso;
        this.quantidade = quantidade;
    }

    public String getQuantidadeText(Context context) {
        return quantidade + " " +  Utilidades.getMedidaAbrev(context, recurso.getTipoMedida());
    }

    public ModeloRecurso getRecurso() {
        return recurso;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
