package com.example.trabalhocs.View;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.trabalhocs.Adapter.TabAdapter;
import com.example.trabalhocs.Controller.HistoricoController;
import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Constants;
import com.example.trabalhocs.View.Dialogs.DialogAjuda;
import com.example.trabalhocs.View.Fragments.FragmentHistoricoCompras;
import com.example.trabalhocs.View.Fragments.FragmentHistoricoVendas;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistoricoActivity extends AppCompatActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private FragmentHistoricoVendas fragmentHistoricoVendas;
    private FragmentHistoricoCompras fragmentHistoricoCompras;

    private TabAdapter adapter;

    private HistoricoController historicoController;
    private Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historicos);
        ButterKnife.bind(this);

        config();
    }

    private void config() {
        historicoController = new HistoricoController();
        adapter = new TabAdapter(getSupportFragmentManager());

        fragmentHistoricoVendas = new FragmentHistoricoVendas();
        fragmentHistoricoCompras = new FragmentHistoricoCompras();

        adapter.addFragment(fragmentHistoricoVendas, "vendas");
        adapter.addFragment(fragmentHistoricoCompras, "compras");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        adapter.config(this, historicoController);
    }

    @OnClick(R.id.btn_ajuda)
    void onClickBtnAjuda() {
        final DialogAjuda dialogAjuda = new DialogAjuda(this, Constants.AJUDA_HISTORICO);
        final AlertDialog dialog = dialogAjuda.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @OnClick(R.id.btn_voltar)
    void onClickBtnVoltar() {
        onBackPressed();
    }
}
