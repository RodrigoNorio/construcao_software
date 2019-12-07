package com.example.trabalhocs.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
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

        holder.tvNome.setText(produto.getNome());
        holder.tvEstoque.setText(String.format("(%d)", produto.getEstoque()));

        holder.tvQtd.setText(String.format("%d", qtdAtual));

        holder.tvNome.setOnClickListener(v -> {
            int pos = (int) v.getTag();
            abrirDialogProduto(produtos.get(pos));
        });

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

//        holder.tvQtd.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) { }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                int qtdInput = !s.toString().equals("") ? Integer.parseInt(s.toString()) : 0;
//
//                if (qtdInput < 0) {
//                    holder.tvQtd.removeTextChangedListener(this);
//                    holder.tvQtd.setText("0");
//                    holder.tvQtd.addTextChangedListener(this);
//
//                    Torradeira.shortToast("quantidade inválida!", context);
//
//                    quantidades.set(position, 0);
//                    controller.updateMap(produto.getId(), 0);
//
//                } else if (qtdInput > produto.getEstoque()) {
//                    holder.tvQtd.removeTextChangedListener(this);
//                    holder.tvQtd.setText(String.format("%d", produto.getEstoque()));
//                    holder.tvQtd.addTextChangedListener(this);
//
//                    alertaPassouEstoque();
//
//                    quantidades.set(position, produto.getEstoque());
//                    controller.updateMap(produto.getId(), produto.getEstoque());
//
//                } else {
//                    quantidades.set(position, qtdInput);
//                    controller.updateMap(produto.getId(), qtdInput);
//                }
//            }
//        });
    }

    private void abrirDialogProduto(ModeloProduto produto) {
        DialogVendaProduto dialogVendaProduto = new DialogVendaProduto(context, this, produto);
        final AlertDialog dialog = dialogVendaProduto.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Log.d("rapidash", "abrirDialogProduto: " + dialog);
    }

    private void alertaPassouEstoque() {
        Torradeira.shortToast("quantidade não pode ser maior que o estoque!", context);
    }

    @Override
    public int getItemCount() {
        return produtos != null ? produtos.size() : 0;
    }

    @Override
    public void addProduto(ModeloProduto produto, int quantidade) {
        controller.updateMap(produto.getId(), quantidade);

        // TODO: 06/12/2019 atualizar Array de quantidades na posição certa e o adapter tbm sei lá
//        int pos = 0;
//        notifyItemChanged(pos);0



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
