package com.example.trabalhocs.Utils;

import android.content.Context;
import android.widget.EditText;

import com.example.trabalhocs.Model.ModeloCompra;
import com.example.trabalhocs.Model.ModeloFabricacaoProduto;
import com.example.trabalhocs.Model.ModeloProduto;
import com.example.trabalhocs.Model.ModeloRecurso;
import com.example.trabalhocs.Model.ModeloVenda;
import com.example.trabalhocs.R;
import com.example.trabalhocs.View.Itens.ProdutoVendaItemView;
import com.example.trabalhocs.View.Itens.RecursoCompraItemView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Bruno Cesar
 * @since 08/11/19
 */
public class Utilidades {

    public static ArrayList<String> getMedidasList(Context context) {
        ArrayList<String> medidasList = new ArrayList();

        medidasList.add(context.getResources().getString(R.string.medida_gr));
        medidasList.add(context.getResources().getString(R.string.medida_ml));
        medidasList.add(context.getResources().getString(R.string.medida_un));

        return medidasList;
    }

    public static String getMedidaText(Context context, int id) {
        String abreviacao;

        switch (id) {
            case Constants.TIPO_MEDIDA_GRAMAS:
                abreviacao = context.getResources().getString(R.string.medida_gr);
                break;

            case Constants.TIPO_MEDIDA_MILILITRO:
                abreviacao = context.getResources().getString(R.string.medida_ml);
                break;

            case Constants.TIPO_MEDIDA_UNIDADE:
                abreviacao = context.getResources().getString(R.string.medida_un);
                break;

            default:
                abreviacao = "";
                break;
        }

        return abreviacao;
    }

    public static String getMedidaAbrev(Context context, int id) {
        String abreviacao;

        switch (id) {
            case Constants.TIPO_MEDIDA_GRAMAS:
                abreviacao = context.getResources().getString(R.string.abrv_gr);
                break;

            case Constants.TIPO_MEDIDA_MILILITRO:
                abreviacao = context.getResources().getString(R.string.abrv_ml);
                break;

            case Constants.TIPO_MEDIDA_UNIDADE:
                abreviacao = context.getResources().getString(R.string.abrv_un);
                break;

            default:
                abreviacao = "";
                break;
        }

        return abreviacao;
    }

