package com.example.trabalhocs.View.Itens;

import androidx.annotation.NonNull;

import com.example.trabalhocs.Model.ModeloProduto;

public class ProdutoVendaItemView {

    private ModeloProduto produto;
    private int quantidade;

    public ProdutoVendaItemView(ModeloProduto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    @NonNull
    @Override
    public String toString() {
        String retorno = "";

        retorno += "Produto -> " + produto.getNome() + "\n";
        retorno += "Quantidade: " + quantidade + "\n";

        return retorno;
    }

    public String getNomeProduto() {
        return produto.getNome();
    }

    public int getIdProduto() {
        return produto.getId();
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
