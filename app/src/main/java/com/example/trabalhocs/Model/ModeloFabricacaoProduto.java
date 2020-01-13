package com.example.trabalhocs.Model;

import android.content.Context;

import com.example.trabalhocs.Utils.Torradeira;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModeloFabricacaoProduto extends SugarRecord {

    @Ignore
    private Map<ModeloRecurso, Integer> mapIngredientes = null;

    private String recursosIds;
    private String quantidadesIngredientes;

    private ModeloProduto produto;
    private int quantidade;

    public ModeloFabricacaoProduto() {
    }

    public ModeloFabricacaoProduto(ModeloProduto produto, Map<ModeloRecurso, Integer> mapIngredientes, int quantidade) {
        this.produto = produto;
        this.mapIngredientes = mapIngredientes;
        this.quantidade = quantidade;

        recursosIds = "";
        quantidadesIngredientes = "";

        try {
            for (Map.Entry<ModeloRecurso, Integer> entrada : mapIngredientes.entrySet()) {
                recursosIds = recursosIds.concat(entrada.getKey().getId() + " ");
                quantidadesIngredientes = quantidadesIngredientes.concat(entrada.getValue() + " ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void configuraMapaIngredientes(Context context) {
        try {
            List<String> recursosIdsList = Arrays.asList(recursosIds.split(" "));
            List<String> quantidadesList = Arrays.asList(quantidadesIngredientes.split(" "));

            List<ModeloRecurso> ingredientes = new ArrayList<>();

            for (String id : recursosIdsList) {
                ingredientes.add(ModeloRecurso.findById(ModeloRecurso.class, Long.parseLong(id)));
            }

            mapIngredientes = new HashMap<>();

            for (int i = 0; i < ingredientes.size(); i++) {
                mapIngredientes.put(ingredientes.get(i), Integer.valueOf(quantidadesList.get(i)));
            }

        } catch (Exception e) {
            e.printStackTrace();
            Torradeira.erroToast(context);
        }
    }
    
    public String getListaIngredientesSimplificada() {
        String lista = "";

        try {
            for (Map.Entry<ModeloRecurso, Integer> entrada : mapIngredientes.entrySet()) {
                lista = lista.concat(entrada.getKey().getNome() + ", ");
            }

            lista = lista.substring(0, lista.length() - 2);

        } catch (Exception e) {
            e.printStackTrace();
            lista = "erro!";
        }

        return lista;
    }

    public ModeloProduto getProduto() {
        return produto;
    }

    public Map<ModeloRecurso, Integer> getMapIngredientes() {
        return mapIngredientes;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public boolean testeModeloFabricacaoProdutoValido() {
        if (this.getMapIngredientes() == null) return false;

        for (Map.Entry<ModeloRecurso, Integer> entrada : this.getMapIngredientes().entrySet()) {
            if (!entrada.getKey().testeRecursoValido()) return  false;
        }

        if (this.getProduto() == null || !this.getProduto().testeProdutoValido()) return false;

        if (this.getQuantidade() < 0) return false;

        if (recursosIds.isEmpty() || quantidadesIngredientes.isEmpty()) return false;

        return  true;
    }
}
