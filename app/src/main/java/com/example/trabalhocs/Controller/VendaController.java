package com.example.trabalhocs.Controller;

import android.util.Log;
import android.util.SparseArray;

import com.example.trabalhocs.Model.ModeloProduto;
import com.example.trabalhocs.View.Itens.ProdutoVendaItemView;

import java.util.ArrayList;
import java.util.List;

public class VendaController {

    List<ModeloProduto> produtosList;
    SparseArray<ModeloProduto> produtosMapa;
    SparseArray<ProdutoVendaItemView> produtosViews;

    public VendaController(List<ModeloProduto> produtos) {
        produtosList = produtos;
        produtosMapa = new SparseArray<>(produtos.size());
        produtosViews = new SparseArray<>(produtos.size());

        for (ModeloProduto p: produtos) {
            produtosMapa.append(p.getId(), p);
            produtosViews.append(p.getId(), new ProdutoVendaItemView(p, 0));
        }
    }

    public List<ProdutoVendaItemView> getProdutosSelecionadosView() {
        List<ProdutoVendaItemView> selecionados = new ArrayList<>();

        for (int i = 0, tam = produtosViews.size(); i < tam; i++) {
            ProdutoVendaItemView p = produtosViews.valueAt(i);
            if (p.getQuantidade() > 0) selecionados.add(p);
        }

        return  selecionados;
    }
    
    public void updateMap(int produtoId, int qtd) {
        ProdutoVendaItemView pView = produtosViews.get(produtoId);
        pView.setQuantidade(qtd);
        produtosViews.put(produtoId, pView);
        Log.d("butterfree", "updateMap: " + pView.toString());
    }

    public List<ModeloProduto> getProdutosList() {
        return produtosList;
    }
}
