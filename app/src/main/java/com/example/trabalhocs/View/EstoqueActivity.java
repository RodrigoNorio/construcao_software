package com.example.trabalhocs.View;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.Adapter.AdapterEstoque;
import com.example.trabalhocs.Controller.RecursoController;
import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Constants;
import com.example.trabalhocs.View.Dialogs.DialogAjuda;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EstoqueActivity extends AppCompatActivity {

    @BindView(R.id.tv_lista_vazia)
    TextView tvListaVazia;
    @BindView(R.id.rv_recursos)
    RecyclerView rvRecursos;

    private RecursoController recursoController;
    private AdapterEstoque adapterEstoque;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_estoque);
        ButterKnife.bind(this);

        rvRecursos.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        config();
    }

    private void config() {
        recursoController = new RecursoController();

        if (recursoController.isRecursoListEmpty()) {
            rvRecursos.setVisibility(View.GONE);
            tvListaVazia.setVisibility(View.VISIBLE);

        } else {
            adapterEstoque = new AdapterEstoque(this, recursoController.getRecursoList());
            rvRecursos.setAdapter(adapterEstoque);

            tvListaVazia.setVisibility(View.GONE);
            rvRecursos.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == Constants.CADASTRAR_NOVO_RECURSO || requestCode == Constants.REGISTRAR_COMPRA) && resultCode == RESULT_OK) {
            config();
        }
    }

    @OnClick(R.id.btn_compra)
    void onClickBtnCompra() {
        Intent intent = new Intent(this, RegistrarCompraActivity.class);
        startActivityForResult(intent, Constants.REGISTRAR_COMPRA);
    }

    @OnClick(R.id.btn_add_novo)
    void onClickBtnAddNovo() {
        Intent intent = new Intent(this, CadastrarRecursoActivity.class);
        startActivityForResult(intent, Constants.CADASTRAR_NOVO_RECURSO);
    }

    @OnClick(R.id.btn_voltar)
    void onClickBtnVoltar() {
        onBackPressed();
    }

    @OnClick(R.id.btn_ajuda)
    void onClickBtnAjuda() {
        final DialogAjuda dialogAjuda = new DialogAjuda(this, Constants.AJUDA_ESTOQUE_RECURSOS);
        final AlertDialog dialog = dialogAjuda.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