    public static String formataReais(Double val) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        format.setCurrency(Currency.getInstance("BRL"));
        return format.format(val);
    }

    public static Double removeCifraoValor(EditText editText) {
        if (!editText.getText().toString().equals("")) {
            return Double.valueOf(editText.getText().toString().replaceAll("\\D", "").replace(".", "").replace(",", ".")) / 100;
        }

        return 0.0;
    }

    public static void geraProdutosTeste() {
        List<ModeloProduto> list = new ArrayList<>();

        list.add(new ModeloProduto("bolo de cenoura", "um bolo verdadeiramente brasileiro! Com cobertura de brigadeiro", 15, 15.0));
        list.add(new ModeloProduto("brigadeiro de pote", "o clássico brigadeiro num potinho", 15, 4.5));
        list.add(new ModeloProduto("bombom morangão", "um delicioso morango coberto por uma camada de beijinho e uma crosta de chocolate ao leite", 5, 5.0));
        list.add(new ModeloProduto("brownie", "um brownie padrão, feito com chocolate", 10, 3.5));

        for (ModeloProduto produto : list) {
            produto.save();
        }
    }

    public static void geraRecursosTeste() {
        List<ModeloRecurso> list = new ArrayList<>();

        list.add(new ModeloRecurso("Leite Integral", "Leite de vaca integral, com 3% de gordura", Constants.TIPO_MEDIDA_MILILITRO, 5000));
        list.add(new ModeloRecurso("Morango", "Morangos de tamanhos e formatos variados", Constants.TIPO_MEDIDA_UNIDADE, 85));
        list.add(new ModeloRecurso("Manteiga", "Manteiga cremosa", Constants.TIPO_MEDIDA_GRAMAS, 270));
        list.add(new ModeloRecurso("Leite Desnatado", "Leite de vaca desnatado, com 1% de gordura", Constants.TIPO_MEDIDA_MILILITRO, 5000));
        list.add(new ModeloRecurso("Farinha de trigo", "Leite de vaca integral, com 3% de gordura", Constants.TIPO_MEDIDA_GRAMAS, 10000));
        list.add(new ModeloRecurso("Achocolatado", "Achocolatado em pó comum, é bem doce.", Constants.TIPO_MEDIDA_GRAMAS, 3000));
        list.add(new ModeloRecurso("Açucar Refinado", "Açucar branco refinado padrão", Constants.TIPO_MEDIDA_GRAMAS, 10000));
        list.add(new ModeloRecurso("Extrato de baunilha", "Extrato liquido de baunilha, usado para perfumar e enriquecer receitas doces", Constants.TIPO_MEDIDA_MILILITRO, 200));
        list.add(new ModeloRecurso("Ovo", "Ovos de galinha, podem ser brancos ou marrons, não faz diferença!", Constants.TIPO_MEDIDA_UNIDADE, 24));

        for (ModeloRecurso recurso: list) {
            recurso.save();
        }
    }

    public static void geraModeloFabricacaoProdutoTeste(Context context) {
        try {

            List<ModeloProduto> produtoList = ModeloProduto.listAll(ModeloProduto.class);
            ModeloProduto produtoTeste = produtoList.get(produtoList.size() - 1);

            Map<ModeloRecurso, Integer> mapIngredientesTeste = new HashMap<>();

            List<ModeloRecurso> ingredientesTeste = ModeloRecurso.listAll(ModeloRecurso.class);
            Collections.reverse(ingredientesTeste);
            int[] quantidades = {14, 10, 500, 500, 250};

            for (int i = 0; i < quantidades.length; i++) {
                mapIngredientesTeste.put(ingredientesTeste.get(i), quantidades[i]);
            }

            ModeloFabricacaoProduto modelo =  new ModeloFabricacaoProduto(produtoTeste, mapIngredientesTeste, 20);
            modelo.save();

        } catch (Exception e) {
            e.printStackTrace();
            Torradeira.shortToast("Algo deu errado!", context);
        }
    }

    public static void geraModeloVendaTeste(Context context) {
        try {
            List<ProdutoVendaItemView> listaProdutosVenda = new ArrayList<>();

            List<ModeloProduto> produtosTeste = ModeloProduto.listAll(ModeloProduto.class);
            Collections.reverse(produtosTeste);

            listaProdutosVenda.add(new ProdutoVendaItemView(produtosTeste.get(0),5));
            listaProdutosVenda.add(new ProdutoVendaItemView(produtosTeste.get(1),2));

            ModeloVenda venda = new ModeloVenda("15/12/2019 20:30:21", listaProdutosVenda);
            venda.save();

        } catch (Exception e) {
            e.printStackTrace();
            Torradeira.shortToast("Algo deu errado!", context);
        }
    }

    public static void geraModeloCompraTeste(Context context) {
        try {
            List<ModeloRecurso> recursosTeste = ModeloRecurso.listAll(ModeloRecurso.class);
            Collections.reverse(recursosTeste);

            List<RecursoCompraItemView> listaRecursosCompra = new ArrayList<>();

            listaRecursosCompra.add(new RecursoCompraItemView(recursosTeste.get(0), 12, 7.35));
            listaRecursosCompra.add(new RecursoCompraItemView(recursosTeste.get(1), 50, 10));

            ModeloCompra compra = new ModeloCompra("15/12/2019 08:02:56", listaRecursosCompra);

            compra.save();

        } catch (Exception e) {
            e.printStackTrace();
            Torradeira.shortToast("Algo deu errado!", context);
        }
    }

    public static void limpaDadosProdutos() {
        ModeloProduto.deleteAll(ModeloProduto.class);
    }

    public static void limpaDadosRecursos() {
        ModeloRecurso.deleteAll(ModeloRecurso.class);
    }

    public static void limpaDadosModelosFabricacao() {
        ModeloFabricacaoProduto.deleteAll(ModeloFabricacaoProduto.class);
    }

    public static void limpaDadosCompras() {
        ModeloCompra.deleteAll(ModeloCompra.class);
    }

    public static void limpaDadosVendas() {
        ModeloVenda.deleteAll(ModeloVenda.class);
    }

    public static String getTituloAjuda(Context context, int ajudaID) {
        switch (ajudaID) {
            case Constants.AJUDA_COMPRA:
                return context.getResources().getString(R.string.ajuda_titulo_compra);

            case Constants.AJUDA_HISTORICO:
                return context.getResources().getString(R.string.ajuda_titulo_historico);

            case Constants.AJUDA_INVENTARIO:
                return context.getResources().getString(R.string.ajuda_titulo_inventario);

            case Constants.AJUDA_CADASTRAR_MODELO:
                return context.getResources().getString(R.string.ajuda_titulo_cadastrar_modelo);

            case Constants.AJUDA_FABRICAR_PRODUTOS:
                return context.getResources().getString(R.string.ajuda_titulo_fabricar_produtos);

            case Constants.AJUDA_ESTOQUE_RECURSOS:
                return context.getResources().getString(R.string.ajuda_titulo_recursos);

            case Constants.AJUDA_VENDA:
                return context.getResources().getString(R.string.ajuda_titulo_venda);

            default:
                return "ajuda";
        }
    }

    public static String getConteudoAjuda(Context context, int ajudaID) {
        switch (ajudaID) {
            case Constants.AJUDA_COMPRA:
                return context.getResources().getString(R.string.ajuda_descricao_compra);

            case Constants.AJUDA_HISTORICO:
                return context.getResources().getString(R.string.ajuda_descricao_historico);

            case Constants.AJUDA_INVENTARIO:
                return context.getResources().getString(R.string.ajuda_descricao_inventario);

            case Constants.AJUDA_CADASTRAR_MODELO:
                return context.getResources().getString(R.string.ajuda_descricao_cadastrar_modelo);

            case Constants.AJUDA_FABRICAR_PRODUTOS:
                return context.getResources().getString(R.string.ajuda_descricao_fabricar_produtos);

            case Constants.AJUDA_ESTOQUE_RECURSOS:
                return context.getResources().getString(R.string.ajuda_descricao_recursos);

            case Constants.AJUDA_VENDA:
                return context.getResources().getString(R.string.ajuda_descricao_venda);

            default:
                return "descrição da ajuda";
        }
    }
}
