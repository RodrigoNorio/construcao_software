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

    public List<ProdutoVendaItemView> getProdutosSelecionadosView() {
        List<ProdutoVendaItemView> selecionados = new ArrayList<>();

        for (ProdutoVendaItemView p : produtosViews) {
            if (p.getQuantidade() > 0) selecionados.add(p);
        }

        return  selecionados;
    }
    
    public void updateLista(ModeloProduto p, int qtd) {
        int index = 0;
        
        for (ProdutoVendaItemView pView : produtosViews) {
            if (pView.getIdProduto() == p.getId_produto()) {
                break; // TODO: 14/11/2019 AQUIIII 
            }
            
            index++;
        }
    }

    public List<ModeloProduto> getProdutos() {
        return produtos;
    }

    public List<ProdutoVendaItemView> getProdutosViews() {
        return produtosViews;
    }
}
