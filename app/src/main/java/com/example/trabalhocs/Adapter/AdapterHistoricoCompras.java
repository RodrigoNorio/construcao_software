package com.example.trabalhocs.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.Model.ModeloCompra;
import com.example.trabalhocs.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterHistoricoCompras extends RecyclerView.Adapter<AdapterHistoricoCompras.CustomViewHolder> {

    private Context context;
    private List<ModeloCompra> compras;

    public AdapterHistoricoCompras(Context context, List<ModeloCompra> compras) {
        this.context = context;
        this.compras = compras;
    }

    @NonNull
    @Override
    public AdapterHistoricoCompras.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.itemlist_historico_compra, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHistoricoCompras.CustomViewHolder holder, int position) {
        final ModeloCompra compra = compras.get(position);

        holder.tvId.setText("#" + compra.getId());
        holder.tvDateTime.setText(compra.getDateTime());
        holder.tvDescricao.setText(compra.getTextoListaCompra(context));
        holder.tvValor.setText(compra.getValorTexto());
    }

    @Override
    public int getItemCount() {
        return compras != null ? compras.size() : 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_id)
        TextView tvId;

        @BindView(R.id.tv_date_time)
        TextView tvDateTime;

        @BindView(R.id.tv_desc)
        TextView tvDescricao;

        @BindView(R.id.tv_valor)
        TextView tvValor;

        CustomViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
