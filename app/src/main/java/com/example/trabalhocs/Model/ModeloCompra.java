package com.example.trabalhocs.Model;

import android.content.Context;

import com.example.trabalhocs.Utils.Torradeira;
import com.example.trabalhocs.Utils.Utilidades;
import com.example.trabalhocs.View.Itens.RecursoCompraItemView;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModeloCompra extends SugarRecord {
    @Ignore
    private List<RecursoCompraItemView> listaCompra;

    private String recursosIds;
    private String quantidadesIngredientes;
    private String valores;
    private String dateTime;
    private double valorCompra;

    public ModeloCompra() {
    }

    public ModeloCompra(String dateTime, List<RecursoCompraItemView> listaCompra) {
        this.dateTime = dateTime;
        this.listaCompra = listaCompra;

        double valorCompra = 0;
        String valores = "";
        String recursosIds = "";
        String quantidadesIngredientes = "";

        try {

            for (RecursoCompraItemView recurso : listaCompra) {
                valorCompra += recurso.getValor();
                valores = valores.concat(recurso.getValor() + " ");
                recursosIds = recursosIds.concat(recurso.getRecurso().getId() + " ");
                quantidadesIngredientes = quantidadesIngredientes.concat(recurso.getQuantidade() + " ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.valorCompra = valorCompra;
        this.valores = valores;
        this.recursosIds = recursosIds;
        this.quantidadesIngredientes = quantidadesIngredientes;
    }

    public void configuraListaCompra(Context context) {
        try {
            List<String> recursosIdsList = Arrays.asList(recursosIds.split(" "));
            List<String> quantidadesList = Arrays.asList(quantidadesIngredientes.split(" "));
            List<String> valoresList = Arrays.asList(valores.split(" "));

            listaCompra = new ArrayList<>();

            for (int i = 0; i < recursosIdsList.size(); i++) {
                ModeloRecurso recurso = ModeloRecurso.findById(ModeloRecurso.class, Long.parseLong(recursosIdsList.get(i)));

                listaCompra.add(new RecursoCompraItemView(recurso, Integer.parseInt(quantidadesList.get(i)), Double.parseDouble(valoresList.get(i))));
            }

        } catch (Exception e) {
            e.printStackTrace();
            Torradeira.erroToast(context);
        }
    }

    public String getTextoListaCompra(Context context) {
        String textoListaCompra = "";

        for (RecursoCompraItemView recurso : listaCompra) {
            textoListaCompra = textoListaCompra + recurso.getTextoHistoricoCompra(context);
        }

        return textoListaCompra;
    }

    public String getValorTexto() {
        return Utilidades.formataReais(valorCompra);
    }

    public String getDateTime() {
        return dateTime;
    }

    public List<RecursoCompraItemView> getListaCompra() {
        return listaCompra;
    }
}
