package com.example.trabalhocs.View;

import android.content.Intent;
import android.os.Bundle;

import com.example.trabalhocs.Controller.LoginCtrl;
import com.example.trabalhocs.MainActivity;
import com.example.trabalhocs.R;
import com.example.trabalhocs.dbhelper.ConexaoSQlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    public static int codusuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button logar = (Button) findViewById(R.id.logar);
        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logar();
            }
        });

    }
    public void logar(){
        TextView textlogar = (TextView) findViewById(R.id.textlogar);
        TextView textsenha = (TextView) findViewById(R.id.textsenha);
        String login = textlogar.getText().toString();
        String senha = textsenha.getText().toString();
        if (login.equals("")){
            Toast.makeText(Login.this, "O campo login esta em branco", Toast.LENGTH_SHORT).show();
            textlogar.setText("");
            textsenha.setText("");
            return;
        }
        else if (senha.equals("")){
            Toast.makeText(Login.this, "O campo senha esta em branco", Toast.LENGTH_SHORT).show();
            textsenha.setText("");
            return;
        }
        else{
            LoginCtrl loginCtrl = new LoginCtrl(ConexaoSQlite.getInstanciaConexao(Login.this));
            if(loginCtrl.verificarLoginCtrl(login) == true){
                textlogar.setText("");
                textsenha.setText("");
                Toast.makeText(Login.this, "Login não existente!", Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                if(loginCtrl.verificarLogineSenhaCtrl(login,senha) == true){
                    textsenha.setText("");
                    Toast.makeText(Login.this, "Senha incorreta!", Toast.LENGTH_SHORT).show();
                }
                else{
                    codusuario = loginCtrl.retornarcodCtrl(login,senha);
                    Toast.makeText(Login.this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                    textlogar.setText("");
                    textsenha.setText("");
                    Intent it = new Intent (Login.this, MainActivity.class);
                    startActivity(it);
                }
            }
        }
    }
    public void mudartelacadastro (View view){
        Intent it = new Intent (Login.this, CadastrarLogin.class);
        startActivity(it);
    }

}
