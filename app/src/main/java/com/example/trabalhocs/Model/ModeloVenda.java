package com.example.trabalhocs.Model;

import android.content.Context;

import com.example.trabalhocs.Utils.Torradeira;
import com.example.trabalhocs.Utils.Utilidades;
import com.example.trabalhocs.View.Itens.ProdutoVendaItemView;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModeloVenda extends SugarRecord {
    @Ignore
    private List<ProdutoVendaItemView> listaProdutos;

    private String produtosIds;
    private String quantidadesIngredientes;
    private String dateTime;
    private double total;

    public ModeloVenda() {
    }

    public ModeloVenda(String dateTime, List<ProdutoVendaItemView> listaProdutos) {
        this.dateTime = dateTime;
        this.listaProdutos = listaProdutos;

        double total = 0;
        String produtosIds = "";
        String quantidadesIngredientes = "";

        try {
            for (ProdutoVendaItemView produto: listaProdutos) {
                total += produto.getValorVenda();
                produtosIds = produtosIds.concat(produto.getIdProduto() + " ");
                quantidadesIngredientes = quantidadesIngredientes.concat(produto.getQuantidade() + " ");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.total = total;
        this.produtosIds = produtosIds;
        this.quantidadesIngredientes = quantidadesIngredientes;
    }

    public void configuraListaProdutos(Context context) {
        try {
            List<String> produtosIdsList = Arrays.asList(produtosIds.split(" "));
            List<String> quantidadesList = Arrays.asList(quantidadesIngredientes.split(" "));

            listaProdutos = new ArrayList<>();

            for (int i = 0; i < produtosIdsList.size(); i++) {
                ModeloProduto produto = ModeloProduto.findById(ModeloProduto.class, Long.parseLong(produtosIdsList.get(i)));

                listaProdutos.add(new ProdutoVendaItemView(produto, Integer.parseInt(quantidadesList.get(i))));
            }

        } catch (Exception e) {
            e.printStackTrace();
            Torradeira.erroToast(context);
        }
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

    public String getDateTime() {
        return dateTime;
    }

    public List<ProdutoVendaItemView> getListaProdutos() {
        return listaProdutos;
    }

    public double getTotal() {
        return total;
    }

    public boolean testeVendaValida() {
        if (this.getListaProdutos() == null) return  false;

        for (ProdutoVendaItemView produto : this.getListaProdutos()) {
            if (!produto.getProduto().testeProdutoValido()) return false;
        }

        if (this.getDateTime().isEmpty()) return  false;
        if (this.total <= 0) return false;

        return  true;
    }
}
