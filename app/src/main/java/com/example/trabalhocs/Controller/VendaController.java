package com.example.trabalhocs.Controller;

import android.util.Log;
import android.util.SparseArray;

import com.example.trabalhocs.Model.ModeloProduto;
import com.example.trabalhocs.View.Itens.ProdutoVendaItemView;

import java.util.ArrayList;
import java.util.List;

public class VendaController {

    private VendaControllerListener listener;
    private List<ModeloProduto> produtosList;
    private List<ProdutoVendaItemView> selecionadosList;
    private SparseArray<ModeloProduto> produtosMapa;
    private SparseArray<ProdutoVendaItemView> produtosViews;

    private double total = 0.0;

    public VendaController(VendaControllerListener listener, List<ModeloProduto> produtos) {
        this.listener = listener;
        this.produtosList = produtos;
        produtosMapa = new SparseArray<>(produtos.size());
        produtosViews = new SparseArray<>(produtos.size());
        selecionadosList = new ArrayList<>();

        for (ModeloProduto p: produtos) {
            produtosMapa.append(p.getId(), p);
            produtosViews.append(p.getId(), new ProdutoVendaItemView(p, 0));
        }
    }

    public List<ProdutoVendaItemView> getProdutosSelecionadosView() {
        return  selecionadosList;
    }
    
    public void updateMap(int produtoId, int qtd) {
        ProdutoVendaItemView pView = produtosViews.get(produtoId);
        pView.setQuantidade(qtd);
        produtosViews.put(produtoId, pView);
        Log.d("butterfree", "updateMap: " + pView.toString());

        total = 0.0;

        selecionadosList = new ArrayList<>();

        for (int i = 0; i < produtosViews.size(); i++) {
            ProdutoVendaItemView p = produtosViews.valueAt(i);
            if (p.getQuantidade() > 0) selecionadosList.add(p);
        }

        for (ProdutoVendaItemView p : selecionadosList) {
            total += p.getValorVenda();
        }

        listener.atualizaLista(total);
    }

    public List<ModeloProduto> getProdutosList() {
        return produtosList;
    }

    public double getTotal() {
        return total;
    }

    public interface VendaControllerListener {
        void atualizaLista(double total);
    }
}
