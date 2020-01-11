package com.example.trabalhocs.View;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.trabalhocs.Model.ModeloRecurso;
import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Constants;
import com.example.trabalhocs.Utils.Torradeira;
import com.example.trabalhocs.Utils.Utilidades;
import com.google.android.material.textfield.TextInputEditText;
import com.jaredrummler.materialspinner.MaterialSpinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Bruno
 * @since 26/10/2019
 */
public class CadastrarRecursoActivity extends AppCompatActivity {

    @BindView(R.id.et_nome)
    TextInputEditText etNome;

    @BindView(R.id.et_descricao)
    TextInputEditText etDescricao;

    @BindView(R.id.spinner_medida)
    MaterialSpinner spinnerMedida;

    @BindView(R.id.et_estoque)
    TextInputEditText etEstoque;

    @BindView(R.id.tv_medida_inventario)
    TextView tvMedidaInventario;

    @BindView(R.id.btn_cadastrar)
    AppCompatButton btnCadastrar;

    int positionMedidaSelecionada = Constants.SELECAO_VAZIA;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cadastrar_recurso);
        ButterKnife.bind(this);

        config();
    }

    private void config() {

        // config spinnerMedida
        spinnerMedida.setItems(Utilidades.getMedidasList(this));
        spinnerMedida.setOnItemSelectedListener((MaterialSpinner.OnItemSelectedListener<String>)
                (view, position, id, item) -> {
                    Torradeira.shortToast("medida selecionada", this);
                    tvMedidaInventario.setText(item);
                    positionMedidaSelecionada = position;
                    if (tvMedidaInventario.getVisibility() == View.INVISIBLE) tvMedidaInventario.setVisibility(View.VISIBLE);
                });

        positionMedidaSelecionada = spinnerMedida.getSelectedIndex();
    }

    @OnClick(R.id.btn_cadastrar)
    void onClickBtnCdastrar() {
        try {

            if (isCamposValidos()) {
                
                String nome = etNome.getText().toString();
                String descricao = etDescricao.getText().toString();
                int quantidade = Integer.parseInt(etEstoque.getText().toString());

                ModeloRecurso novoRecurso = new ModeloRecurso(13, nome, descricao, positionMedidaSelecionada, quantidade);

                // TODO: 11/01/2020 Salvar no recurso no banco
                
                Torradeira.shortToast("cadastrou: " + novoRecurso.toString(), this);
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

        if (positionMedidaSelecionada == Constants.SELECAO_VAZIA) {
            Torradeira.shortToast(getString(R.string.erro_un_medida), this);
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
