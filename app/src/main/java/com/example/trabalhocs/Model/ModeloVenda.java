package com.example.trabalhocs.Model;

import com.example.trabalhocs.Utils.Utilidades;
import com.example.trabalhocs.View.Itens.ProdutoVendaItemView;

import java.util.List;

public class ModeloVenda {

    private int id;
    private String dateTime;
    private List<ProdutoVendaItemView> listaProdutos;
    private double total;

    public ModeloVenda(int id, String dateTime, List<ProdutoVendaItemView> listaProdutos) {
        this.id = id;
        this.dateTime = dateTime;
        this.listaProdutos = listaProdutos;

        double total = 0;

        for (ProdutoVendaItemView produto: listaProdutos) {
            total += produto.getValorVenda();
        }

        this.total = total;
    }

    public String getTextoListaProdutos() {
        String textoListaProdutos = "";

        for (ProdutoVendaItemView produto: listaProdutos) {
            textoListaProdutos = textoListaProdutos + produto.getTextoHistoricoVenda();
        }

        return textoListaProdutos;
    }

    public String getTextoValorVenda() {
        return Utilidades.formataReais(total);
    }

    public String getTextoQuantidadeProdutos() {
        int total = 0;

        for (ProdutoVendaItemView produto: listaProdutos) {
            total += produto.getQuantidade();
        }

        return "Total de " + total + " itens";
    }

    public int getId() {
        return id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public List<ProdutoVendaItemView> getListaProdutos() {
        return listaProdutos;
    }

    public double getTotal() {
        return total;
    }
}
