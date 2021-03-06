package com.example.trabalhocs.View;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.trabalhocs.Model.ModeloProduto;
import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Torradeira;
import com.example.trabalhocs.Utils.Utilidades;
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

        config();
    }

    private void config() {
        etValor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etValor.removeTextChangedListener(this);

                String stringValor = s.toString().replaceAll("[BR$,.\\s]", "");

                double valor = 0.0;

                try {
                    valor = Double.parseDouble(stringValor);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Torradeira.erroToast(getApplicationContext());
                }

                String valorFormatado = Utilidades.formataReais(valor / 100);

                etValor.setText(valorFormatado);
                etValor.setSelection(valorFormatado.length());

                etValor.addTextChangedListener(this);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick(R.id.btn_cadastrar)
    void onClickBtnCdastrar() {
        try {
            if (isCamposValidos()) {

                String nome = etNome.getText().toString();
                String descricao = etDescricao.getText().toString();
                int estoque = Integer.parseInt(etEstoque.getText().toString());
                double valor = Utilidades.removeCifraoValor(etValor);

                ModeloProduto novoProduto = new ModeloProduto(nome, descricao, estoque, valor);

                novoProduto.save();

                Torradeira.shortToast(getString(R.string.cadastro_sucesso), this);

                setResult(RESULT_OK);
                finish();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Torradeira.erroToast(this);
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

        } else if (Utilidades.removeCifraoValor(etValor) < 0) {
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

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }
}
