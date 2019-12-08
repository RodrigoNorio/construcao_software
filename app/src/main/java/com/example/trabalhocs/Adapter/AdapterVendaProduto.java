package com.example.trabalhocs.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.Controller.VendaController;
import com.example.trabalhocs.Model.ModeloProduto;
import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Torradeira;
import com.example.trabalhocs.View.Dialogs.DialogVendaProduto;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterVendaProduto extends RecyclerView.Adapter<AdapterVendaProduto.CustomViewHolder> implements DialogVendaProduto.AddProdutoListener {

    private Context context;
    private List<ModeloProduto> produtos;
    private List<Integer> quantidades;

    private VendaController controller;
    private RecyclerView recyclerView;

    public AdapterVendaProduto(Context context, VendaController controller) {
        this.context = context;
        this.controller = controller;
        this.produtos = controller.getProdutosList();

        quantidades = new ArrayList<>();

        for (ModeloProduto p : produtos) {
            quantidades.add(0);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.itemlist_produto_venda, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        final ModeloProduto produto = produtos.get(position);
        final int qtdAtual = quantidades.get(position);

        holder.tvNome.setTag(position);
        holder.btnMenos.setTag(position);
        holder.btnMais.setTag(position);
        holder.tvQtd.setTag(position);
        holder.tvEstoque.setTag(position);

        holder.tvNome.setText(produto.getNome());
        holder.tvEstoque.setText(String.format("(%d)", produto.getEstoque()));

        holder.tvQtd.setText(String.format("%d", qtdAtual));

        //Click Listeners

        holder.btnMenos.setOnClickListener(v -> {
            if (qtdAtual > 0) {
                int pos = (int) v.getTag();
                ModeloProduto produtoAtualizar = produtos.get(pos);

                int qtdNova = qtdAtual - 1;
                quantidades.set(pos, qtdNova);
                holder.tvQtd.setText(String.format("%d", qtdNova));
                recyclerView.post(() -> notifyItemChanged(pos));

                controller.updateMap(produtoAtualizar.getId(), qtdNova);
            }
        });

        holder.btnMais.setOnClickListener(v -> {
            if (qtdAtual < produto.getEstoque()) {
                int pos = (int) v.getTag();
                ModeloProduto produtoAtualizar = produtos.get(pos);

                int qtdNova = qtdAtual + 1;
                quantidades.set(pos, qtdNova);
                holder.tvQtd.setText(String.format("%d", qtdNova));
                recyclerView.post(() -> notifyItemChanged(pos));

                controller.updateMap(produtoAtualizar.getId(), qtdNova);
            } else {
                alertaPassouEstoque();
            }
        });

        holder.tvNome.setOnClickListener(v -> {
            int pos = (int) v.getTag();
            abrirDialogProduto(produtos.get(pos), pos, quantidades.get(pos));
        });

        holder.tvQtd.setOnClickListener(v -> {
            int pos = (int) v.getTag();
            abrirDialogProduto(produtos.get(pos), pos, quantidades.get(pos));
        });

        holder.tvEstoque.setOnClickListener(v -> {
            int pos = (int) v.getTag();
            abrirDialogProduto(produtos.get(pos), pos, quantidades.get(pos));
        });
    }

    private void abrirDialogProduto(ModeloProduto produto, int posicao, int qtdAtual) {
        DialogVendaProduto dialogVendaProduto = new DialogVendaProduto(context, this, posicao, produto, qtdAtual);
        final AlertDialog dialog = dialogVendaProduto.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private void alertaPassouEstoque() {
        Torradeira.shortToast("quantidade não pode ser maior que o estoque!", context);
    }

    @Override
    public int getItemCount() {
        return produtos != null ? produtos.size() : 0;
    }

    @Override
    public void addProduto(int posicao, ModeloProduto produto, int quantidade) {
        controller.updateMap(produto.getId(), quantidade);
        quantidades.set(posicao, quantidade);
        notifyDataSetChanged();
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
