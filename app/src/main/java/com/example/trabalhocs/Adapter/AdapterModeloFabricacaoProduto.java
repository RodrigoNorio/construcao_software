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
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.Model.ModeloFabricacaoProduto;
import com.example.trabalhocs.R;
import com.example.trabalhocs.View.Dialogs.DialogFabricacaoProduto;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterModeloFabricacaoProduto extends RecyclerView.Adapter<AdapterModeloFabricacaoProduto.CustomViewHolder> {

    private Context context;
    private List<ModeloFabricacaoProduto> lista;

    public AdapterModeloFabricacaoProduto(Context context, List<ModeloFabricacaoProduto> lista) {
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.itemlist_modelo_fabricacao_produto, parent, false);
        return new CustomViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        final ModeloFabricacaoProduto modeloFabricacaoProduto = lista.get(position);

        holder.tvId.setText("#" + modeloFabricacaoProduto.getId());
        holder.tvNome.setText(modeloFabricacaoProduto.getProduto().getNome());
        holder.tvListaIngredientes.setText(modeloFabricacaoProduto.getListaIngredientesSimplificada());
        holder.tvProducao.setText(String.format("Produz %d unidades", modeloFabricacaoProduto.getQuantidade()));

        holder.itemView.setOnClickListener(v -> abrirDialogFabricao(modeloFabricacaoProduto));
    }

    private void abrirDialogFabricao(ModeloFabricacaoProduto modeloFabricacaoProduto) {
        DialogFabricacaoProduto dialogFabricacaoProduto = new DialogFabricacaoProduto(context, modeloFabricacaoProduto);
        final AlertDialog dialog = dialogFabricacaoProduto.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public int getItemCount() {
        return lista != null ? lista.size() : 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_id)
        TextView tvId;

        @BindView(R.id.tv_nome)
        TextView tvNome;

        @BindView(R.id.tv_lista_ingredientes)
        TextView tvListaIngredientes;

        @BindView(R.id.tv_producao)
        TextView tvProducao;

        CustomViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
