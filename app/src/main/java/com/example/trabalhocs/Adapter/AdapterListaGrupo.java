package com.example.trabalhocs.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.trabalhocs.Model.ModeloGrupo;
import com.example.trabalhocs.R;

import java.util.List;

public class AdapterListaGrupo extends BaseAdapter {

    private Context context;
    private List<ModeloGrupo> grupoList;
    private int selected = -1;

    public AdapterListaGrupo(Context context, List<ModeloGrupo> grupoList) {
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

        View v = View.inflate(this.context, R.layout.layout_listargrupos,null);

        TextView tvnomeGrupo = v.findViewById(R.id.listargrupos);

        if(selected != -1 && posicao == selected) {
            tvnomeGrupo.setBackgroundColor(Color.parseColor("#59C065"));
        }
        tvnomeGrupo.setText(this.grupoList.get(posicao).getNome());

        return v;
    }

}