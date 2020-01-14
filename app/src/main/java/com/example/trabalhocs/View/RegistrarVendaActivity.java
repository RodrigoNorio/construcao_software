package com.example.trabalhocs.View;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.example.trabalhocs.Controller.ProdutoController;
import com.example.trabalhocs.Model.ModeloVenda;
import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Constants;
import com.example.trabalhocs.Utils.Torradeira;
import com.example.trabalhocs.Utils.Utilidades;
import com.example.trabalhocs.View.Dialogs.DialogAjuda;
import com.example.trabalhocs.View.Dialogs.DialogAvisoVoltar;
import com.example.trabalhocs.View.Itens.ProdutoVendaItemView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrarVendaActivity extends AppCompatActivity implements ProdutoController.VendaControllerListener {

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

    private ProdutoController produtoController;
    private AdapterVendaProduto adapterVendaProduto;
    private AdapterResumoVenda adapterResumoVenda;
    private boolean temItens = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registrar_venda);
        ButterKnife.bind(this);

        config();
    }

    private void config() {
        produtoController = new ProdutoController(this);
        adapterVendaProduto = new AdapterVendaProduto(this, produtoController);
        adapterResumoVenda = new AdapterResumoVenda(this, produtoController.getProdutosSelecionadosView());

        rvProdutos.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvProdutos.setAdapter(adapterVendaProduto);
    }

    public void atualizarResumo(double total) {
        List<ProdutoVendaItemView> produtosSelecionados = produtoController.getProdutosSelecionadosView();

        if (!adapterResumoVenda.isEmpty()) adapterResumoVenda.clear();
        adapterResumoVenda.addAll(produtosSelecionados);
        lvResumo.setAdapter(adapterResumoVenda);

        if (total > 0) {
            tvValorTotal.setText(Utilidades.formataReais(total));

            tvTotal.setVisibility(View.VISIBLE);
            tvValorTotal.setVisibility(View.VISIBLE);

            btnConfirmar.setEnabled(true);
            temItens = true;

        }  else {
            tvTotal.setVisibility(View.GONE);
            tvValorTotal.setVisibility(View.GONE);
            btnConfirmar.setEnabled(false);
            temItens = false;
        }

    }

    @Override
    public void atualizaLista(double total) {
        atualizarResumo(total);
    }

    @OnClick(R.id.btn_confirmar)
    void onClickBtnConfirmar() {

        Date horaAgora = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss", Locale.getDefault());
        String horaFormatada = sdf.format(horaAgora);

        ModeloVenda venda = new ModeloVenda(horaFormatada, produtoController.getProdutosSelecionadosView());
        venda.save();

        produtoController.efetivarVenda();

        Torradeira.shortToast(getString(R.string.venda_sucesso), this);
        setResult(RESULT_OK);
        finish();
    }

    @OnClick(R.id.btn_ajuda)
    void onClickBtnAjuda() {
        final DialogAjuda dialogAjuda = new DialogAjuda(this, Constants.AJUDA_VENDA);
        final AlertDialog dialog = dialogAjuda.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}
