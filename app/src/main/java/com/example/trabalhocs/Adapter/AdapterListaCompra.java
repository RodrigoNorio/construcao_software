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
import com.example.trabalhocs.View.Itens.RecursoCompraItemView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterListaCompra extends RecyclerView.Adapter<AdapterListaCompra.CustomViewHolder> {

    private Context context;
    private RecursoController controller;
    private List<RecursoCompraItemView> recursoCompraItemViewList;

    public AdapterListaCompra(Context context, RecursoController controller) {
        this.context = context;
        this.controller = controller;
        this.recursoCompraItemViewList = controller.getCompraList();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.itemlist_recurso_compra_resumo, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        RecursoCompraItemView recursoCompraItemView = recursoCompraItemViewList.get(position);

        holder.tvNome.setText(recursoCompraItemView.getRecurso().getNome());
        holder.tvQuantidade.setText(recursoCompraItemView.getQuantidadeText(context));
        holder.tvValor.setText(recursoCompraItemView.getValorText());

        holder.btnRemover.setOnClickListener(v -> controller.removeRecursoCompra(recursoCompraItemView));
    }

    @Override
    public int getItemCount() {
        return recursoCompraItemViewList != null ?recursoCompraItemViewList.size() : 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_nome)
        TextView tvNome;

        @BindView(R.id.tv_quantidade)
        TextView tvQuantidade;

        @BindView(R.id.tv_valor)
        TextView tvValor;

        @BindView(R.id.btn_remover)
        AppCompatImageButton btnRemover;

        CustomViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
