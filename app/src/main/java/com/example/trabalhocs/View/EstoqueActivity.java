package com.example.trabalhocs.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Torradeira;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EstoqueActivity extends AppCompatActivity {

    @BindView(R.id.rv_recursos)
    RecyclerView rvRecursos;

    @BindView(R.id.btn_add_novo)
    AppCompatButton btnCadastroRecurso;

    @BindView(R.id.btn_compra)
    AppCompatButton btnCompraRecurso;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_estoque);
        ButterKnife.bind(this);

        config();
    }

    private void config() {
//        vendaController = new VendaController(this, Utilidades.getListaProdutosTeste()); // produtos teste
//        adapterVendaProduto = new AdapterVendaProduto(this, vendaController);
//        adapterResumoVenda = new AdapterResumoVenda(this, vendaController.getProdutosSelecionadosView());
//
//        rvProdutos.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
//        rvProdutos.setAdapter(adapterVendaProduto);
    }

    @OnClick(R.id.btn_compra)
    void onClickBtnCompra() {
        Torradeira.longToast("clicou no botão de compra!", this);
    }

    @OnClick(R.id.btn_add_novo)
    void onClickBtnAddNovo() {
        Intent intent = new Intent(this, CadastrarRecursoActivity.class);
        startActivity(intent);
    }
}
