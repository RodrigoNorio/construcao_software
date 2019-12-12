package com.example.trabalhocs.View;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.R;
import com.example.trabalhocs.View.Dialogs.DialogAvisoVoltar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrarCompraActivity extends AppCompatActivity {

    @BindView(R.id.rv_recursos)
    RecyclerView rvProdutos;

    @BindView(R.id.btn_confirmar)
    AppCompatButton btnConfirmar;

    private boolean temItens = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registrar_venda);
        ButterKnife.bind(this);

        config();
    }

    private void config() {

    }

    @OnClick(R.id.btn_confirmar)
    void onClickBtnConfirmar() {
//        Torradeira.longToast("total da venda: " + tvValorTotal.getText(), this);
    }

    @OnClick(R.id.btn_ajuda)
    void onClickBtnAjuda() {
        // TODO: 11/11/2019 add dialog de ajuda
    }

    @OnClick(R.id.btn_cancelar)
    void onClickBtnCancelar(){
        if (temItens) {
            dialogAvisoSalvar();
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        dialogAvisoSalvar();
    }

    private void dialogAvisoSalvar() {
        final DialogAvisoVoltar dialogAvisoSalvar = new DialogAvisoVoltar(this);
        final AlertDialog dialog =  dialogAvisoSalvar.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialogAvisoSalvar.buttonVoltar.setOnClickListener(v -> dialog.dismiss());

        dialogAvisoSalvar.buttonContinuar.setOnClickListener(v -> {
            dialog.dismiss();

            finish();
        });
    }
}
