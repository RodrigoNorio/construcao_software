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
        medidasList.add(context.getResources().getString(R.string.medida_kg));
        medidasList.add(context.getResources().getString(R.string.medida_l));

        return medidasList;
    }

    public static String getMedidaText(Context context, int id) {
        String abreviacao = "";

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

            case Constants.TIPO_MEDIDA_KILO:
                abreviacao = context.getResources().getString(R.string.medida_kg);
                break;

            case Constants.TIPO_MEDIDA_LITRO:
                abreviacao = context.getResources().getString(R.string.medida_l);
                break;

            default:
                abreviacao = "";
                break;
        }

        return abreviacao;
    }

    public static String getMedidaAbrev(Context context, int id) {
        String abreviacao = "";

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

            case Constants.TIPO_MEDIDA_KILO:
                abreviacao = context.getResources().getString(R.string.abrv_kg);
                break;

            case Constants.TIPO_MEDIDA_LITRO:
                abreviacao = context.getResources().getString(R.string.abrv_l);
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

    public static List<ModeloProduto> getListaProdutosTeste() {
        List<ModeloProduto> list = new ArrayList<>();

        list.add(new ModeloProduto("brownie comum", "um brownie padrão, feito com chocolate meio amargo", 10, 4.2));
        list.add(new ModeloProduto("blondie", "um brownie branco, feito com chocolate branco", 15, 3.5));
        list.add(new ModeloProduto("bombom morangão", "um delicioso coberto por uma camada de beijinho e uma crosta de chocolate ao leite", 5, 5.0));

        return list;
    }

    public static void geraRecursosTeste() {
        List<ModeloRecurso> list = new ArrayList<>();

        list.add(new ModeloRecurso("Leite Integral", "Leite de vaca integral", Constants.TIPO_MEDIDA_LITRO, 5));
        list.add(new ModeloRecurso("Achocolatado", "Achocolatado em pó comum", Constants.TIPO_MEDIDA_GRAMAS, 3000));
        list.add(new ModeloRecurso("Açucar", "Açucar branco padrão", Constants.TIPO_MEDIDA_KILO, 10));
        list.add(new ModeloRecurso("Extrato de baunilha", "Extrato liquido de baunilha, usado para perfumar e enriquecer receitas doces", Constants.TIPO_MEDIDA_MILILITRO, 200));
        list.add(new ModeloRecurso("Ovo", "Ovos de galinha, podem ser brancos os marrons não faz diferença!", Constants.TIPO_MEDIDA_UNIDADE, 24));

        for (ModeloRecurso recurso: list) {
            recurso.save();
        }
    }

    public static void limpaDadosRecursos() {
        ModeloRecurso.deleteAll(ModeloRecurso.class);
    }

    public static List<ModeloFabricacaoProduto> getListaModeloFabricacaoProdutoTeste(Context context) {
        List<ModeloFabricacaoProduto> list = new ArrayList<>();

        try {
            ModeloProduto produtoTeste = new ModeloProduto("brownie comum", "um brownie padrão, feito com chocolate meio amargo", 10, 4.2);
            Map<ModeloRecurso, Integer> mapIngredientesTeste = new HashMap<>();

            List<ModeloRecurso> ingredientesTeste = ModeloRecurso.listAll(ModeloRecurso.class);
            int[] quantidades = {1, 500, 1, 10, 14};

            for (int i = 0; i < quantidades.length; i++) {
                mapIngredientesTeste.put(ingredientesTeste.get(i), quantidades[i]);
            }

            list.add(new ModeloFabricacaoProduto(1, produtoTeste, mapIngredientesTeste, 30));

        } catch (Exception e) {
            e.printStackTrace();
            Torradeira.shortToast("Algo deu errado!", context);
        }

        return  list;
    }

    public static List<ModeloVenda> getListaModeloVendaTeste() {
        List<ModeloVenda> list = new ArrayList<>();

        try {
            List<ProdutoVendaItemView> listaProdutosVenda = new ArrayList<>();
            List<ModeloProduto> listaProdutos = getListaProdutosTeste();

            listaProdutosVenda.add(new ProdutoVendaItemView(listaProdutos.get(0),5));
            listaProdutosVenda.add(new ProdutoVendaItemView(listaProdutos.get(2),2));

            list.add(new ModeloVenda(1, "15/12/2019 20:30:21", listaProdutosVenda));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<ModeloCompra> getListaModeloCompraTeste() {
        List<ModeloCompra> list = new ArrayList<>();

        try {
            List<ModeloRecurso> recursosTeste = ModeloRecurso.listAll(ModeloRecurso.class);
            int[] quantidades = {1, 500, 1, 10, 14};

            List<RecursoCompraItemView> listaRecursosCompra = new ArrayList<>();

            listaRecursosCompra.add(new RecursoCompraItemView(recursosTeste.get(0), 1, 3.45));
            listaRecursosCompra.add(new RecursoCompraItemView(recursosTeste.get(1), 200, 10));

            list.add(new ModeloCompra(1, "15/12/2019 08:02:56", listaRecursosCompra));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return  list;
    }
}
