package com.example.trabalhocs.View.Dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Utilidades;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogAjuda extends AlertDialog.Builder {

    @BindView(R.id.tv_titulo_ajuda)
    TextView tvTitulo;

    @BindView(R.id.tv_ajuda_texto)
    TextView tvConteudo;

    private AlertDialog dialog;

    public DialogAjuda(Context context, int ajudaID) {
        super(context);
        config(context, ajudaID);
    }

    @Override
    public AlertDialog create() {
        this.dialog = super.create();
        return this.dialog;
    }

    private void config(Context context, int ajudaID) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_ajuda, null);
        ButterKnife.bind(this, view);

        this.tvTitulo.setText(Utilidades.getTituloAjuda(context, ajudaID));
        this.tvConteudo.setText(Utilidades.getConteudoAjuda(context, ajudaID));

        setView(view);
    }

    @OnClick(R.id.btn_ok)
    void onClickBtnOk() {
        dialog.dismiss();
    }
}
