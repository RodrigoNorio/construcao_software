package com.example.trabalhocs.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.trabalhocs.Model.ModeloProduto;
import com.example.trabalhocs.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterInventarioSimplificado extends ArrayAdapter<ModeloProduto> {

    private Context context;
    private List<ModeloProduto> recursos;

    public AdapterInventarioSimplificado(@NonNull Context context, List<ModeloProduto> list) {
        super(context, 0, list);

        this.context = context;
        this.recursos = list;
    }

    @SuppressLint("DefaultLocale")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder itemHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.itemlist_produto_inventario_simplificado, parent, false);
            itemHolder = new ViewHolder(convertView);
            convertView.setTag(itemHolder);

        } else {
            itemHolder = (ViewHolder) convertView.getTag();
        }

        ModeloProduto item = recursos.get(position);

        itemHolder.tvNome.setText(item.getNome());
        itemHolder.tvEstoque.setText(item.getEstoque() + "x");

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_nome)
        TextView tvNome;

        @BindView(R.id.tv_estoque)
        TextView tvEstoque;

        ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
