package com.example.trabalhocs.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Utilidades;
import com.example.trabalhocs.View.Itens.ProdutoVendaItemView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterResumoVenda extends ArrayAdapter<ProdutoVendaItemView> {

    private Context context;
    private List<ProdutoVendaItemView> list;

    public AdapterResumoVenda(@NonNull Context context, List<ProdutoVendaItemView> list) {
        super(context, 0, list);

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder itemHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.itemlist_resumo_venda, parent, false);
            itemHolder = new ViewHolder(convertView);
            convertView.setTag(itemHolder);

        } else {
            itemHolder = (ViewHolder) convertView.getTag();
        }

        ProdutoVendaItemView item = list.get(position);

        itemHolder.tvNome.setText(item.getNomeProduto());
        itemHolder.tvQtd.setText("(x" + item.getQuantidade() + ")");
        itemHolder.tvValor.setText(Utilidades.formataReais(item.getValorVenda()));

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_nome)
        TextView tvNome;

        @BindView(R.id.tv_qtd)
        TextView tvQtd;

        @BindView(R.id.tv_valor)
        TextView tvValor;

        ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
