package com.example.trabalhocs.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.trabalhocs.Adapter.AdapterListaGrupo;
import com.example.trabalhocs.Controller.GrupoCtrl;
import com.example.trabalhocs.Model.ModeloGrupo;
import com.example.trabalhocs.R;
import com.example.trabalhocs.dbhelper.ConexaoSQlite;

import java.util.ArrayList;
import java.util.List;

public class GerenciarGrupo extends AppCompatActivity {

    private ListView lsvGrupo;
    private List<ModeloGrupo> grupoList;
    private AdapterListaGrupo adapterListaGrupo;
    public static int selecionargrupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciargrupo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        ConexaoSQlite conexaoSQlite = ConexaoSQlite.getInstanciaConexao(this);
        listargrupos();
        Button btngrupo = (Button) findViewById(R.id.adicionargrupo);
        btngrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addgruponome();
            }
        });

        SearchView searchView = (SearchView) findViewById(R.id.buscargrupo);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchContact(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchContact(newText);
                return true;
            }
        });


    }

    public boolean verificartamanhostring(String s){
        if (s.length() > 0 && s.length() < 15){
            return true;
        }
        return false;
    }

    public void addgruponome(){
        final EditText grupo_nome;
        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        builder.setMessage("Digite o nome do grupo: ");
        grupo_nome = new EditText(this);
        builder.setView(grupo_nome);

        //OK
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ModeloGrupo grupocadastrar = new ModeloGrupo();

                grupocadastrar.setNome(grupo_nome.getText().toString());

                if (verificartamanhostring(grupo_nome.getText().toString())){
                    GrupoCtrl grupoCtrl = new GrupoCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarGrupo.this));
                    grupoCtrl.salvarGruposDAOCtrl(grupocadastrar);
                    Toast.makeText(GerenciarGrupo.this, "Grupo cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
                    listargrupos();
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
        grupo_nome.setText("");
    }

    private void listargrupos() {
        //BUSCAR TODOS OS GRUPOS DO BANCO

        final GrupoCtrl grupoCtrl = new GrupoCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarGrupo.this));

        this.grupoList = new ArrayList<>();
        grupoList = grupoCtrl.getListaGruposCtrl();

        this.lsvGrupo = (ListView) findViewById(R.id.listargrupos);

        this.adapterListaGrupo = new AdapterListaGrupo(GerenciarGrupo.this, grupoList);

        this.lsvGrupo.setAdapter(this.adapterListaGrupo);

        this.lsvGrupo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int posicao, long id) {

                adapterListaGrupo.select(posicao);
                ModeloGrupo grupoSelecionada = (ModeloGrupo) adapterListaGrupo.getItem(posicao);
                final int cod_grupo = posicao;

                Button btnexcluir = findViewById(R.id.excluirgrupo);
                Button btneditar = findViewById(R.id.editargrupo);
                Button btnverificar = findViewById(R.id.verificargrupo);


                btnexcluir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertdialogexcluirgrupo(cod_grupo);
                    }
                });
                /*btneditar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertdialogeeditargrupo(cod_grupo);
                    }
                });*/
                btnverificar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent it = new Intent (GerenciarGrupo.this, GerenciarValoresGrupo.class);
                        startActivity(it);
                    }
                });

            }
        });
    }

    /*private void alertdialogeeditargrupo(final int cod_fonte) {
        final GrupoCtrl grupoCtrl = new GrupoCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarGrupo.this));
        final EditText fontetxt;
        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        builder.setMessage("Digite o novo nome da fonte(até 15 caracteres): ");
        fontetxt = new EditText(this);
        builder.setView(fontetxt);

        //OK
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ModeloFonte fonteACadastrar = new ModeloFonte();
                fonteACadastrar.setCodfonte(cod_fonte);
                fonteACadastrar.setDescricao(fontetxt.getText().toString());
                if (verificartamanhostring(fontetxt.getText().toString())){
                    fonteCtrl.atualizarFonteCtrl(fonteACadastrar);
                    Toast.makeText(GerenciarReceita.this, "Fonte alterada com sucesso!", Toast.LENGTH_SHORT).show();
                    listarfontes();
                }
                else {
                    Toast.makeText(GerenciarReceita.this, "Preencha o campo corretamente!", Toast.LENGTH_SHORT).show();
                    alertdialogeeditarfonte(cod_fonte);
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
    }*/

    private void alertdialogexcluirgrupo(final int cod_grupo) {
        final GrupoCtrl grupoCtrl = new GrupoCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarGrupo.this));
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final AlertDialog.Builder alert2 = new AlertDialog.Builder(this);
        alert.setMessage("Deseja exluir este Grupo?");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alert2.setMessage("Se você excluir este grupo, todas as suas informações serão removidas, REALMENTE DESEJA EXCLUIR ESTE GRUPO?");
                alert2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        grupoCtrl.excluirGrupoCtrl(cod_grupo);
                        Toast.makeText(GerenciarGrupo.this, "Fonte removida com sucesso!",Toast.LENGTH_SHORT).show();;
                        listargrupos();
                    }
                });
                alert2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert2.create().show();
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.create().show();
    }

    private void searchContact(String keyword) {
        final GrupoCtrl grupoCtrl = new GrupoCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarGrupo.this));
        List<ModeloGrupo> grupos = (List<ModeloGrupo>) grupoCtrl.procurarControler(keyword);
        if (grupos != null) {
            this.lsvGrupo = (ListView) findViewById(R.id.listargrupos);
            lsvGrupo.setAdapter(new AdapterListaGrupo(getApplicationContext(),grupos));
        }
        else {
            this.lsvGrupo = (ListView) findViewById(R.id.listargrupos);
            lsvGrupo.setAdapter(null);
        }
    }



}
