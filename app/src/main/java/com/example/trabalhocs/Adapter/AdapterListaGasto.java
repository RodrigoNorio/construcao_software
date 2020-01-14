package com.example.trabalhocs.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.trabalhocs.Model.ModeloGasto;
import com.example.trabalhocs.R;

import java.util.List;

public class AdapterListaGasto extends BaseAdapter {

    private Context context;
    private List<ModeloGasto> gastoList;
    private int selected = -1;

    public AdapterListaGasto(Context context, List<ModeloGasto> destinoList) {
        this.context = context;
        this.gastoList = destinoList;
    }

    @Override
    public int getCount() {
        return this.gastoList.size();
    }

    @Override
    public Object getItem(int posicao) {
        return this.gastoList.get(posicao);
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

        View v = View.inflate(this.context, R.layout.layout_listarvaloresedatasgastos,null);

        TextView tvValorrgastos = v.findViewById(R.id.listarvaloresgasto);
        TextView tvDatagasto = v.findViewById(R.id.listardatagasto);

        if(selected != -1 && posicao == selected) {
            tvValorrgastos.setBackgroundColor(Color.parseColor("#59C065"));
            tvDatagasto.setBackgroundColor(Color.parseColor("#59C065"));
        }
        tvValorrgastos.setText(Float.toString(this.gastoList.get(posicao).getValor()));
        tvDatagasto.setText(this.gastoList.get(posicao).getDate());

        return v;
    }


}
