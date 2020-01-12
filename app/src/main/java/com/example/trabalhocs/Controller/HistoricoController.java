package com.example.trabalhocs.Controller;

import android.content.Context;

import com.example.trabalhocs.Model.ModeloCompra;
import com.example.trabalhocs.Model.ModeloVenda;

import java.util.ArrayList;
import java.util.List;

public class HistoricoController {

    private  List<ModeloVenda> vendasList;
    private  List<ModeloCompra> comprasList;

    private static HistoricoController historicoController;

    public static HistoricoController getInstance() {
        return  historicoController;
    }

    public HistoricoController() {
        vendasList = new ArrayList<>();
        comprasList = new ArrayList<>();

        historicoController = this;
    }

    public boolean isVendasListEmpty() {
        return vendasList.isEmpty();
    }

    public boolean isComprasListEmpty() {
        return comprasList.isEmpty();
    }

    public void setVendasList(Context context) {
        List<ModeloVenda> vendas = ModeloVenda.listAll(ModeloVenda.class);

        for (ModeloVenda venda : vendas) {
            venda.configuraListaProdutos(context);
        }

        this.vendasList.addAll(vendas);
    }

    public void updateComprasList(Context context) {
        List<ModeloCompra> compras = ModeloCompra.listAll(ModeloCompra.class);

        for (ModeloCompra compra : compras) {
            compra.configuraListaCompra(context);
        }

        this.comprasList = compras;
    }

    public List<ModeloVenda> getVendasList() {
        return vendasList;
    }

    public List<ModeloCompra> getComprasList() {
        return comprasList;
    }
}
