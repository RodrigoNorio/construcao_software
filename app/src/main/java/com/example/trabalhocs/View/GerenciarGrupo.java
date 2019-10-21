package com.example.trabalhocs.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.trabalhocs.Controller.FonteCtrl;
import com.example.trabalhocs.Controller.GrupoCtrl;
import com.example.trabalhocs.Model.ModeloFonte;
import com.example.trabalhocs.Model.ModeloGrupo;
import com.example.trabalhocs.R;
import com.example.trabalhocs.dbhelper.ConexaoSQlite;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GerenciarGrupo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciargrupo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btngrupo = (Button) findViewById(R.id.adicionargrupo);
        btngrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addgruponome();
            }
        });

    }

    public void addgruponome(){
        final EditText fontetxt;
        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        builder.setMessage("Digite o nome do grupo: ");
        fontetxt = new EditText(this);
        builder.setView(fontetxt);

        //OK
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ModeloGrupo grupocadastrar = new ModeloGrupo();

                grupocadastrar.setNome(fontetxt.getText().toString());

                if (fontetxt.getText().length() != 0){
                    GrupoCtrl grupoCtrl = new GrupoCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarGrupo.this));
                    grupoCtrl.salvarGruposDAOCtrl(grupocadastrar);
                    Toast.makeText(GerenciarGrupo.this, "Grupo cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
                    //listarfontes();
                }
                else {
                    Toast.makeText(GerenciarGrupo.this, "Preencha o campo corretamente!", Toast.LENGTH_SHORT).show();
                    addgruponome();
                }
            }
        });

        //CANCEL
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        //CRIAR DIALOG
        final AlertDialog ad = builder.create();
        ad.show();
        fontetxt.setText("");
    }





}
