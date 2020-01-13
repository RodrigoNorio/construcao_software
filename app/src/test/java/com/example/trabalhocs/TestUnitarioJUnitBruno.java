package com.example.trabalhocs;

import com.example.trabalhocs.Model.ModeloCompra;
import com.example.trabalhocs.Model.ModeloFabricacaoProduto;
import com.example.trabalhocs.Model.ModeloProduto;
import com.example.trabalhocs.Model.ModeloRecurso;
import com.example.trabalhocs.Model.ModeloVenda;
import com.example.trabalhocs.Utils.Constants;
import com.example.trabalhocs.View.Itens.ProdutoVendaItemView;
import com.example.trabalhocs.View.Itens.RecursoCompraItemView;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestUnitarioJUnitBruno extends TestCase {

    public void testCriarModeloProduto() {
        ModeloProduto produtoTeste = new ModeloProduto("Produto teste", "descrição de teste", 1, 2.5);

        boolean resultado = produtoTeste instanceof ModeloProduto && produtoTeste.testeProdutoValido();

        assertTrue(resultado);
    }

    public void testCriarModeloRecurso() {
        ModeloRecurso recursoTeste = new ModeloRecurso("Recurso teste", "descrição de teste", Constants.TIPO_MEDIDA_GRAMAS, 1);

        boolean resultado = recursoTeste instanceof ModeloRecurso && recursoTeste.testeRecursoValido();

        assertTrue(resultado);
    }

    public void testCriarModeloFabricacaoProduto() {
        ModeloProduto produtoTeste = new ModeloProduto("Produto teste", "descrição de teste", 1, 2.5);
        ModeloRecurso recursoTeste = new ModeloRecurso("Recurso teste", "descrição de teste", Constants.TIPO_MEDIDA_UNIDADE, 1);

        Map<ModeloRecurso, Integer> mapIngredientesTeste = new HashMap<>();

        mapIngredientesTeste.put(recursoTeste, 1);
        ModeloFabricacaoProduto modeloFabricacaoProdutoTeste = new ModeloFabricacaoProduto(produtoTeste, mapIngredientesTeste, 1);

        boolean resultado = modeloFabricacaoProdutoTeste instanceof ModeloFabricacaoProduto && modeloFabricacaoProdutoTeste.testeModeloFabricacaoProdutoValido();

        assertTrue(resultado);
    }

    public void testCriarModeloVenda() {
        List<ProdutoVendaItemView> listaProdutosVenda = new ArrayList<>();

        ModeloProduto produtoTeste = new ModeloProduto("Produto teste", "descrição de teste", 1, 2.5);
        produtoTeste.setId(1L);

        listaProdutosVenda.add(new ProdutoVendaItemView(produtoTeste,5));

        ModeloVenda vendaTeste = new ModeloVenda("15/12/2019 20:30:21", listaProdutosVenda);

        boolean resultado = vendaTeste instanceof ModeloVenda && vendaTeste.testeVendaValida();

        assertTrue(resultado);
    }

    public void testCriarModeloCompra() {
        List<RecursoCompraItemView> listaRecursosCompra = new ArrayList<>();

        ModeloRecurso recursoTeste = new ModeloRecurso("Recurso teste", "descrição de teste", Constants.TIPO_MEDIDA_MILILITRO, 1);
        recursoTeste.setId(1L);

        listaRecursosCompra.add(new RecursoCompraItemView(recursoTeste, 12, 7.35));

        ModeloCompra compraTeste = new ModeloCompra("15/12/2019 08:02:56", listaRecursosCompra);

        boolean resultado = compraTeste instanceof  ModeloCompra && compraTeste.testeVendaValida();

        assertTrue(resultado);
    }

}
