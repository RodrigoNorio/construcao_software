package com.example.trabalhocs.Controller;

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

    public void setVendasList(List<ModeloVenda> vendasList) {
        this.vendasList.addAll(vendasList);
    }

    public void setComprasList(List<ModeloCompra> comprasList) {
        this.comprasList.addAll(comprasList);
    }

    public List<ModeloVenda> getVendasList() {
        return vendasList;
    }

    public List<ModeloCompra> getComprasList() {
        return comprasList;
    }
}
