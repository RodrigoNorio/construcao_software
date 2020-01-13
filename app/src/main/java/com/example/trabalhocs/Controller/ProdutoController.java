package com.example.trabalhocs.Controller;

import android.util.SparseArray;

import com.example.trabalhocs.Model.ModeloFabricacaoProduto;
import com.example.trabalhocs.Model.ModeloProduto;
import com.example.trabalhocs.View.Itens.ProdutoVendaItemView;

import java.util.ArrayList;
import java.util.List;

public class ProdutoController {

    private VendaControllerListener vendaListener;
    private CadastrarModeloListener cadastroModeloListener;

    private List<ModeloProduto> produtosList;
    private List<ProdutoVendaItemView> selecionadosList;
    private SparseArray<ModeloProduto> produtosMapa;
    private SparseArray<ProdutoVendaItemView> produtosViews;

    private double total = 0.0;

    /**
     * construtor para o inventário
     */
    public ProdutoController() {
        this.produtosList = ModeloProduto.listAll(ModeloProduto.class);
    }

    /**
     * construtor cadastro de modelo de produto
     */
    public ProdutoController(CadastrarModeloListener cadastroModeloListener) {
        this.cadastroModeloListener = cadastroModeloListener;
        this.produtosList = ModeloProduto.listAll(ModeloProduto.class);
    }

    /**
     * construtor para o registro de venda
     */
    public ProdutoController(VendaControllerListener vendaListener) {
        this.vendaListener = vendaListener;
        this.produtosList = ModeloProduto.listAll(ModeloProduto.class);
        produtosMapa = new SparseArray<>(produtosList.size());
        produtosViews = new SparseArray<>(produtosList.size());
        selecionadosList = new ArrayList<>();

        for (ModeloProduto p: produtosList) {
            produtosMapa.append(p.getId().intValue(), p);
            produtosViews.append(p.getId().intValue(), new ProdutoVendaItemView(p, 0));
        }
    }

    public List<ProdutoVendaItemView> getProdutosSelecionadosView() {
        return  selecionadosList;
    }
    
    public void updateMap(Long produtoId, int qtd) {
        ProdutoVendaItemView pView = produtosViews.get(produtoId.intValue());
        pView.setQuantidade(qtd);
        produtosViews.put(produtoId.intValue(), pView);

        total = 0.0;

        selecionadosList = new ArrayList<>();

        for (int i = 0; i < produtosViews.size(); i++) {
            ProdutoVendaItemView p = produtosViews.valueAt(i);
            if (p.getQuantidade() > 0) selecionadosList.add(p);
        }

        for (ProdutoVendaItemView p : selecionadosList) {
            total += p.getValorVenda();
        }

        vendaListener.atualizaLista(total);
    }

    public void efetivarVenda() {
        for (ProdutoVendaItemView item : selecionadosList) {
            ModeloProduto produto = ModeloProduto.findById(ModeloProduto.class, item.getIdProduto());
            produto.decrementarEstoque(item.getQuantidade());
            produto.save();
        }
    }

    public void efetivarFabricacaoProduto(ModeloFabricacaoProduto modeloFabricacaoProduto) {
        ModeloProduto produto = ModeloProduto.findById(ModeloProduto.class, modeloFabricacaoProduto.getProduto().getId());
        produto.incrementarEstoque(modeloFabricacaoProduto.getQuantidade());
        produto.save();
    }

    public boolean isProdutoListEmpty() {
        return produtosList.isEmpty();
    }

    public List<ModeloProduto> getProdutosList() {
        return produtosList;
    }

    public double getTotal() {
        return total;
    }

    public void setaProduto(ModeloProduto produto) {
        cadastroModeloListener.atualizaProduto(produto);
    }

    public interface VendaControllerListener {
        void atualizaLista(double total);
    }

    public interface CadastrarModeloListener {
        void atualizaProduto(ModeloProduto produto);
    }
}
