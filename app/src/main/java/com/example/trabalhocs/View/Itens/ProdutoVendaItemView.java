package com.example.trabalhocs.View.Itens;

import com.example.trabalhocs.Model.ModeloProduto;

public class ProdutoVendaItemView {

    private ModeloProduto produto;
    private int quantidade;

    public ProdutoVendaItemView(ModeloProduto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public String getNomeProduto() {
        return produto.getNome();
    }

    public int getIdProduto() {
        return produto.getId_produto();
    }

    public double getValorVenda() {
        return produto.getValorUnitario() * quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
