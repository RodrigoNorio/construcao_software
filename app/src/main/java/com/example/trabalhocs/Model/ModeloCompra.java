package com.example.trabalhocs.Model;

import android.content.Context;

import com.example.trabalhocs.Utils.Utilidades;
import com.example.trabalhocs.View.Itens.RecursoCompraItemView;

import java.util.List;

public class ModeloCompra {

    private int id;
    private String dateTime;
    private List<RecursoCompraItemView> listaCompra;
    private double valor;

    public ModeloCompra(int id, String dateTime, List<RecursoCompraItemView> listaCompra, double valor) {
        this.id = id;
        this.dateTime = dateTime;
        this.listaCompra = listaCompra;
        this.valor = valor;
    }

    public String getTextoListaCompra(Context context) {
        String textoListaCompra = "";

        for (RecursoCompraItemView recurso : listaCompra) {
            textoListaCompra = textoListaCompra + recurso.getTextoHistoricoCompra(context);
        }

        return textoListaCompra;
    }

    public String getValorTexto() {
        return Utilidades.formataReais(valor);
    }

    public int getId() {
        return id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public List<RecursoCompraItemView> getListaCompra() {
        return listaCompra;
    }
}
