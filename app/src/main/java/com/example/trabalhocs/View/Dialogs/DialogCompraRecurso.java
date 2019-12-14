package com.example.trabalhocs.View.Dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import com.example.trabalhocs.Model.ModeloRecurso;
import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Torradeira;
import com.example.trabalhocs.Utils.Utilidades;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogCompraRecurso extends AlertDialog.Builder {

    @BindView(R.id.tv_nome)
    TextView tvNome;

    @BindView(R.id.tv_estoque_atual)
    TextView tvEstoqueAtual;

    @BindView(R.id.et_quantidade)
    TextInputEditText etQuantidade;

    @BindView(R.id.tv_medida)
    TextView tvMedida;

    @BindView(R.id.et_valor)
    TextInputEditText etValor;

    @BindView(R.id.btn_confirmar)
    AppCompatButton btnConfirmar;

    private AlertDialog dialog;

    private Context context;
    private ModeloRecurso recurso;

    public DialogCompraRecurso(Context context, ModeloRecurso recurso) {
        super(context);
        this.context = context;
        this.recurso = recurso;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_compra_recurso, null);
        setCancelable(true);

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

        tvEstoqueAtual.setText(String.format("%s %s", context.getResources().getString(R.string.estoque_atual), recurso.getTextoEstoque(context)));

        tvMedida.setText(Utilidades.getMedidaText(context, recurso.getTipoMedida()));
    }

    @OnClick(R.id.btn_confirmar)
    void onClickBtnConfirmar() {
        Torradeira.shortToast("clicou no confirmar", context);
        // TODO: 14/12/2019
    }

    @OnClick(R.id.btn_cancelar)
    void onClickBtnCancelar() {
        dialog.dismiss();
    }

    public interface AddRecursoListener {
        void addRecurso(ModeloRecurso recurso, int quantidade);
    }
}
