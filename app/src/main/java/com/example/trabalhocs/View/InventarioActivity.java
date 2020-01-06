package com.example.trabalhocs.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.Adapter.AdapterInventario;
import com.example.trabalhocs.Controller.VendaController;
import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Utilidades;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InventarioActivity extends AppCompatActivity {

    @BindView(R.id.rv_produtos)
    RecyclerView rvProdutos;
    
    VendaController vendaController;
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
        vendaController = new VendaController(Utilidades.getListaProdutosTeste());
        adapterInventario = new AdapterInventario(this, vendaController.getProdutosList());
        rvProdutos.setAdapter(adapterInventario);
    }

    @OnClick(R.id.btn_fabricacao)
    void onClickBtnFabricacao() {
        // TODO: 05/01/2020
    }

    @OnClick(R.id.btn_add_novo)
    void onClickBtnAddNovo() {
        Intent intent = new Intent(this, CadastrarProdutoActivity.class);
        startActivity(intent);
    }
}
