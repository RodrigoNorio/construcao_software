package com.example.trabalhocs.View.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.Adapter.AdapterHistoricoCompras;
import com.example.trabalhocs.Controller.HistoricoController;
import com.example.trabalhocs.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentHistoricoCompras extends FragmentConfiguravel {

    @BindView(R.id.rv_historico)
    RecyclerView rvHistorico;

    @BindView(R.id.tv_historico_vazio)
    TextView tvHistoricoVazio;

    Context context;
    HistoricoController controller;
    AdapterHistoricoCompras adapterHistoricoCompras;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.fragment_historico_compras, container, false);
        ButterKnife.bind(this, view);

        return  view;
    }

    @Override
    public void config(Context context, HistoricoController controller) {
        this.context = context;
        this.controller = controller;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setup();
    }

    private void setup() {
        rvHistorico.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));

        controller.updateComprasList(context);

        if (controller.isComprasListEmpty()) {
            rvHistorico.setVisibility(View.GONE);
            tvHistoricoVazio.setVisibility(View.VISIBLE);

        } else {
            adapterHistoricoCompras = new AdapterHistoricoCompras(context, controller.getComprasList());
            rvHistorico.setAdapter(adapterHistoricoCompras);
            rvHistorico.setVisibility(View.VISIBLE);

            tvHistoricoVazio.setVisibility(View.GONE);
        }

    }

}
