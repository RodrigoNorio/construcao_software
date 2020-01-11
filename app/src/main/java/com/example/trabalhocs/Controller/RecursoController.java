package com.example.trabalhocs.Controller;

import com.example.trabalhocs.Model.ModeloRecurso;
import com.example.trabalhocs.View.Itens.RecursoAdicionarIngredienteItemView;
import com.example.trabalhocs.View.Itens.RecursoCompraItemView;

import java.util.ArrayList;
import java.util.List;

public class RecursoController {

    private CompraRecursosListener compraRecursosListener;
    private CadastroModeloProdutoListener cadastroModeloProdutoListener;

    private List<ModeloRecurso> recursoList;
    private List<RecursoCompraItemView> compraList;
    private List<RecursoAdicionarIngredienteItemView> ingredientesList;

    private static RecursoController recursoController;
    public static RecursoController getInstance() {
        return recursoController;
    }

    /**
     * construtor estoque activity
     */
    public RecursoController(List<ModeloRecurso> recursoList) {
        this.recursoList = recursoList;
        recursoController = this;
    }

    /**
     * construtor cadastro de modelo de produto
     */
    public RecursoController(CadastroModeloProdutoListener listener, List<ModeloRecurso> recursoList) {
        this.cadastroModeloProdutoListener = listener;
        this.recursoList = recursoList;

        ingredientesList = new ArrayList<>();

        recursoController = this;
    }

    /**
     * construtor registro de compra
     */
    public RecursoController(CompraRecursosListener listener, List<ModeloRecurso> recursoList) {
        this.compraRecursosListener = listener;
        this.recursoList = recursoList;
        compraList = new ArrayList<>();
        recursoController = this;
    }

    public void addRecursoCompra(RecursoCompraItemView recursoCompraItemView) {
        compraList.add(recursoCompraItemView);
        if (compraRecursosListener != null) compraRecursosListener.atualizaListaCompras();
    }

    public void removeRecursoCompra(RecursoCompraItemView recursoCompraItemView){
        compraList.remove(recursoCompraItemView);
        if (compraRecursosListener != null) compraRecursosListener.atualizaListaCompras();
    }

    /**
     * Usado no DialogFabricacaoProduto
     */
    public void addListaRecursoIngrediente(List<RecursoAdicionarIngredienteItemView> ingredientes) {
        if (ingredientesList == null) ingredientesList = new ArrayList<>();
        ingredientesList.addAll(ingredientes);
    }

    public void addRecursoIngrediente(RecursoAdicionarIngredienteItemView recursoAdicionarIngredienteItemView) {
        ingredientesList.add(recursoAdicionarIngredienteItemView);
        if (cadastroModeloProdutoListener != null) cadastroModeloProdutoListener.atualizaListaIngredientes();
    }

    public void removeRecursoIngrediente(RecursoAdicionarIngredienteItemView recursoAdicionarIngredienteItemView) {
        ingredientesList.remove(recursoAdicionarIngredienteItemView);
        if (cadastroModeloProdutoListener != null) cadastroModeloProdutoListener.atualizaListaIngredientes();
    }

    public List<ModeloRecurso> getRecursoList() {
        return recursoList;
    }

    public List<RecursoCompraItemView> getCompraList() {
        return compraList;
    }

    public List<RecursoAdicionarIngredienteItemView> getIngredientesList() {
        return ingredientesList;
    }

    public interface CompraRecursosListener {
        void atualizaListaCompras();
    }

    public interface CadastroModeloProdutoListener {
        void atualizaListaIngredientes();
    }
}
