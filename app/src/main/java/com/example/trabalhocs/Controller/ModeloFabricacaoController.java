package com.example.trabalhocs.Controller;

import android.content.Context;

import com.example.trabalhocs.Model.ModeloFabricacaoProduto;

import java.util.List;

public class ModeloFabricacaoController {

    private List<ModeloFabricacaoProduto> modelos;

    public ModeloFabricacaoController(Context context) {
        this.modelos = ModeloFabricacaoProduto.listAll(ModeloFabricacaoProduto.class);

        for (ModeloFabricacaoProduto modelo : modelos) {
            modelo.configuraMapaIngredientes(context);
        }
    }

    public boolean isListaModelosEmpty() {
        return  modelos.isEmpty();
    }

    public List<ModeloFabricacaoProduto> getModelos() {
        return modelos;
    }
}
