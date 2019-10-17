package com.example.trabalhocs.View;


import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.example.trabalhocs.Adapter.AdapterListaFonte;
import com.example.trabalhocs.Controller.FonteCtrl;
import com.example.trabalhocs.Model.ModeloFonte;
import com.example.trabalhocs.R;
import com.example.trabalhocs.dbhelper.ConexaoSQlite;

import java.util.ArrayList;
import java.util.List;

public class GerenciarReceita extends AppCompatActivity {

    private ListView lsvFontes;
    private List<ModeloFonte> fonteList;
    private AdapterListaFonte adapterListaFonte;
    public static int selecionarfonte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_receita);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        ConexaoSQlite conexaoSQlite = ConexaoSQlite.getInstanciaConexao(this);

        listarfontes();
        Button btnfonte = (Button) findViewById(R.id.adicionarfonte);
        btnfonte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertdialogaddfonte();
            }
        });


        SearchView searchView = (SearchView) findViewById(R.id.buscarfontes);

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

    private void alertdialogaddfonte() {
        final EditText fontetxt;
        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        builder.setMessage("Digite o nome da fonte: ");
        fontetxt = new EditText(this);
        builder.setView(fontetxt);

        //OK
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ModeloFonte fonteACadastrar = new ModeloFonte();

                fonteACadastrar.setDescricao(fontetxt.getText().toString());

                if (fontetxt.getText().length() != 0){
                    FonteCtrl fonteCtrl = new FonteCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarReceita.this));
                    fonteCtrl.salvarFonteCtrl(fonteACadastrar);
                    Toast.makeText(GerenciarReceita.this, "Fonte cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
                    listarfontes();
                }
                else {
                    Toast.makeText(GerenciarReceita.this, "Preencha o campo corretamente!", Toast.LENGTH_SHORT).show();
                    alertdialogaddfonte();
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

    private void alertdialogeeditarfonte(final int cod_fonte) {
        final FonteCtrl fonteCtrl = new FonteCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarReceita.this));
        final EditText fontetxt;
        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        builder.setMessage("Digite o novo nome da fonte: ");
        fontetxt = new EditText(this);
        builder.setView(fontetxt);

        //OK
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ModeloFonte fonteACadastrar = new ModeloFonte();
                fonteACadastrar.setCodfonte(cod_fonte);
                fonteACadastrar.setDescricao(fontetxt.getText().toString());
                if (fontetxt.getText().length() != 0){
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
    }

    private void alertdialogexcluirfonte(final int cod_fonte) {
        final FonteCtrl fonteCtrl = new FonteCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarReceita.this));
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Deseja realmente exluir esta fonte?");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fonteCtrl.excluirFonteCtrl(cod_fonte);
                Toast.makeText(GerenciarReceita.this, "Fonte removida com sucesso!",Toast.LENGTH_SHORT).show();;
                listarfontes();
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

    private void alertdialogverificardin(final int cod_fonte){
        selecionarfonte = cod_fonte;
        //Intent it = new Intent (GerenciarReceita.this, GerenciarValores.class);
        //startActivity(it);
    }

    public void listarfontes(){

        //BUSCAR TODOS AS FONTES DO BANCO

        final FonteCtrl fonteCtrl = new FonteCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarReceita.this));

        this.fonteList = new ArrayList<>();
        fonteList = fonteCtrl.getListaFontesCtrl();

        this.lsvFontes = (ListView) findViewById(R.id.listarfontes);

        this.adapterListaFonte = new AdapterListaFonte(GerenciarReceita.this, fonteList);

        this.lsvFontes.setAdapter(this.adapterListaFonte);

        this.lsvFontes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int posicao, long id) {

                adapterListaFonte.select(posicao);

                ModeloFonte fonteSelecionada = (ModeloFonte) adapterListaFonte.getItem(posicao);
                final int cod_fonte = fonteSelecionada.getCodfonte();

                Button btnexcluir = findViewById(R.id.excluirfonte);
                Button btneditar = findViewById(R.id.editarfonte);
                Button btnverificar = findViewById(R.id.verificardin);

                btnexcluir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertdialogexcluirfonte(cod_fonte);
                    }
                });
                btneditar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertdialogeeditarfonte(cod_fonte);
                    }
                });
                btnverificar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertdialogverificardin(cod_fonte);
                    }
                });
            }
        });
    }

    private void searchContact(String keyword) {
        final FonteCtrl fonteCtrl = new FonteCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarReceita.this));
        List<ModeloFonte> fontes = (List<ModeloFonte>) fonteCtrl.procurarControler(keyword);
        if (fontes != null) {
            this.lsvFontes = (ListView) findViewById(R.id.listarfontes);
            lsvFontes.setAdapter(new AdapterListaFonte(getApplicationContext(),fontes));
        }
        else {
            this.lsvFontes = (ListView) findViewById(R.id.listarfontes);
            lsvFontes.setAdapter(null);
        }
    }

}
