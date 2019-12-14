package com.example.trabalhocs.View.Itens;

import android.content.Context;

import com.example.trabalhocs.Model.ModeloRecurso;
import com.example.trabalhocs.Utils.Utilidades;

public class RecursoCompraItemView {

    private ModeloRecurso recurso;
    private int quantidade;
    private double valor;

    public RecursoCompraItemView(ModeloRecurso recurso, int quantidade, double valor) {
        this.recurso = recurso;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public String getQuantidadeText(Context context) {
        return quantidade + " " +  Utilidades.getMedidaAbrev(context, recurso.getTipoMedida());
    }

    public String getValorText() {
        return Utilidades.formataReais(valor);
    }

    public ModeloRecurso getRecurso() {
        return recurso;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValor() {
        return valor;
    }
}
