package com.example.trabalhocs.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.trabalhocs.Model.ModeloLogin;
import com.example.trabalhocs.R;

import java.util.List;

public class AdapterListaLogin extends BaseAdapter {

    private Context context;
    private List<ModeloLogin> grupoList;
    private int selected = -1;

    public AdapterListaLogin(Context context, List<ModeloLogin> grupoList) {
        this.context = context;
        this.grupoList = grupoList;
    }

    @Override
    public int getCount() {
        return this.grupoList.size();
    }

    @Override
    public Object getItem(int posicao) {
        return this.grupoList.get(posicao);
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

        View v = View.inflate(this.context, R.layout.layout_listarusuarios,null);

        TextView tvnomeUsuario = v.findViewById(R.id.listarusuarios);

        if(selected != -1 && posicao == selected) {
            tvnomeUsuario.setBackgroundColor(Color.parseColor("#59C065"));
        }
        tvnomeUsuario.setText(this.grupoList.get(posicao).getUsuario());

        return v;
    }

}