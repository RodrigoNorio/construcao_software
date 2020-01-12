package com.example.trabalhocs.View.Dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.Adapter.AdapterListaRecursosModeloProduto;
import com.example.trabalhocs.Controller.RecursoController;
import com.example.trabalhocs.Model.ModeloFabricacaoProduto;
import com.example.trabalhocs.Model.ModeloProduto;
import com.example.trabalhocs.Model.ModeloRecurso;
import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Torradeira;
import com.example.trabalhocs.View.Itens.RecursoAdicionarIngredienteItemView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogFabricacaoProduto extends AlertDialog.Builder {

    @BindView(R.id.tv_nome)
    TextView tvNome;

    @BindView(R.id.tv_estoque_atual)
    TextView tvEstoqueAtual;

    @BindView(R.id.tv_desc)
    TextView tvDesc;

    @BindView(R.id.rv_lista_ingredientes)
    RecyclerView rvListaIngredientes;

    @BindView(R.id.tv_producao)
    TextView tvProducao;

    private AlertDialog dialog;

    private Context context;
    private ModeloFabricacaoProduto modeloFabricacaoProduto;
    private RecursoController recursoController;
    private AdapterListaRecursosModeloProduto adapterListaRecursosModeloProduto;

    public DialogFabricacaoProduto(Context context, ModeloFabricacaoProduto modeloFabricacaoProduto) {
        super(context);

        this.context = context;
        this.modeloFabricacaoProduto = modeloFabricacaoProduto;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_fabricacao_produto, null);
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

    @SuppressLint("DefaultLocale")
    private void config() {
        ModeloProduto produto = modeloFabricacaoProduto.getProduto();

        tvNome.setText(produto.getNome());
        tvEstoqueAtual.setText(String.format("%s %s", context.getResources().getString(R.string.estoque_atual), produto.getEstoque()));
        tvDesc.setText(produto.getDescricao());

        tvProducao.setText(String.format("Produz %d unidades do produto", modeloFabricacaoProduto.getQuantidade()));

        // Lista de recursos e quantidades
        List<ModeloRecurso> recursoList = new ArrayList<>();
        List<RecursoAdicionarIngredienteItemView> ingredientes = new ArrayList<>();

        for (Map.Entry<ModeloRecurso, Integer> entrada : modeloFabricacaoProduto.getMapIngredientes().entrySet()) {
            recursoList.add(entrada.getKey());
            ingredientes.add(new RecursoAdicionarIngredienteItemView(entrada.getKey(), entrada.getValue()));
        }

        recursoController = new RecursoController();
        recursoController.addListaRecursoIngrediente(ingredientes);

        adapterListaRecursosModeloProduto = new AdapterListaRecursosModeloProduto(context, recursoController, false);

        rvListaIngredientes.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        rvListaIngredientes.setAdapter(adapterListaRecursosModeloProduto);
    }

    @OnClick(R.id.btn_confirmar)
    void onClickBtnConfirmar() {
        // TODO: 11/01/2020
        String msg = "Fabricação de " + modeloFabricacaoProduto.getQuantidade() + " unidades de " + modeloFabricacaoProduto.getProduto().getNome() + " registrada com sucesso!";
        Torradeira.longToast(msg, context);
        dialog.dismiss();
    }

    @OnClick(R.id.btn_cancelar)
    void onClickBtnCancelar() {
        dialog.dismiss();
    }
}
