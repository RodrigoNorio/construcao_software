package com.example.trabalhocs.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.trabalhocs.Adapter.AdapterListaLogin;
import com.example.trabalhocs.Controller.GrupoCtrl;
import com.example.trabalhocs.Model.ModeloGrupo;
import com.example.trabalhocs.Model.ModeloLogin;
import com.example.trabalhocs.R;

import com.example.trabalhocs.dbhelper.ConexaoSQlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GerenciarValoresGrupo extends AppCompatActivity {
    private List<ModeloLogin> usuarioList;
    private ListView lsvUsuario;
    private AdapterListaLogin adapterListaLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_valores_grupo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final int selecionargrupo = GerenciarGrupo.selecionargrupo;
        listarusuarios(selecionargrupo);
        System.out.println(selecionargrupo);
        Button btngrupo = (Button) findViewById(R.id.adicionarusuario);
        btngrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addusuario(selecionargrupo);
            }
        });
    }
    public void addusuario(int selecionargrupo){
        final EditText fontetxt;
        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        builder.setMessage("Digite o nome do usuário: ");
        fontetxt = new EditText(this);
        builder.setView(fontetxt);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ModeloGrupo grupoACadastrar = new ModeloGrupo();
                GrupoCtrl grupoCtrl = new GrupoCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarValoresGrupo.this));

                if (grupoCtrl.verusuarioGruposCtrl(fontetxt.getText().toString()) != -1){
                    grupoACadastrar.setCod_grupo(selecionargrupo);
                    grupoACadastrar.setCod_pessoa(grupoCtrl.verusuarioGruposCtrl(fontetxt.getText().toString()));
                    Toast.makeText(GerenciarValoresGrupo.this, "Usuario adicionado com sucesso!", Toast.LENGTH_SHORT).show();
                    listarusuarios(selecionargrupo);
                }
                else {
                    Toast.makeText(GerenciarValoresGrupo.this, "Usuario Inexistente!", Toast.LENGTH_SHORT).show();
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

    public void listarusuarios(final int selecionar){

        final GrupoCtrl usuarioCtrl = new GrupoCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarValoresGrupo.this));

        this.usuarioList = new ArrayList<>();
        usuarioList = usuarioCtrl.getListaUsuarioDAO(selecionar);

        this.lsvUsuario = (ListView) findViewById(R.id.listgerenvaloresg);

        this.adapterListaLogin = new AdapterListaLogin(GerenciarValoresGrupo.this, usuarioList);

        this.lsvUsuario.setAdapter(this.adapterListaLogin);

        this.lsvUsuario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int posicao, long id) {

                adapterListaLogin.select(posicao);
                //Button btnexcluir = findViewById(R.id.excluirval);
                //Button btneditar = findViewById(R.id.editarval);

                //ModeloReceita receitaSelecionada = (ModeloReceita) adapterListaReceita.getItem(posicao);
                /*final int cod_receita = receitaSelecionada.getCodreceita();
                btnexcluir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertdialogexcluirreceita(cod_receita, selecionar);
                        listarreceitas(selecionar);
                    }
                });
                btneditar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertdialogeeditarreceita(cod_receita, selecionar);
                        listarreceitas(selecionar);
                    }
                });*/
            }
        });
    }
}
