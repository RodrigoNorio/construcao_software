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

import com.example.trabalhocs.Adapter.AdapterHistoricoVendas;
import com.example.trabalhocs.Controller.HistoricoController;
import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Utilidades;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentHistoricoVendas extends FragmentConfiguravel {

    @BindView(R.id.rv_historico)
    RecyclerView rvHistorico;

    @BindView(R.id.tv_historico_vazio)
    TextView tvHistoricoVazio;

    Context context;
    HistoricoController controller;
    AdapterHistoricoVendas adapterHistoricoVendas;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.fragment_historico_vendas, container, false);
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

        controller.setVendasList(Utilidades.getListaModeloVendaTeste()); //teste

        if (controller.isVendasListEmpty()) {
            rvHistorico.setVisibility(View.GONE);
            tvHistoricoVazio.setVisibility(View.VISIBLE);

        } else {
            AdapterHistoricoVendas adapterHistoricoVendas = new AdapterHistoricoVendas(controller.getVendasList());
            rvHistorico.setAdapter(adapterHistoricoVendas);
            rvHistorico.setVisibility(View.VISIBLE);

            tvHistoricoVazio.setVisibility(View.GONE);
        }

    }

}
