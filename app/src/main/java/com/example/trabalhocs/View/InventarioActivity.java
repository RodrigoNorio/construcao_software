package com.example.trabalhocs.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.Adapter.AdapterInventario;
import com.example.trabalhocs.Controller.ProdutoController;
import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InventarioActivity extends AppCompatActivity {

    @BindView(R.id.tv_lista_vazia)
    TextView tvListaVazia;
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
        produtoController = new ProdutoController();

        if (produtoController.isProdutoListEmpty()) {
            rvProdutos.setVisibility(View.GONE);
            tvListaVazia.setVisibility(View.VISIBLE);

        } else {
            adapterInventario = new AdapterInventario(this, produtoController.getProdutosList());
            rvProdutos.setAdapter(adapterInventario);

            tvListaVazia.setVisibility(View.GONE);
            rvProdutos.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.CADASTRAR_NOVO_PRODUTO && resultCode == RESULT_OK) {
            config();
        }
    }

    @OnClick(R.id.btn_add_novo)
    void onClickBtnAddNovo() {
        Intent intent = new Intent(this, CadastrarProdutoActivity.class);
        startActivityForResult(intent, Constants.CADASTRAR_NOVO_PRODUTO);
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
