package com.example.trabalhocs.View;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.Adapter.AdapterResumoVenda;
import com.example.trabalhocs.Adapter.AdapterVendaProduto;
import com.example.trabalhocs.Controller.VendaController;
import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Torradeira;
import com.example.trabalhocs.Utils.Utilidades;
import com.example.trabalhocs.View.Itens.ProdutoVendaItemView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrarVendaActivity extends AppCompatActivity implements VendaController.VendaControllerListener {

    @BindView(R.id.rv_produtos)
    RecyclerView rvProdutos;

    @BindView(R.id.lv_resumo)
    ListView lvResumo;

    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_valor_total)
    TextView tvValorTotal;

    @BindView(R.id.btn_confirmar)
    AppCompatButton btnConfirmar;

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

        vendaController = new VendaController(this, Utilidades.getListaProdutosTeste()); // produtos teste
        adapterVendaProduto = new AdapterVendaProduto(this, vendaController);
        adapterResumoVenda = new AdapterResumoVenda(this, vendaController.getProdutosSelecionadosView());

        rvProdutos.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvProdutos.setAdapter(adapterVendaProduto);
    }

    public void atualizarResumo(double total) {
        List<ProdutoVendaItemView> produtosSelecionados = vendaController.getProdutosSelecionadosView();

        if (!adapterResumoVenda.isEmpty()) adapterResumoVenda.clear();
        adapterResumoVenda.addAll(produtosSelecionados);
        lvResumo.setAdapter(adapterResumoVenda);

        if (total > 0) {
            tvValorTotal.setText(Utilidades.formataReais(total));

            tvTotal.setVisibility(View.VISIBLE);
            tvValorTotal.setVisibility(View.VISIBLE);

            btnConfirmar.setEnabled(true);

        }  else {
            tvTotal.setVisibility(View.GONE);
            tvValorTotal.setVisibility(View.GONE);
            btnConfirmar.setEnabled(false);
        }

    }

    @Override
    public void atualizaLista(double total) {
        atualizarResumo(total);
    }

    @OnClick(R.id.btn_confirmar)
    void onClickBtnConfirmar() {
        Torradeira.longToast("total da venda: " + tvValorTotal.getText(), this);
    }

    @OnClick(R.id.btn_cancelar)
    void onClickBtnCancelar(){
        // TODO: 11/11/2019
    }

    @OnClick(R.id.btn_ajuda)
    void onClickBtnAjuda() {
        // TODO: 11/11/2019 add dialog de ajuda
    }
}
