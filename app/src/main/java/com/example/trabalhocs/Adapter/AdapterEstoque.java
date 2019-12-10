package com.example.trabalhocs.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.Model.ModeloRecurso;
import com.example.trabalhocs.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterEstoque extends RecyclerView.Adapter<AdapterEstoque.CustomViewHolder> {

    private Context context;
    private List<ModeloRecurso> recursos;

    public AdapterEstoque(Context context, List<ModeloRecurso> recursos) {
        this.context = context;
        this.recursos = recursos;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.itemlist_recurso_estoque, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        // TODO: 10/12/2019 continuar aqui, pegar recurso e fazer o bind etc. 
    }

    @Override
    public int getItemCount() {
        return recursos != null ? recursos.size() : 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder  {
        @BindView(R.id.tv_nome)
        TextView tvNome;

        @BindView(R.id.tv_desc)
        TextView tvDesc;

        @BindView(R.id.tv_estoque)
        TextView tvEstoque;

        CustomViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
