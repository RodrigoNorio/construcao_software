package com.example.trabalhocs.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.Adapter.AdapterModeloFabricacaoProduto;
import com.example.trabalhocs.Controller.ModeloFabricacaoController;
import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Utilidades;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FabricarProdutoActivity extends AppCompatActivity {

    @BindView(R.id.btn_ajuda)
    AppCompatImageButton btnAjuda;

    @BindView(R.id.btn_cadastrar_modelo)
    AppCompatButton btnCadastrarModelo;

    @BindView(R.id.rv_modelos)
    RecyclerView rvModelos;

    @BindView(R.id.tv_lista_vazia)
    TextView tvListaVazia;

    ModeloFabricacaoController modeloFabricacaoController;
    AdapterModeloFabricacaoProduto adapterModeloFabricacaoProduto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fabricacao_produto);
        ButterKnife.bind(this);

        rvModelos.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        config();
    }

    private void config() {
        modeloFabricacaoController = new ModeloFabricacaoController(Utilidades.getListaModeloFabricacaoProdutoTeste(this));

        if (modeloFabricacaoController.getModelos().isEmpty()) {
            tvListaVazia.setVisibility(View.VISIBLE);

        } else {
            adapterModeloFabricacaoProduto = new AdapterModeloFabricacaoProduto(this, modeloFabricacaoController.getModelos());

            rvModelos.setAdapter(adapterModeloFabricacaoProduto);
            rvModelos.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.btn_cadastrar_modelo)
    void onClickBtnCadastrarModelo() {
        Intent intent = new Intent(this, CadastrarModeloProdutoActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_ajuda)
    void onClickBtnAjuda(){
        // TODO: 10/01/2020
    }

    @OnClick(R.id.btn_voltar)
    void onClickBtnVoltar() {
        onBackPressed();
    }
}