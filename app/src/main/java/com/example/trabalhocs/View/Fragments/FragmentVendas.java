package com.example.trabalhocs.View.Fragments;

import android.animation.Animator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.trabalhocs.R;
import com.example.trabalhocs.View.CadastrarProdutoActivity;
import com.example.trabalhocs.View.CadastrarRecursoActivity;
import com.example.trabalhocs.View.RegistrarVendaActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentVendas extends Fragment {

    @BindView(R.id.btn_cadastrar_produto)
    AppCompatButton btnCadastrarProduto;

    @BindView(R.id.btn_cadastrar_recurso)
    AppCompatButton btnCadastrarRecurso;

    @BindView(R.id.fab_menu)
    FloatingActionButton fabMenu;

    @BindView(R.id.ll_fab_compra)
    LinearLayout llFabCompra;
    @BindView(R.id.tv_fab_compra)
    TextView tvFabCompra;
    @BindView(R.id.fab_compra)
    FloatingActionButton fabCompra;

    @BindView(R.id.ll_fab_venda)
    LinearLayout llFabVenda;
    @BindView(R.id.tv_fab_venda)
    TextView tvFabVenda;
    @BindView(R.id.fab_venda)
    FloatingActionButton fabVenda;

    private boolean isMostrandoMenu = false;

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

        config();

        return view;
    }

    private void config() {
        // TODO: 11/11/2019 implementar lógicas do fragment aqui
    }

    private void mostrarMenu() {

        llFabCompra.animate().translationY(-getContext().getResources().getDimension(R.dimen.fab_compra_offset)).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                tvFabCompra.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        llFabVenda.animate().translationY(-getContext().getResources().getDimension(R.dimen.fab_venda_offset)).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                tvFabVenda.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        isMostrandoMenu = true;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fabMenu.setBackgroundColor(getContext().getColor(R.color.vermelho));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fabMenu.setImageDrawable(getContext().getDrawable(R.drawable.ic_fechar));
        }
    }

    private void esconderMenu() {

        llFabCompra.animate().translationY(0).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                tvFabCompra.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        llFabVenda.animate().translationY(0).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                tvFabVenda.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        isMostrandoMenu = false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fabMenu.setBackgroundColor(getContext().getColor(R.color.colorAccent));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fabMenu.setImageDrawable(getContext().getDrawable(R.drawable.ic_fab_menu));
        }
    }

    @OnClick(R.id.fab_venda)
    void onClickFabVenda() {
        Intent intent = new Intent(getContext(), RegistrarVendaActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.fab_compra)
    void onClickFabCompra() {
        // TODO: 11/11/2019
    }

    @OnClick(R.id.fab_menu)
    void onClickFabMenu() {
        if (isMostrandoMenu) esconderMenu();
        else mostrarMenu();
    }

    @OnClick(R.id.btn_cadastrar_produto)
    void clickBtnCadastrarProduto() {
        Intent intent = new Intent(getContext(), CadastrarProdutoActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_cadastrar_recurso)
    void clickBtnCadastrarRecurso() {
        Intent intent = new Intent(getContext(), CadastrarRecursoActivity.class);
        startActivity(intent);
    }
}
