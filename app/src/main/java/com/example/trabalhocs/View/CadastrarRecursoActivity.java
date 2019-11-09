package com.example.trabalhocs.View;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

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

    @BindView(R.id.et_inventario)
    TextInputEditText etInventario;

    @BindView(R.id.tv_medida_inventario)
    TextView tvMedidaInventario;

    @BindView(R.id.btn_cadastrar)
    AppCompatButton btnCadastrar;

    int positionMedidaSelecionada = -1;

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
    }

    @OnClick(R.id.btn_cadastrar)
    void onClickBtnCdastrar() {

        if (!isCamposValidos()) {
            piscarBotaoCadastro();

        } else {
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

        if (positionMedidaSelecionada == Constants.SELECAO_VAZIA) {
            Torradeira.shortToast(getString(R.string.erro_un_medida), this);
            isValido = false;
        }

        if (etInventario.getText().toString().isEmpty()) {
            Torradeira.longToast(getString(R.string.error_inventario_vazio), this);
            isValido = false;

        } else if (Integer.parseInt(etInventario.getText().toString()) < 0) {
            Torradeira.longToast(getString(R.string.error_inventario_invalido), this);
            isValido = false;
        }

        return isValido;
    }

    private void piscarBotaoCadastro() {
        btnCadastrar.setEnabled(false);

        ObjectAnimator avermelhar = ObjectAnimator.ofObject(btnCadastrar, "backgroundColor",
                new ArgbEvaluator(), getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.vermelho));

        avermelhar.setDuration(300);
        avermelhar.setStartDelay(50);
        avermelhar.start();

        ObjectAnimator esverdear = ObjectAnimator.ofObject(btnCadastrar, "backgroundColor",
                new ArgbEvaluator(), getResources().getColor(R.color.vermelho), getResources().getColor(R.color.colorPrimary));

        avermelhar.setDuration(300);
        avermelhar.setStartDelay(350);
        avermelhar.start();

        btnCadastrar.setEnabled(true);
    }
}
