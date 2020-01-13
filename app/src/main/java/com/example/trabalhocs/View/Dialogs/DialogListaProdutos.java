package com.example.trabalhocs.View.Dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.Adapter.AdapterListaProdutos;
import com.example.trabalhocs.Controller.ProdutoController;
import com.example.trabalhocs.Model.ModeloProduto;
import com.example.trabalhocs.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogListaProdutos extends AlertDialog.Builder implements AdapterListaProdutos.AdapterCadastroModeloProduto {

    @BindView(R.id.rv_produtos)
    RecyclerView rvProdutos;

    @BindView(R.id.btn_fechar)
    AppCompatImageButton btnFechar;

    private AlertDialog dialog;

    private Context context;
    private List<ModeloProduto> produtoList;
    private int origemID; //Constants
    private ProdutoController produtoController;

    public DialogListaProdutos(Context context, List<ModeloProduto> produtoList, int origemID, ProdutoController produtoController) {
        super(context);
        this.context = context;
        this.produtoList = produtoList;
        this.origemID = origemID;
        this.produtoController = produtoController;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_lista_produtos, null);
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
        AdapterListaProdutos adapterListaRecursos = new AdapterListaProdutos(produtoList, this, context, origemID);

        rvProdutos.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        rvProdutos.setAdapter(adapterListaRecursos);
    }

    @OnClick(R.id.btn_fechar)
    void onClickBtnFechar() {
        dialog.dismiss();
    }

    @Override
    public void fecharDialog(ModeloProduto produto) {
        produtoController.setaProduto(produto);
        dialog.dismiss();
    }
}
