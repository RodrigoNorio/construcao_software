package com.example.trabalhocs.View;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.Adapter.AdapterResumoVenda;
import com.example.trabalhocs.Adapter.AdapterVendaProduto;
import com.example.trabalhocs.Controller.VendaController;
import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Utilidades;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrarVendaActivity extends AppCompatActivity {

    @BindView(R.id.rv_produtos)
    RecyclerView rvProdutos;

    @BindView(R.id.lv_resumo)
    ListView lvResumo;

    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_valor_total)
    TextView tvValorTotal;

    private VendaController vendaController;
    private AdapterVendaProduto adapterVendaProduto;
    private AdapterResumoVenda adapterResumoVenda;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registrar_venda);
        ButterKnife.bind(this);

        config();
    }

    private void config() {
        vendaController = new VendaController(Utilidades.getListaProdutosTeste()); // produtos teste
        adapterVendaProduto = new AdapterVendaProduto(this, vendaController.getProdutos());
//        adapterResumoVenda = new AdapterResumoVenda(this, vendaController.getProdutosViews());

        rvProdutos.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvProdutos.setAdapter(adapterVendaProduto);
    }

    @OnClick(R.id.btn_confirmar)
    void onClickBtnConfirmar() {
        // TODO: 11/11/2019
    }

    @OnClick(R.id.btn_cancelar)
    void onClickBtnCancelar(){
        // TODO: 11/11/2019
    }

    @OnClick(R.id.btn_ajuda)
    void onClickBtnAjuda() {
        // TODO: 11/11/2019 add modal ajuda?
    }
}
