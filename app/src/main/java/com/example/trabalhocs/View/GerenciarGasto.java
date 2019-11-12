package com.example.trabalhocs.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.trabalhocs.Adapter.AdapterListaDestino;
import com.example.trabalhocs.Controller.DestinoCtrl;
import com.example.trabalhocs.Model.ModeloDestino;
import com.example.trabalhocs.R;
import com.example.trabalhocs.dbhelper.ConexaoSQlite;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GerenciarGasto extends AppCompatActivity {

    private ListView lsvDestino;
    private List<ModeloDestino> destinoList;
    private AdapterListaDestino adapterListaDestino;
    public static int selecionardestino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_gasto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        ConexaoSQlite conexaoSQlite = ConexaoSQlite.getInstanciaConexao(this);

        listardestinos();

        Button btndestino = (Button) findViewById(R.id.adicionardestinos);
        btndestino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertdialogadddestino();
            }
        });


        SearchView searchView = (SearchView) findViewById(R.id.buscardestinos);

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



    private void alertdialogadddestino() {
        final EditText destinotxt;
        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        builder.setMessage("Digite o nome do destino: ");
        destinotxt = new EditText(this);
        builder.setView(destinotxt);

        //OK
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ModeloDestino destinoACadastrar = new ModeloDestino();

                destinoACadastrar.setDescricao(destinotxt.getText().toString());

                if (destinotxt.getText().length() != 0){
                    DestinoCtrl destinoCtrl = new DestinoCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarGasto.this));
                    destinoCtrl.salvarDestinoCtrl(destinoACadastrar);
                    Toast.makeText(GerenciarGasto.this, "Destino cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    listardestinos();
                }
                else {
                    Toast.makeText(GerenciarGasto.this, "Preencha o campo corretamente!", Toast.LENGTH_SHORT).show();
                    alertdialogadddestino();
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
        destinotxt.setText("");
    }

    private void alertdialogeeditardestino(final int cod_destino) {
        final DestinoCtrl fonteCtrl = new DestinoCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarGasto.this));
        final EditText destinotxt;
        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        builder.setMessage("Digite o novo nome do destino: ");
        destinotxt = new EditText(this);
        builder.setView(destinotxt);

        //OK
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ModeloDestino destinoACadastrar = new ModeloDestino();
                destinoACadastrar.setCoddestino(cod_destino);
                destinoACadastrar.setDescricao(destinotxt.getText().toString());
                if (destinotxt.getText().length() != 0){
                    fonteCtrl.atualizarDestinoCtrl(destinoACadastrar);
                    Toast.makeText(GerenciarGasto.this, "Destino alterado com sucesso!", Toast.LENGTH_SHORT).show();
                    listardestinos();
                }
                else {
                    Toast.makeText(GerenciarGasto.this, "Preencha o campo corretamente!", Toast.LENGTH_SHORT).show();
                    alertdialogeeditardestino(cod_destino);
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
        destinotxt.setText("");
    }

    private void alertdialogexcluirdestino(final int cod_destino) {
        final DestinoCtrl fonteCtrl = new DestinoCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarGasto.this));
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final AlertDialog.Builder alert2 = new AlertDialog.Builder(this);
        alert.setMessage("Deseja realmente excluir este destino?");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alert2.setMessage("Se você excluir este destino, todos os gastos relacionadas irão ser excluidas, REALMENTE DESEJA EXCLUIR ESTE DESTINO?");
                alert2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        fonteCtrl.excluirDestinoCtrl(cod_destino);
                        Toast.makeText(GerenciarGasto.this, "Destino removido com sucesso!",Toast.LENGTH_SHORT).show();;
                        listardestinos();
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

    private void alertdialogverificargasto(final int cod_destino){
        selecionardestino = cod_destino;
        Intent it = new Intent (GerenciarGasto.this, GerenciarValores.class);
        startActivity(it);
    }

    public void listardestinos(){

        //BUSCAR TODOS OS DESTINOS DO BANCO

        final DestinoCtrl destinoCtrl = new DestinoCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarGasto.this));

        this.destinoList = new ArrayList<>();
        destinoList = destinoCtrl.getListaDestinoCtrl();

        this.lsvDestino = (ListView) findViewById(R.id.listardestinos);

        this.adapterListaDestino = new AdapterListaDestino(GerenciarGasto.this, destinoList);

        this.lsvDestino.setAdapter(this.adapterListaDestino);

        this.lsvDestino.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int posicao, long id) {

                adapterListaDestino.select(posicao);

                ModeloDestino destinoSelecionado = (ModeloDestino) adapterListaDestino.getItem(posicao);
                final int cod_destino = destinoSelecionado.getCoddestino();

                Button btnexcluir = findViewById(R.id.excluirdestinos);
                Button btneditar = findViewById(R.id.editardestinos);
                Button btnverificar = findViewById(R.id.verificardingastos);

                btnexcluir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertdialogexcluirdestino(cod_destino);
                    }
                });
                btneditar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertdialogeeditardestino(cod_destino);
                    }
                });
                btnverificar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertdialogverificargasto(cod_destino);
                    }
                });
            }
        });
    }

    private void searchContact(String keyword) {
        final DestinoCtrl destinoCtrl = new DestinoCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarGasto.this));
        List<ModeloDestino> destinos = (List<ModeloDestino>) destinoCtrl.procurarControler(keyword);
        if (destinos != null) {
            this.lsvDestino = (ListView) findViewById(R.id.listardestinos);
            lsvDestino.setAdapter(new AdapterListaDestino(getApplicationContext(),destinos));
        }
        else {
            this.lsvDestino = (ListView) findViewById(R.id.listardestinos);
            lsvDestino.setAdapter(null);
        }
    }



}
