package com.example.trabalhocs.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.trabalhocs.Model.ModeloDestino;
import com.example.trabalhocs.R;

import java.util.List;

public class AdapterListaDestino extends BaseAdapter {

    private Context context;
    private List<ModeloDestino> destinoList;
    private int selected = -1;

    public AdapterListaDestino(Context context, List<ModeloDestino> destinoList) {
        this.context = context;
        this.destinoList = destinoList;
    }

    @Override
    public int getCount() {
        return this.destinoList.size();
    }

    @Override
    public Object getItem(int posicao) {
        return this.destinoList.get(posicao);
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

        View v = View.inflate(this.context, R.layout.layout_listardestino,null);

        TextView tvDescricaoDestino = v.findViewById(R.id.listardestinos);

        if(selected != -1 && posicao == selected) {
            tvDescricaoDestino.setBackgroundColor(Color.parseColor("#59C065"));
        }
        tvDescricaoDestino.setText(this.destinoList.get(posicao).getDescricao());

        return v;
    }
}
