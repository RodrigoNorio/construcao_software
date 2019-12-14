package com.example.trabalhocs.View;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.Adapter.AdapterListaCompra;
import com.example.trabalhocs.Controller.EstoqueController;
import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Utilidades;
import com.example.trabalhocs.View.Dialogs.DialogAvisoVoltar;
import com.example.trabalhocs.View.Dialogs.DialogListaRecursos;
import com.example.trabalhocs.View.Itens.RecursoCompraItemView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrarCompraActivity extends AppCompatActivity implements EstoqueController.CompraRecursosListener {

    @BindView(R.id.tv_lista_vazia)
    TextView tvListaVazia;

    @BindView(R.id.rv_lista_compras)
    RecyclerView rvListaCompras;

    @BindView(R.id.btn_add)
    AppCompatImageButton btnAdd;

    @BindView(R.id.btn_confirmar)
    AppCompatButton btnConfirmar;

    private EstoqueController estoqueController;
    private boolean temItens = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registrar_compra);
        ButterKnife.bind(this);

        config();
    }

    private void config() {
        estoqueController = new EstoqueController(this, Utilidades.getListaRecursosTeste());
        AdapterListaCompra adapterCompraRecurso = new AdapterListaCompra(this, estoqueController);

        rvListaCompras.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvListaCompras.setAdapter(adapterCompraRecurso);

        rvListaCompras.setVisibility(View.GONE);
        tvListaVazia.setVisibility(View.VISIBLE);
    }

    @Override
    public void atualizaListaCompras() {
        List<RecursoCompraItemView> listaCompras = estoqueController.getCompraList();

        AdapterListaCompra adapterCompraRecurso = new AdapterListaCompra(this, estoqueController);
        rvListaCompras.setAdapter(adapterCompraRecurso);

        if (listaCompras.isEmpty()) {
            rvListaCompras.setVisibility(View.GONE);
            tvListaVazia.setVisibility(View.VISIBLE);
        } else {
            tvListaVazia.setVisibility(View.GONE);
            rvListaCompras.setVisibility(View.VISIBLE);
        }

    }

    private void abrirDialogListaRecursos() {
        DialogListaRecursos dialogListaRecursos = new DialogListaRecursos(this, estoqueController.getRecursoList());
        final AlertDialog dialog = dialogListaRecursos.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @OnClick(R.id.btn_add)
    void onClickBtnAdd() {
        abrirDialogListaRecursos();
    }

    @OnClick(R.id.btn_confirmar)
    void onClickBtnConfirmar() {
//        Torradeira.longToast("total da compra: " + tvValorTotal.getText(), this);
    }

    @OnClick(R.id.btn_ajuda)
    void onClickBtnAjuda() {
        // TODO: 11/11/2019 add dialog de ajuda
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

            finish();
        });
    }
}
