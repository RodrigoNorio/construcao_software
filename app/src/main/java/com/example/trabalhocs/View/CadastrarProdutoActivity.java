package com.example.trabalhocs.View;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Torradeira;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Bruno
 * @since 26/10/2019
 */
public class CadastrarProdutoActivity extends AppCompatActivity {

    @BindView(R.id.et_nome)
    TextInputEditText etNome;

    @BindView(R.id.et_descricao)
    TextInputEditText etDescricao;

    @BindView(R.id.et_valor)
    TextInputEditText etValor;

    @BindView(R.id.et_estoque)
    TextInputEditText etEstoque;

    @BindView(R.id.btn_cadastrar)
    AppCompatButton btnCadastrar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cadastrar_produto);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_cadastrar)
    void onClickBtnCdastrar() {

        if (isCamposValidos()) {
            // TODO: 08/11/2019 implementar lógica do cadastro
            Torradeira.shortToast("cadastrou!!", this);
        }

    }

    private boolean isCamposValidos() {
        boolean isValido = true;

        if (etNome.getText().toString().isEmpty()) {
            etNome.setError(getString(R.string.error_nome));
            Torradeira.shortToast(getString(R.string.erro_nome_recurso), this);
            isValido = false;
        }

        if (etValor.getText().toString().isEmpty()) {
            etValor.setError(getString(R.string.erro_valor));
            Torradeira.longToast(getString(R.string.erro_valor_vazio), this);
            isValido = false;

        } else if (Integer.parseInt(etValor.getText().toString()) < 0) {
            Torradeira.longToast(getString(R.string.erro_valor_invalido), this);
            isValido = false;
        }

        if (etEstoque.getText().toString().isEmpty()) {
            Torradeira.longToast(getString(R.string.error_estoque_vazio), this);
            isValido = false;

        } else if (Integer.parseInt(etEstoque.getText().toString()) < 0) {
            Torradeira.longToast(getString(R.string.error_estoque_invalido), this);
            isValido = false;
        }

        return isValido;
    }

    @OnClick(R.id.btn_voltar)
    void onClickBtnVoltar() {
        onBackPressed();
    }
}
