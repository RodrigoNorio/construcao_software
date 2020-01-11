package com.example.trabalhocs.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.Adapter.AdapterInventario;
import com.example.trabalhocs.Controller.ProdutoController;
import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Utilidades;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InventarioActivity extends AppCompatActivity {

    @BindView(R.id.rv_produtos)
    RecyclerView rvProdutos;
    
    ProdutoController produtoController;
    AdapterInventario adapterInventario;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_inventario);
        ButterKnife.bind(this);

        rvProdutos.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        config();
    }
    
    private void config() {
        produtoController = new ProdutoController(Utilidades.getListaProdutosTeste());
        adapterInventario = new AdapterInventario(this, produtoController.getProdutosList());
        rvProdutos.setAdapter(adapterInventario);
    }

    @OnClick(R.id.btn_add_novo)
    void onClickBtnAddNovo() {
        Intent intent = new Intent(this, CadastrarProdutoActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_cadastrar_modelo)
    void onClickBtnCadastrarModeloProduto() {
        Intent intent = new Intent(this, CadastrarModeloProdutoActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_fabricacao)
    void onClickBtnFabricacao() {
        Intent intent = new Intent(this, FabricarProdutoActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_voltar)
    void onClickBtnVoltar() {
        onBackPressed();
    }
}
