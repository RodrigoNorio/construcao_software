package com.example.trabalhocs.View.Dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import com.example.trabalhocs.Model.ModeloProduto;
import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Torradeira;
import com.example.trabalhocs.Utils.Utilidades;
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

    private AddProdutoListener listener;
    private AlertDialog dialog;

    private Context context;
    private int posicao;
    private ModeloProduto produto;

    public DialogVendaProduto(Context context, AddProdutoListener listener,
                              int posicao, ModeloProduto produto,
                              int quantidadeAtual) {
        super(context);
        this.context = context;
        this.listener = listener;
        this.posicao = posicao;
        this.produto = produto;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_venda_produto, null);
        setCancelable(true);

        ButterKnife.bind(this, view);

        config(quantidadeAtual);

        setView(view);
    }

    @Override
    public AlertDialog create() {
        this.dialog = super.create();
        return this.dialog;
    }

    @SuppressLint("DefaultLocale")
    private void config(int quantidadeAtual) {
        tvNome.setText(produto.getNome());
        tvValorUn.setText(produto.getValorUnitarioText());
        tvEstoque.setText(String.format("%d unidades em estoque", produto.getEstoque()));
        tvDesc.setText(produto.getDescricao());

        if (quantidadeAtual > 0) {
            etQtd.setText(String.format("%d", quantidadeAtual));
            tvValorTotal.setText(String.format("total: %s", Utilidades.formataReais(produto.getValorUnitario() * quantidadeAtual)));
            tvValorTotal.setVisibility(View.VISIBLE);
            btnConfirmar.setEnabled(true);
        }

        etQtd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                int qtdInput = !s.toString().equals("") ? Integer.parseInt(s.toString()) : 0;

                if (qtdInput <= 0) {

                    if (qtdInput < 0) Torradeira.shortToast("quantidade inválida!", context);
                    tvValorTotal.setVisibility(View.GONE);
                    btnConfirmar.setEnabled(false);

                } else {

                    if (qtdInput > produto.getEstoque()) {
                        Torradeira.shortToast("quantidade não pode ser maior que o estoque!", context);
                        etQtd.setSelection(s.length());

                        etQtd.removeTextChangedListener(this);
                        etQtd.setText(String.format("%d", produto.getEstoque()));
                        etQtd.addTextChangedListener(this);

                        qtdInput = produto.getEstoque();
                    }

                    tvValorTotal.setText(String.format("total: %s", Utilidades.formataReais(produto.getValorUnitario() * qtdInput)));
                    tvValorTotal.setVisibility(View.VISIBLE);
                    btnConfirmar.setEnabled(true);
                }
            }
        });
    }

    @OnClick(R.id.btn_confirmar)
    void onClickBtnConfirmar() {

        try {
            int qtdInput = !etQtd.getText().toString().equals("") ? Integer.parseInt(etQtd.getText().toString()) : 0;

            if (qtdInput > 0) {
                listener.addProduto(posicao, produto, qtdInput);
                dialog.dismiss();
            } else {
                Torradeira.shortToast("quantidade inválida!", context);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("DialogVendaProduto", "edit text da quantidade retornou null!");
            Torradeira.erroToast(context);
        }

    }

    @OnClick(R.id.btn_cancelar)
    void onClickBtnCancelar() {
        dialog.dismiss();
    }

    public interface AddProdutoListener {
        void addProduto(int posicao, ModeloProduto produto, int quantidade);
    }
}
