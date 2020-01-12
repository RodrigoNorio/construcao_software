package com.example.trabalhocs.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.Controller.RecursoController;
import com.example.trabalhocs.R;
import com.example.trabalhocs.View.Itens.RecursoAdicionarIngredienteItemView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterListaRecursosModeloProduto extends RecyclerView.Adapter<AdapterListaRecursosModeloProduto.CustomViewHolder> {

    private Context context;
    private RecursoController controller;
    private List<RecursoAdicionarIngredienteItemView> recursoAdicionarIngredienteItemViews;
    private boolean podeRemover;

    public AdapterListaRecursosModeloProduto(Context context, RecursoController controller, boolean podeRemover) {
        this.context = context;
        this.controller = controller;
        this.podeRemover = podeRemover;
         this.recursoAdicionarIngredienteItemViews = controller.getIngredientesList();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.itemlist_recurso_modelo_produto, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        RecursoAdicionarIngredienteItemView recursoAdicionarIngredienteItemView = recursoAdicionarIngredienteItemViews.get(position);

        holder.tvNome.setText(recursoAdicionarIngredienteItemView.getRecurso().getNome());
        holder.tvQuantidade.setText(recursoAdicionarIngredienteItemView.getQuantidadeText(context));

        if (podeRemover) {
            holder.btnRemover.setOnClickListener(v -> controller.removeRecursoIngrediente(recursoAdicionarIngredienteItemView));
            holder.btnRemover.setVisibility(View.VISIBLE);
        } else {
            holder.btnRemover.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return recursoAdicionarIngredienteItemViews != null ?recursoAdicionarIngredienteItemViews.size() : 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_nome)
        TextView tvNome;

        @BindView(R.id.tv_quantidade)
        TextView tvQuantidade;

        @BindView(R.id.btn_remover)
        AppCompatImageButton btnRemover;

        CustomViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
