package com.example.trabalhocs.Controller;

import com.example.trabalhocs.Model.ModeloRecurso;
import com.example.trabalhocs.View.Itens.RecursoCompraItemView;

import java.util.ArrayList;
import java.util.List;

public class EstoqueController {
    private CompraRecursosListener listener = null;
    private List<ModeloRecurso> recursoList;
    private List<RecursoCompraItemView> compraList;

    private static EstoqueController estoqueController;
    public static  EstoqueController getInstance() {
        return estoqueController;
    }

    public EstoqueController(List<ModeloRecurso> recursoList) {
        this.recursoList = recursoList;
        estoqueController = this;
    }

    public EstoqueController(CompraRecursosListener listener, List<ModeloRecurso> recursoList) {
        this.listener = listener;
        this.recursoList = recursoList;
        compraList = new ArrayList<>();
        estoqueController = this;
    }

    public void addRecursoCompra(RecursoCompraItemView recursoCompraItemView) {
        compraList.add(recursoCompraItemView);
        if (listener != null) listener.atualizaListaCompras();
    }

    public void removeRecursoCompra(RecursoCompraItemView recursoCompraItemView){
        compraList.remove(recursoCompraItemView);
        if (listener != null) listener.atualizaListaCompras();
    }

    public List<ModeloRecurso> getRecursoList() {
        return recursoList;
    }

    public List<RecursoCompraItemView> getCompraList() {
        return compraList;
    }

    public interface CompraRecursosListener {
        void atualizaListaCompras();
    }
}
