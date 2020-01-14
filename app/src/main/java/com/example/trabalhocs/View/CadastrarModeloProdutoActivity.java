package com.example.trabalhocs.View;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.Adapter.AdapterListaRecursosModeloProduto;
import com.example.trabalhocs.Controller.ProdutoController;
import com.example.trabalhocs.Controller.RecursoController;
import com.example.trabalhocs.Model.ModeloFabricacaoProduto;
import com.example.trabalhocs.Model.ModeloProduto;
import com.example.trabalhocs.Model.ModeloRecurso;
import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Constants;
import com.example.trabalhocs.Utils.Torradeira;
import com.example.trabalhocs.View.Dialogs.DialogAjuda;
import com.example.trabalhocs.View.Dialogs.DialogAvisoVoltar;
import com.example.trabalhocs.View.Dialogs.DialogListaProdutos;
import com.example.trabalhocs.View.Dialogs.DialogListaRecursos;
import com.example.trabalhocs.View.Itens.RecursoAdicionarIngredienteItemView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @BindView(R.id.tv_lista_vazia)
    TextView tvListaVazia;

    @BindView(R.id.rv_lista_ingredientes)
    RecyclerView rvListaCompras;

    @BindView(R.id.ll_producao)
    LinearLayout llProducao;

    @BindView(R.id.et_quantidade)
    EditText etQuantidade;

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
        recursoController = new RecursoController(this);
        produtoController = new ProdutoController(this);

        rvListaCompras.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        etQuantidade.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                verificaCondicaoBotaoCadastrar();
            }
        });
    }

    @Override
    public void atualizaProduto(ModeloProduto produto) {
        this.produto = produto;

        this.tvProduto.setText(produto.getNome());
        this.tvCardRecursos.setVisibility(View.VISIBLE);
        this.imgTrocar.setVisibility(View.VISIBLE);

        this.cardRecursos.setVisibility(View.VISIBLE);

        this.llProducao.setVisibility(View.VISIBLE);
    }

    private void abrirDialogListaProdutos() {
        DialogListaProdutos dialogListaProdutos = new DialogListaProdutos(this, produtoController.getProdutosList(), Constants.CADASTRO_MODELO_PRODUTO, produtoController);
        final AlertDialog dialog = dialogListaProdutos.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @OnClick(R.id.card_produto)
    void onClickCardProduto() {
        abrirDialogListaProdutos();
    }

    @Override
    public void atualizaListaIngredientes() {
        AdapterListaRecursosModeloProduto adapterListaRecursosModeloProduto = new AdapterListaRecursosModeloProduto(this, recursoController, true);
        rvListaCompras.setAdapter(adapterListaRecursosModeloProduto);

        List<RecursoAdicionarIngredienteItemView> listaIngredientes = recursoController.getIngredientesList();

        if (listaIngredientes.isEmpty()) {
            rvListaCompras.setVisibility(View.GONE);
            tvListaVazia.setVisibility(View.VISIBLE);
        } else {
            tvListaVazia.setVisibility(View.GONE);
            rvListaCompras.setVisibility(View.VISIBLE);
        }

        verificaCondicaoBotaoCadastrar();
    }

    private void verificaCondicaoBotaoCadastrar() {
        List<RecursoAdicionarIngredienteItemView> listaIngredientes = recursoController.getIngredientesList();

        if (listaIngredientes.isEmpty() || !validaQuantidadeProducao()) {
            btnConfirmar.setEnabled(false);
            temItens = false;

        } else if (validaQuantidadeProducao()) {
            btnConfirmar.setEnabled(true);
            temItens = true;
        }
    }

    private boolean validaQuantidadeProducao() {
        try {
            return !etQuantidade.getText().toString().isEmpty() && Integer.parseInt(etQuantidade.getText().toString()) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @OnClick(R.id.btn_confirmar)
    void onClickBtnConfirmar() {

        try {
            List<RecursoAdicionarIngredienteItemView> listaIngredientes = recursoController.getIngredientesList();

            Map<ModeloRecurso, Integer> mapIngredientesTeste = new HashMap<>();

            for (RecursoAdicionarIngredienteItemView ingrediente : listaIngredientes) {
                mapIngredientesTeste.put(ingrediente.getRecurso(), ingrediente.getQuantidade());
            }

            ModeloFabricacaoProduto modelo = new ModeloFabricacaoProduto(produto, mapIngredientesTeste, Integer.parseInt(etQuantidade.getText().toString()));
            modelo.save();

            Torradeira.shortToast(getString(R.string.cadastro_sucesso), this);
            setResult(RESULT_OK);
            finish();

        } catch (Exception e) {
            e.printStackTrace();
            Torradeira.erroToast(this);
        }
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
            setResult(RESULT_CANCELED);
            finish();
        });
    }

    @OnClick(R.id.btn_ajuda)
    void onClickBtnAjuda() {
        final DialogAjuda dialogAjuda = new DialogAjuda(this, Constants.AJUDA_CADASTRAR_MODELO);
        final AlertDialog dialog = dialogAjuda.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
