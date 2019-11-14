package com.example.trabalhocs.Controller;

import com.example.trabalhocs.Model.ModeloProduto;
import com.example.trabalhocs.View.Itens.ProdutoVendaItemView;

import java.util.ArrayList;
import java.util.List;

public class VendaController {

    List<ModeloProduto> produtos;
    List<ProdutoVendaItemView> produtosViews;

    public VendaController(List<ModeloProduto> produtos) {
        this.produtos = produtos;

        produtosViews = new ArrayList<>();

        for (ModeloProduto p: produtos) {
            produtosViews.add(new ProdutoVendaItemView(p, 0));
        }
    }

    public List<ModeloProduto> getProdutos() {
        return produtos;
    }

    public List<ProdutoVendaItemView> getProdutosViews() {
        return produtosViews;
    }
}
