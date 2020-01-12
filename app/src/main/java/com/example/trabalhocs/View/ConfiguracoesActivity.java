package com.example.trabalhocs.View;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalhocs.R;
import com.example.trabalhocs.Utils.Torradeira;
import com.example.trabalhocs.Utils.Utilidades;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfiguracoesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_configuracoes);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_dados_teste)
    void onClickBtnDadosTeste() {
        // TODO: 11/01/2020

        Utilidades.geraProdutosTeste();
        Utilidades.geraRecursosTeste();
        Utilidades.geraModeloFabricacaoProdutoTeste(this);
        Torradeira.shortToast("O banco de dados foi atualizado com dados de teste!", this);
    }

    @OnClick(R.id.btn_limpar_bd)
    void onClickBtnLimparBd() {
        // TODO: 11/01/2020

        Utilidades.limpaDadosProdutos();
        Utilidades.limpaDadosRecursos();
        Utilidades.limpaDadosModelosFabricacao();
        Torradeira.shortToast("Todos os dados do banco de dados foram apagados!", this);
    }

    @OnClick(R.id.btn_voltar)
    void onClickBtnVoltar() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        finish();
    }
}
