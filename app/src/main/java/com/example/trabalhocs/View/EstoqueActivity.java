package com.example.trabalhocs.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.Adapter.AdapterEstoque;
import com.example.trabalhocs.Controller.RecursoController;
import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Utilidades;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EstoqueActivity extends AppCompatActivity {

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
        recursoController = new RecursoController(Utilidades.getListaRecursosTeste());
        adapterEstoque = new AdapterEstoque(this, recursoController.getRecursoList());
        rvRecursos.setAdapter(adapterEstoque);
    }

    @OnClick(R.id.btn_compra)
    void onClickBtnCompra() {
        Intent intent = new Intent(this, RegistrarCompraActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_add_novo)
    void onClickBtnAddNovo() {
        Intent intent = new Intent(this, CadastrarRecursoActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_voltar)
    void onClickBtnVoltar() {
        onBackPressed();
    }
}
