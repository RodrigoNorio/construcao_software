package com.example.trabalhocs.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.trabalhocs.Model.ModeloReceita;
import com.example.trabalhocs.R;

import java.util.List;

public class AdapterListaReceita extends BaseAdapter {

    private Context context;
    private List<ModeloReceita> receitaList;
    private int selected = -1;

    public AdapterListaReceita(Context context, List<ModeloReceita> fonteList) {
        this.context = context;
        this.receitaList = fonteList;
    }

    @Override
    public int getCount() {
        return this.receitaList.size();
    }

    @Override
    public Object getItem(int posicao) {
        return this.receitaList.get(posicao);
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

        View v = View.inflate(this.context, R.layout.layout_listarvaloresedatas,null);

        TextView tvValorreceitas = v.findViewById(R.id.listarvalores);
        TextView tvDatareceita = v.findViewById(R.id.listardata);

        if(selected != -1 && posicao == selected) {
            tvValorreceitas.setBackgroundColor(Color.parseColor("#59C065"));
            tvDatareceita.setBackgroundColor(Color.parseColor("#59C065"));
        }
        tvValorreceitas.setText(Float.toString(this.receitaList.get(posicao).getValor()));
        tvDatareceita.setText(this.receitaList.get(posicao).getDate());

        return v;
    }

}
