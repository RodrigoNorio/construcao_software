package com.example.trabalhocs.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.trabalhocs.Model.ModeloFonte;
import com.example.trabalhocs.R;

import java.util.List;

public class AdapterListaFonte extends BaseAdapter {

    private Context context;
    private List<ModeloFonte> fonteList;
    private int selected = -1;

    public AdapterListaFonte(Context context, List<ModeloFonte> fonteList) {
        this.context = context;
        this.fonteList = fonteList;
    }

    @Override
    public int getCount() {
        return this.fonteList.size();
    }

    @Override
    public Object getItem(int posicao) {
        return this.fonteList.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return posicao;
    }


    public void select(int position) {
        this.selected = position;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent) {

        View v = View.inflate(this.context, R.layout.layout_listarfontes,null);

        TextView tvDescricaoFonte = v.findViewById(R.id.listarfontes);

        if(selected != -1 && posicao == selected) {
            tvDescricaoFonte.setBackgroundColor(Color.parseColor("#59C065"));
        }
        tvDescricaoFonte.setText(this.fonteList.get(posicao).getDescricao());

        return v;
    }
}
