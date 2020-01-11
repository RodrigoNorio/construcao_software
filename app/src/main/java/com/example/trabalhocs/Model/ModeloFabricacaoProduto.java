package com.example.trabalhocs.Model;

import java.util.Map;

public class ModeloFabricacaoProduto {

    private int id;
    private ModeloProduto produto;
    private Map<ModeloRecurso, Integer> mapIngredientes;
    private int quantidade;

    public ModeloFabricacaoProduto(int id, ModeloProduto produto, Map<ModeloRecurso, Integer> mapIngredientes, int quantidade) {
        this.id = id;
        this.produto = produto;
        this.mapIngredientes = mapIngredientes;
        this.quantidade = quantidade;
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

    public int getId() {
        return id;
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
}
