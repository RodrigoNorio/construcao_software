package com.example.trabalhocs.View;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalhocs.R;

import butterknife.ButterKnife;

/**
 * @author Bruno
 * @since 26/10/2019
 */
public class CadastrarProdutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cadastrar_produto);
        ButterKnife.bind(this);
    }
}
