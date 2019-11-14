package com.example.trabalhocs.Utils;

import android.content.Context;

import com.example.trabalhocs.Model.ModeloProduto;
import com.example.trabalhocs.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

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

    public static String formataReais(Double val) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        format.setCurrency(Currency.getInstance("BRL"));
        return format.format(val);
    }

    public static List<ModeloProduto> getListaProdutosTeste() {
        List<ModeloProduto> list = new ArrayList<>();

        list.add(new ModeloProduto("brownie comum", "um brownie padrão, feito com chocolate meio amargo", 10, 4.2));
        list.add(new ModeloProduto("blondie", "um brownie branco, feito com chocolate branco", 15, 3.5));
        list.add(new ModeloProduto("bombom morangão", "um delicioso coberto por uma camada de beijinho e uma crosta de chocolate ao leite", 5, 5.0));

        return list;
    }
}
