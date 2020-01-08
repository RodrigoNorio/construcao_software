package com.example.trabalhocs.View;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.Controller.ProdutoController;
import com.example.trabalhocs.Controller.RecursoController;
import com.example.trabalhocs.Model.ModeloProduto;
import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Constants;
import com.example.trabalhocs.Utils.Utilidades;
import com.example.trabalhocs.View.Dialogs.DialogAvisoVoltar;
import com.example.trabalhocs.View.Dialogs.DialogListaRecursos;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CadastrarModeloProdutoActivity extends AppCompatActivity implements ProdutoController.CadastrarModeloListener, RecursoController.CadastroModeloProdutoListener {

    @BindView(R.id.card_produto)
    CardView cardProduto;
    @BindView(R.id.tv_produto)
    TextView tvProduto;
    @BindView(R.id.img_trocar)
    ImageView imgTrocar;

    @BindView(R.id.titulo_card_recursos)
    TextView tvCardRecursos;
    @BindView(R.id.card_recursos)
    CardView cardRecursos;

    @BindView(R.id.rv_lista_ingredientes)
    RecyclerView rvListaCompras;

    @BindView(R.id.btn_add)
    AppCompatImageButton btnAdd;

    @BindView(R.id.btn_confirmar)
    AppCompatButton btnConfirmar;

    private RecursoController recursoController;
    private ProdutoController produtoController;

    private ModeloProduto produto;
    private boolean temItens = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cadastrar_modelo);
        ButterKnife.bind(this);

        config();
    }

    private void config() {
        recursoController = new RecursoController(this, Utilidades.getListaRecursosTeste());
        produtoController = new ProdutoController(this, Utilidades.getListaProdutosTeste());

    }

    @Override
    public void atualizaProduto(ModeloProduto produto) {
        this.produto = produto;

        this.tvCardRecursos.setText(produto.getNome());
        this.tvCardRecursos.setVisibility(View.VISIBLE);
        this.imgTrocar.setVisibility(View.VISIBLE);

        this.cardRecursos.setVisibility(View.VISIBLE);
    }

    private void abrirDialogListaProdutos() {
        // TODO: 07/01/2020
    }

    @OnClick(R.id.card_produto)
    void onClickCardProduto() {
        abrirDialogListaProdutos();
    }

    @Override
    public void atualizaListaIngredientes() {

    }

    private void abrirDialogListaRecursos() {
        DialogListaRecursos dialogListaRecursos = new DialogListaRecursos(this, recursoController.getRecursoList(), Constants.CADASTRO_MODELO_PRODUTO);
        final AlertDialog dialog = dialogListaRecursos.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @OnClick(R.id.btn_add)
    void onClickBtnAdd() {
        abrirDialogListaRecursos();
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
