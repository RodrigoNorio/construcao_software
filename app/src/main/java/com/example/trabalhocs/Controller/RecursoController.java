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
    public RecursoController() {
        this.recursoList = ModeloRecurso.listAll(ModeloRecurso.class);
        recursoController = this;
    }

    /**
     * construtor cadastro de modelo de produto
     */
    public RecursoController(CadastroModeloProdutoListener listener) {
        this.cadastroModeloProdutoListener = listener;
        this.recursoList = ModeloRecurso.listAll(ModeloRecurso.class);

        ingredientesList = new ArrayList<>();

        recursoController = this;
    }

    /**
     * construtor registro de compra
     */
    public RecursoController(CompraRecursosListener listener) {
        this.compraRecursosListener = listener;
        this.recursoList = ModeloRecurso.listAll(ModeloRecurso.class);

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

    public void efetivarCompra() {
        for (RecursoCompraItemView item: compraList) {
            ModeloRecurso recurso = ModeloRecurso.findById(ModeloRecurso.class, item.getRecurso().getId());
            recurso.incrementarInventario(item.getQuantidade());
            recurso.save();
        }
    }

    public void efetivarFabricacaoProduto() {
        for (RecursoAdicionarIngredienteItemView item : ingredientesList) {
            ModeloRecurso recurso = ModeloRecurso.findById(ModeloRecurso.class, item.getRecurso().getId());
            recurso.decrementarInventario(item.getQuantidade());
            recurso.save();
        }
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

    public boolean isRecursoListEmpty() {
        return recursoList.isEmpty();
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
