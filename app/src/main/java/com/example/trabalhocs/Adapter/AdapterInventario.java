package com.example.trabalhocs.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.Model.ModeloProduto;
import com.example.trabalhocs.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterInventario extends RecyclerView.Adapter<AdapterInventario.CustomViewHolder> {

    private Context context;
    private List<ModeloProduto> produtos;

    public AdapterInventario(Context context, List<ModeloProduto> produtos) {
        this.context = context;
        this.produtos = produtos;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.itemlist_produto_inventario, parent, false);
        return new CustomViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        final ModeloProduto produto = produtos.get(position);

        holder.tvId.setText("#" + produto.getId());
        holder.tvNome.setText(produto.getNome());
        holder.tvDescricao.setText(produto.getDescricao());
        holder.tvEstoque.setText(String.format("%d unidades em estoque", produto.getEstoque()));
        holder.tvValor.setText(produto.getValorUnitarioText());
    }

    @Override
    public int getItemCount() {
        return produtos != null ? produtos.size() : 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_id)
        TextView tvId;

        @BindView(R.id.tv_nome)
        TextView tvNome;

        @BindView(R.id.tv_desc)
        TextView tvDescricao;

        @BindView(R.id.tv_estoque)
        TextView tvEstoque;

        @BindView(R.id.tv_valor)
        TextView tvValor;

        CustomViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
