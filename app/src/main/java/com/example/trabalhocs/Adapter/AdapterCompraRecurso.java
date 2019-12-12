package com.example.trabalhocs.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.Model.ModeloRecurso;
import com.example.trabalhocs.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterCompraRecurso extends RecyclerView.Adapter<AdapterCompraRecurso.CustomViewHolder> {

    List<ModeloRecurso> recursoList; //ModeloRecursoView??

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_nome)
        TextView tvNome;

        @BindView(R.id.tv_qtd)
        TextView tvQtd;

        @BindView(R.id.btn_menos)
        AppCompatImageButton btnMenos;
        @BindView(R.id.btn_mais)
        AppCompatImageButton btnMais;

        @BindView(R.id.tv_estoque)
        TextView tvEstoque;

        CustomViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
