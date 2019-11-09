package com.example.trabalhocs.Utils;

import android.app.Activity;
import android.widget.Toast;

public class Torradeira {

    private static Toast torrada;

    /**
     * toast longo de propósito geral
     */
    public static void longToast(String msg, Activity activity) {
        if (torrada != null) torrada.cancel();
        torrada = Toast.makeText(activity, msg, Toast.LENGTH_LONG);
        torrada.show();
    }

    /**
     * toast curto de propósito geral
     */
    public static void shortToast(String msg, Activity activity) {
        if (torrada != null) torrada.cancel();
        torrada = Toast.makeText(activity, msg, Toast.LENGTH_SHORT);
        torrada.show();
    }

}




