package com.example.trabalhocs.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.Model.ModeloProduto;
import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Torradeira;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterVendaProduto extends RecyclerView.Adapter<AdapterVendaProduto.CustomViewHolder> {

    private Context context;
    private List<ModeloProduto> produtos;
    private List<Integer> quantidades;

    private RecyclerView recyclerView;

    public AdapterVendaProduto(Context context, List<ModeloProduto> produtos) {
        this.context = context;
        this.produtos = produtos;

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

        holder.tvNome.setText(produto.getNome());
        holder.tvEstoque.setText(String.format("(%d)", produto.getEstoque()));

        holder.etQtd.setText(String.format("%d", qtdAtual));

        holder.btnMenos.setOnClickListener(v -> {
            if (qtdAtual > 0) {
                int qtdNova = qtdAtual - 1;
                quantidades.set(position, qtdNova);
                holder.etQtd.setText(String.format("%d", qtdNova));
                recyclerView.post(() -> notifyItemChanged(position));
            }
        });

        holder.btnMais.setOnClickListener(v -> {
            if (qtdAtual < produto.getEstoque()) {
                int qtdNova = qtdAtual + 1;
                quantidades.set(position, qtdNova);
                holder.etQtd.setText(String.format("%d", qtdNova));
                recyclerView.post(() -> notifyItemChanged(position));
            } else {
                alertaPassouEstoque();
            }
        });

        holder.etQtd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                int qtdInput = !s.toString().equals("") ? Integer.parseInt(s.toString()) : 0;

                if (qtdInput < 0) {
                    holder.etQtd.removeTextChangedListener(this);
                    holder.etQtd.setText("0");
                    holder.etQtd.addTextChangedListener(this);

                    Torradeira.shortToast("quantidade inválida!", context);

                    quantidades.set(position, 0);
                    recyclerView.post(() -> notifyItemChanged(position));

                } else if (qtdInput > produto.getEstoque()) {
                    holder.etQtd.removeTextChangedListener(this);
                    holder.etQtd.setText(String.format("%d", produto.getEstoque()));
                    holder.etQtd.addTextChangedListener(this);

                    alertaPassouEstoque();

                    quantidades.set(position, produto.getEstoque());
                    recyclerView.post(() -> notifyItemChanged(position));

                } else {
                    quantidades.set(position, qtdInput);
                    recyclerView.post(() -> notifyItemChanged(position));
                }
            }
        });
    }

    private void alertaPassouEstoque() {
        Torradeira.shortToast("a quantidade não pode ser maior que o estoque!", context);
    }

    @Override
    public int getItemCount() {
        return produtos != null ? produtos.size() : 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_nome)
        TextView tvNome;

        @BindView(R.id.et_qtd)
        AppCompatEditText etQtd;

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
