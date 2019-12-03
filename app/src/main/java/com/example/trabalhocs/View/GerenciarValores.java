package com.example.trabalhocs.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.trabalhocs.Adapter.AdapterListaReceita;
import com.example.trabalhocs.Controller.ReceitaCtrl;
import com.example.trabalhocs.Model.ModeloReceita;
import com.example.trabalhocs.R;
import com.example.trabalhocs.dbhelper.ConexaoSQlite;

import androidx.appcompat.app.AppCompatActivity;

import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GerenciarValores extends AppCompatActivity {

    private ListView lsvReceitas;
    private List<ModeloReceita> receitasList;
    private AdapterListaReceita adapterListaReceita;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_valores);
        GerenciarReceita GerenciarReceita = new GerenciarReceita();
        final int selecionar = GerenciarReceita.selecionarfonte;
        listarreceitas(selecionar);

        SearchView searchView = (SearchView) findViewById(R.id.searchdata);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchContact(query, selecionar);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                searchContact(newText, selecionar);
                return true;
            }
        });
    }

    public void alertdialogeeditarreceitadata(final int cod_receita, final int selecionar, final String valor){
        final ReceitaCtrl receitaCtrl = new ReceitaCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarValores.this));
        final EditText fontetxtdata;
        AlertDialog.Builder builderdata = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        builderdata.setMessage("Digite o nova data da receita: ");
        fontetxtdata = new EditText(this);
        builderdata.setView(fontetxtdata);
        fontetxtdata.setInputType(InputType.TYPE_CLASS_DATETIME);
        //OK
        builderdata.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ModeloReceita receitaACadastrar = new ModeloReceita();
                receitaACadastrar.setCodreceita(cod_receita);
                receitaACadastrar.setValor(Float.parseFloat(valor));
                receitaACadastrar.setDate(fontetxtdata.getText().toString());
                receitaACadastrar.setCodfonte(selecionar);
                if (fontetxtdata.getText().length() != 0) {
                    if (verdata(fontetxtdata.getText().toString()) == true){
                        receitaCtrl.atualizarReceitaCtrl(receitaACadastrar);
                        listarreceitas(selecionar);
                        Toast.makeText(GerenciarValores.this, "Receita alterada com sucesso!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(GerenciarValores.this, "Data invalida!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else {
                    Toast.makeText(GerenciarValores.this, "Preencha o campo corretamente!", Toast.LENGTH_SHORT).show();
                    alertdialogeeditarreceitadata(cod_receita, selecionar, valor);
                }
            }
        });

        //CANCEL
        builderdata.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        final AlertDialog ad = builderdata.create();
        ad.show();
        fontetxtdata.setText("");
    }
    public boolean verdata(String data) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            sdf.parse(data);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    private void alertdialogeeditarreceita(final int cod_receita, final int selecionar) {
        final EditText fontetxtvalor;
        AlertDialog.Builder buildervalor = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        buildervalor.setMessage("Digite o novo valor da receita: ");
        fontetxtvalor = new EditText(this);
        buildervalor.setView(fontetxtvalor);
        fontetxtvalor.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        //OK
        buildervalor.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String valor;
                valor = fontetxtvalor.getText().toString();
                if (fontetxtvalor.getText().length() != 0) {
                    alertdialogeeditarreceitadata(cod_receita, selecionar, valor);
                } else {
                    Toast.makeText(GerenciarValores.this, "Preencha o campo corretamente!", Toast.LENGTH_SHORT).show();
                    alertdialogeeditarreceita(cod_receita, selecionar);
                }
            }
        });

        //CANCEL
        buildervalor.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        final AlertDialog ad = buildervalor.create();
        ad.show();
        fontetxtvalor.setText("");
    }

    private void alertdialogexcluirreceita(final int cod_receita, final int selecionar) {
        final ReceitaCtrl receitaCtrl = new ReceitaCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarValores.this));
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Deseja realmente excluir esta receita?");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                receitaCtrl.excluirReceitaCtrl(cod_receita);
                Toast.makeText(GerenciarValores.this, "Receita removida com sucesso!",Toast.LENGTH_SHORT).show();;
                listarreceitas(selecionar);
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

    private void searchContact(String keyword, int selecionar) {
        final ReceitaCtrl receitaCtrl = new ReceitaCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarValores.this));
        List<ModeloReceita> receitas = (List<ModeloReceita>) receitaCtrl.procurarControler(keyword,selecionar);
        if (receitas != null) {
            this.lsvReceitas = (ListView) findViewById(R.id.listagerenciarvalores);
            lsvReceitas.setAdapter(new AdapterListaReceita(getApplicationContext(),receitas));
        }
        else {
            this.lsvReceitas = (ListView) findViewById(R.id.listagerenciarvalores);
            lsvReceitas.setAdapter(null);
        }
    }

    public void listarreceitas(final int selecionar){

        //BUSCAR TODOS AS RECEITAS DO BANCO

        final ReceitaCtrl receitasCtrl = new ReceitaCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarValores.this));

        this.receitasList = new ArrayList<>();
        receitasList = receitasCtrl.getListaReceitaCtrl(selecionar);

        this.lsvReceitas = (ListView) findViewById(R.id.listagerenciarvalores);

        this.adapterListaReceita = new AdapterListaReceita(GerenciarValores.this, receitasList);

        this.lsvReceitas.setAdapter(this.adapterListaReceita);

        this.lsvReceitas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int posicao, long id) {

                adapterListaReceita.select(posicao);
                Button btnexcluir = findViewById(R.id.excluirval);
                Button btneditar = findViewById(R.id.editarval);

                ModeloReceita receitaSelecionada = (ModeloReceita) adapterListaReceita.getItem(posicao);
                final int cod_receita = receitaSelecionada.getCodreceita();
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
                });

            }
        });
    }

}
