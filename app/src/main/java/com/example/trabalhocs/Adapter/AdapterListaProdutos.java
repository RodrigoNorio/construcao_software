package com.example.trabalhocs.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.Model.ModeloProduto;
import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterListaProdutos extends RecyclerView.Adapter<AdapterListaProdutos.CustomViewHolder> {

    private Context context;
    private AdapterCadastroModeloProduto listener;
    private List<ModeloProduto> produtoList;
    private int origemID; //Constants

    public AdapterListaProdutos(List<ModeloProduto> produtos,
                                AdapterCadastroModeloProduto listener,
                                Context context,
                                int origemID) {
        this.context = context;
        this.listener = listener;
        this.produtoList = produtos;
        this.origemID = origemID;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.itemlist_nome_simples, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        final ModeloProduto produto = produtoList.get(position);

        holder.tvNome.setText(produto.getNome());

        holder.itemView.setOnClickListener(v -> {
             if (origemID == Constants.CADASTRO_MODELO_PRODUTO){
                listener.fecharDialog(produto);
            }
        });
    }



    @Override
    public int getItemCount() {
        return produtoList != null ? produtoList.size() : 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_nome)
        TextView tvNome;

        CustomViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface AdapterCadastroModeloProduto {
        void fecharDialog(ModeloProduto produto);
    }
}
