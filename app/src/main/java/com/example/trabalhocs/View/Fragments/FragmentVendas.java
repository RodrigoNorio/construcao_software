package com.example.trabalhocs.View.Fragments;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.trabalhocs.Adapter.AdapterEstoqueSimplificado;
import com.example.trabalhocs.Adapter.AdapterInventarioSimplificado;
import com.example.trabalhocs.Model.ModeloProduto;
import com.example.trabalhocs.Model.ModeloRecurso;
import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Utilidades;
import com.example.trabalhocs.View.EstoqueActivity;
import com.example.trabalhocs.View.FabricarProdutoActivity;
import com.example.trabalhocs.View.InventarioActivity;
import com.example.trabalhocs.View.RegistrarCompraActivity;
import com.example.trabalhocs.View.RegistrarVendaActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentVendas extends Fragment {

    @BindView(R.id.tv_lista_vazia_produtos)
    TextView tvProdutosVazio;
    @BindView(R.id.lv_produtos)
    ListView lvProdutos;

    @BindView(R.id.tv_lista_vazia_recursos)
    TextView tvRecursosVazio;
    @BindView(R.id.lv_recursos)
    ListView lvRecursos;

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

    @BindView(R.id.ll_fab_fabricacao)
    LinearLayout llFabFabricacao;
    @BindView(R.id.tv_fab_fabricacao)
    TextView tvFabFabricacao;
    @BindView(R.id.fab_fabricacao)
    FloatingActionButton fabFabricacao;

    private boolean isMostrandoMenu = false;

    private AdapterEstoqueSimplificado adapterEstoqueSimplificado;
    private AdapterInventarioSimplificado adapterInventarioSimplificado;

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
        Context context = getContext();

        if (context != null) {

            //Card produtos
            List<ModeloProduto> listaPreviaProdutos = new ArrayList<>(Utilidades.getListaProdutosTeste()); // TODO: 05/01/2020 mudar aqui

            if (!listaPreviaProdutos.isEmpty()) {
                if (listaPreviaProdutos.size() > 10) {
                    listaPreviaProdutos.subList(9,listaPreviaProdutos.size()).clear();
                }

                adapterInventarioSimplificado = new AdapterInventarioSimplificado(context, listaPreviaProdutos);
                lvProdutos.setAdapter(adapterInventarioSimplificado);

                tvProdutosVazio.setVisibility(View.GONE);
                lvProdutos.setVisibility(View.VISIBLE);

            } else {
                lvProdutos.setVisibility(View.GONE);
                tvProdutosVazio.setVisibility(View.VISIBLE);
            }

            //Card recursos
            List<ModeloRecurso> listaPreviaRecursos = new ArrayList<>(Utilidades.getListaRecursosTeste()); // TODO: 05/01/2020 mudar aqui

            if (!listaPreviaRecursos.isEmpty()) {
                if (listaPreviaRecursos.size() > 10) {
                    listaPreviaRecursos.subList(9,listaPreviaRecursos.size()).clear();
                }

                adapterEstoqueSimplificado = new AdapterEstoqueSimplificado(context, listaPreviaRecursos);
                lvRecursos.setAdapter(adapterEstoqueSimplificado);

                tvRecursosVazio.setVisibility(View.GONE);
                lvRecursos.setVisibility(View.VISIBLE);

            } else {
                lvRecursos.setVisibility(View.GONE);
                tvRecursosVazio.setVisibility(View.VISIBLE);
            }

        }

    }

    private void mostrarMenu() {

        llFabCompra.animate().translationY(-getContext().getResources().getDimension(R.dimen.fab_compra_offset)).alpha(1f).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                llFabCompra.setVisibility(View.VISIBLE);
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

        llFabVenda.animate().translationY(-getContext().getResources().getDimension(R.dimen.fab_venda_offset)).alpha(1f).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                llFabVenda.setVisibility(View.VISIBLE);
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

        llFabFabricacao.animate().translationY(-getContext().getResources().getDimension(R.dimen.fab_fabricacao_offset)).alpha(1f).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                llFabFabricacao.setVisibility(View.VISIBLE);
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

        isMostrandoMenu = true;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fabMenu.setImageDrawable(getContext().getDrawable(R.drawable.ic_fechar));
        }
    }

    private void esconderMenu() {

        llFabCompra.animate().translationY(0).alpha(0f).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                llFabCompra.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        llFabVenda.animate().translationY(0).alpha(0f).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                llFabVenda.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        llFabFabricacao.animate().translationY(0).alpha(0f).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                llFabFabricacao.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        isMostrandoMenu = false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fabMenu.setImageDrawable(getContext().getDrawable(R.drawable.ic_fab_menu));
        }
    }

    @OnClick(R.id.fab_fabricacao)
    void onClickFabFabricacao() {
        Intent intent = new Intent(getContext(), FabricarProdutoActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.fab_venda)
    void onClickFabVenda() {
        Intent intent = new Intent(getContext(), RegistrarVendaActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.fab_compra)
    void onClickFabCompra() {
        Intent intent = new Intent(getContext(), RegistrarCompraActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.fab_menu)
    void onClickFabMenu() {
        if (isMostrandoMenu) esconderMenu();
        else mostrarMenu();
    }

//    @OnClick(R.id.btn_cadastrar_produto)
//    void clickBtnCadastrarProduto() {
//        Intent intent = new Intent(getContext(), CadastrarProdutoActivity.class);
//        startActivity(intent);
//    }

    @OnClick(R.id.card_produtos)
    void onClickCardProdutos() {
        Intent intent = new Intent(getContext(), InventarioActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.card_recursos)
    void onClickCardRecursos() {
        Intent intent = new Intent(getContext(), EstoqueActivity.class);
        startActivity(intent);
    }
}
