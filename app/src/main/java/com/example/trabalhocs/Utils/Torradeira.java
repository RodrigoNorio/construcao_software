package com.example.trabalhocs.Utils;

import android.content.Context;
import android.widget.Toast;

public class Torradeira {

    private static Toast torrada;

    /**
     * toast longo de propósito geral
     */
    public static void longToast(String msg, Context context) {
        if (torrada != null) torrada.cancel();
        torrada = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        torrada.show();
    }

    /**
     * toast curto de propósito geral
     */
    public static void shortToast(String msg, Context context) {
        if (torrada != null) torrada.cancel();
        torrada = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        torrada.show();
    }

}




