package com.example.trabalhocs.View.Dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import com.example.trabalhocs.Controller.RecursoController;
import com.example.trabalhocs.Model.ModeloRecurso;
import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Torradeira;
import com.example.trabalhocs.Utils.Utilidades;
import com.example.trabalhocs.View.Itens.RecursoAdicionarIngredienteItemView;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogAdicionarIngrediente extends AlertDialog.Builder {

    @BindView(R.id.tv_nome)
    TextView tvNome;

    @BindView(R.id.et_quantidade)
    TextInputEditText etQuantidade;

    @BindView(R.id.tv_medida)
    TextView tvMedida;

    @BindView(R.id.btn_confirmar)
    AppCompatButton btnConfirmar;

    private AlertDialog dialog;

    private Context context;
    private ModeloRecurso recurso;

    public DialogAdicionarIngrediente(Context context, ModeloRecurso recurso) {
        super(context);

        this.context = context;
        this.recurso = recurso;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_adiciona_ingrediente, null);
        setCancelable(false);

        ButterKnife.bind(this, view);

        config();

        setView(view);
    }

    @Override
    public AlertDialog create() {
        this.dialog = super.create();
        return this.dialog;
    }

    private void config() {
        tvNome.setText(recurso.getNome());
        tvMedida.setText(Utilidades.getMedidaText(context, recurso.getTipoMedida()));
    }

    @OnClick(R.id.btn_confirmar)
    void onClickBtnConfirmar() {

        if (etQuantidade.getText().toString().isEmpty()) {
            Torradeira.longToast("insira uma quantidade",context);
            return;
        }

        try {
            int quantidade = Integer.parseInt(etQuantidade.getText().toString());

            RecursoAdicionarIngredienteItemView recursoAdicionarIngredienteItemView = new RecursoAdicionarIngredienteItemView(recurso, quantidade);

            RecursoController.getInstance().addRecursoIngrediente(recursoAdicionarIngredienteItemView);
            dialog.dismiss();

        } catch (Exception e) {
            Torradeira.erroToast(context);
        }
    }

    @OnClick(R.id.btn_cancelar)
    void onClickBtnCancelar() {
        dialog.dismiss();
    }
}
