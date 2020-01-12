package com.example.trabalhocs.View.Dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.trabalhocs.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogAvisoVoltar extends AlertDialog.Builder {

    @BindView(R.id.btn_voltar)
    public Button buttonVoltar;
    @BindView(R.id.btn_continuar)
    public Button buttonContinuar;

    public DialogAvisoVoltar(Context context) {
        super(context);
        config(context);
    }

    private void config(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_aviso_voltar, null);
        ButterKnife.bind(this, view);

        setView(view);
    }
}
