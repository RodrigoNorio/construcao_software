package com.example.trabalhocs.Controller;

import com.example.trabalhocs.Model.ModeloFabricacaoProduto;

import java.util.List;

public class ModeloFabricacaoController {

    private List<ModeloFabricacaoProduto> modelos;

    public ModeloFabricacaoController(List<ModeloFabricacaoProduto> modelos) {
        this.modelos = modelos;
    }

    public List<ModeloFabricacaoProduto> getModelos() {
        return modelos;
    }
}
