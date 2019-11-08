package com.example.trabalhocs.View;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalhocs.R;
import com.google.android.material.textfield.TextInputEditText;
import com.jaredrummler.materialspinner.MaterialSpinner;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cadastrar_recurso);
        ButterKnife.bind(this);
    }
}
