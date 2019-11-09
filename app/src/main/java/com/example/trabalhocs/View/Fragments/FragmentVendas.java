package com.example.trabalhocs.View.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.trabalhocs.R;
import com.example.trabalhocs.View.CadastrarProdutoActivity;
import com.example.trabalhocs.View.CadastrarRecursoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentVendas extends Fragment {

    @BindView(R.id.btn_cadastrar_produto)
    AppCompatButton btnCadastrarProduto;

    @BindView(R.id.btn_cadastrar_recurso)
    AppCompatButton btnCadastrarRecurso;

    public FragmentVendas() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vendas, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.btn_cadastrar_produto)
    void clickBtnCadastrarProduto() {
//        Intent intent = new Intent(getContext(), CadastrarProdutoActivity.class);
//        startActivity(intent);
    }

    @OnClick(R.id.btn_cadastrar_recurso)
    void clickBtnCadastrarRecurso() {
        Intent intent = new Intent(getContext(), CadastrarRecursoActivity.class);
        startActivity(intent);
    }
}
