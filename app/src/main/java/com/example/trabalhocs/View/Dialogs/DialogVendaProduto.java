package com.example.trabalhocs.View.Dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import com.example.trabalhocs.Model.ModeloProduto;
import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Torradeira;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogVendaProduto extends AlertDialog.Builder {
    @BindView(R.id.tv_nome)
    TextView tvNome;

    @BindView(R.id.tv_valor_unitario)
    TextView tvValorUn;
    @BindView(R.id.tv_estoque)
    TextView tvEstoque;

    @BindView(R.id.tv_desc)
    TextView tvDesc;

    @BindView(R.id.et_quantidade)
    TextInputEditText etQtd;

    @BindView(R.id.tv_valor_total)
    TextView tvValorTotal;

    @BindView(R.id.btn_confirmar)
    AppCompatButton btnConfirmar;

    Context context;
    ModeloProduto produto;
    private AlertDialog dialog;

    public DialogVendaProduto(Context context, ModeloProduto produto) {
        super(context);
        this.context = context;
        this.produto = produto;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_venda_produto, null);
        setCancelable(true);

        ButterKnife.bind(this, view);

        config();
    }

    @Override
    public AlertDialog create() {
        this.dialog = super.create();
        return this.dialog;
    }

    @SuppressLint("DefaultLocale")
    private void config() {
        tvNome.setText(produto.getNome());
        tvValorUn.setText(produto.getValorUnitarioText());
        tvEstoque.setText(String.format("%d unidades em estoque", produto.getEstoque()));
        tvDesc.setText(produto.getDescricao());
    }

    @OnClick(R.id.btn_confirmar)
    void onClickBtnConfirmar() {
        Torradeira.shortToast("botao confirmar", context);
    }

    @OnClick(R.id.btn_cancelar)
    void onClickBtnCancelar() {
        Torradeira.shortToast("botao cancelar", context);
    }
}
