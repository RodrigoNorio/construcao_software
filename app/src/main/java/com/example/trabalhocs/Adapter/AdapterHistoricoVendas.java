package com.example.trabalhocs.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.Model.ModeloVenda;
import com.example.trabalhocs.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterHistoricoVendas extends RecyclerView.Adapter<AdapterHistoricoVendas.CustomViewHolder> {

    private List<ModeloVenda> vendas;

    public AdapterHistoricoVendas(List<ModeloVenda> vendas) {
        this.vendas = vendas;
    }

    @NonNull
    @Override
    public AdapterHistoricoVendas.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.itemlist_historico_venda, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHistoricoVendas.CustomViewHolder holder, int position) {
        final ModeloVenda venda = vendas.get(position);

        holder.tvId.setText("#" + venda.getId());
        holder.tvDateTime.setText(venda.getDateTime());
        holder.tvDescricao.setText(venda.getTextoListaProdutos());
        holder.tvQuantidadeTotal.setText(venda.getTextoQuantidadeProdutos());
        holder.tvValor.setText(venda.getTextoValorVenda());
    }

    @Override
    public int getItemCount() {
        return vendas != null ? vendas.size() : 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_id)
        TextView tvId;

        @BindView(R.id.tv_date_time)
        TextView tvDateTime;

        @BindView(R.id.tv_desc)
        TextView tvDescricao;

        @BindView(R.id.tv_quantidade_total)
        TextView tvQuantidadeTotal;

        @BindView(R.id.tv_valor)
        TextView tvValor;

        CustomViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
