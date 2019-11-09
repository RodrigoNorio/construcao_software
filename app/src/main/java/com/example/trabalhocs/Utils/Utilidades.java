package com.example.trabalhocs.Utils;

import android.content.Context;

import com.example.trabalhocs.R;

import java.util.ArrayList;

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

}
