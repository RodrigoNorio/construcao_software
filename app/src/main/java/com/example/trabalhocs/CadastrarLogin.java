package com.example.trabalhocs;

import android.content.Intent;
import android.os.Bundle;

import com.example.trabalhocs.Controller.LoginCtrl;
import com.example.trabalhocs.Model.ModeloLogin;
import com.example.trabalhocs.dbhelper.ConexaoSQlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CadastrarLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button blogin = (Button) findViewById(R.id.okcadastro);
        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarlogin();
            }
        });


    }
    public void cadastrarlogin(){
        TextView tLogin = (TextView) findViewById(R.id.novoid);
        TextView tSenha = (TextView) findViewById(R.id.senha);
        TextView tCsenha = (TextView) findViewById(R.id.csenha);
        String login = tLogin.getText().toString();
        String senha = tSenha.getText().toString();
        String csenha = tCsenha.getText().toString();
        if (login.equals("")){
            Toast.makeText(CadastrarLogin.this, "O campo login esta em branco", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (senha.equals("")){
            Toast.makeText(CadastrarLogin.this, "O campo senha esta em branco", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(csenha.equals("")){
            Toast.makeText(CadastrarLogin.this, "O campo confirmar senha esta em branco", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            if(!csenha.equals(senha)){
                Toast.makeText(CadastrarLogin.this, "Senhas incompativeis!", Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                LoginCtrl loginCtrl = new LoginCtrl(ConexaoSQlite.getInstanciaConexao(CadastrarLogin.this));
                if (loginCtrl.verificarLoginCtrl(login) == true){
                    ModeloLogin loginACadastrar = new ModeloLogin();
                    loginACadastrar.setUsuario(login);
                    loginACadastrar.setPassword(senha);
                    loginCtrl.salvarLoginCtrl(loginACadastrar);
                    Toast.makeText(CadastrarLogin.this, "Cadastro efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(CadastrarLogin.this, "Login já existente!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    public void voltartelalogin (View view){
        Intent it = new Intent (CadastrarLogin.this, Login.class);
        startActivity(it);
    }
}
