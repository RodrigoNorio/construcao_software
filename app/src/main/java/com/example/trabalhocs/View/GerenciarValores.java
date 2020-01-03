package com.example.trabalhocs.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.trabalhocs.Adapter.AdapterListaReceita;
import com.example.trabalhocs.Controller.DestinoCtrl;
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
import java.util.Date;
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

        Button btnverificarfontemes = (Button) findViewById(R.id.verificarmes);
        btnverificarfontemes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertdialogverificarreceitames(selecionar);
            }
        });

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
    private void alertdialogverificarreceitames(int selecionar){
        final EditText receitatxt,receitatxt2;
        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        AlertDialog.Builder builder3 = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        builder.setMessage("Digite a data inicial que deseja verificar: ");
        receitatxt = new EditText(this);
        receitatxt2 = new EditText(this);
        receitatxt.setInputType(InputType.TYPE_CLASS_DATETIME);
        receitatxt2.setInputType(InputType.TYPE_CLASS_DATETIME);
        builder.setView(receitatxt);
        final ReceitaCtrl receitaCtrl = new ReceitaCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarValores.this));
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (verdata(receitatxt.getText().toString()) == true){
                    builder2.setMessage("Digite a data final que deseja verificar: ");
                    builder2.setView(receitatxt2);
                    builder2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (verdata(receitatxt2.getText().toString()) == true && verificarmenor(receitatxt.getText().toString(), receitatxt2.getText().toString()) == true){
                                builder3.setMessage("Seu depósito foi de " + receitaCtrl.verificartotalReceitaCtrl(receitatxt.getText().toString(),
                                        receitatxt2.getText().toString(), selecionar) + " reais, durante a data entre " + receitatxt.getText().toString() + " e "
                                        + receitatxt2.getText().toString());
                                builder3.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                final AlertDialog ad3 = builder3.create();
                                ad3.show();
                                receitatxt2.setText("");
                            }
                            else{
                                Toast.makeText(GerenciarValores.this, "Data invalida!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    });
                    //CANCEL
                    builder2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    final AlertDialog ad2 = builder2.create();
                    ad2.show();
                    receitatxt2.setText("");
                }
                else{
                    Toast.makeText(GerenciarValores.this, "Data invalida!", Toast.LENGTH_SHORT).show();
                    return;
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
        final AlertDialog ad = builder.create();
        ad.show();
        receitatxt.setText("");
    }

    private boolean verificarmenor(String data1, String data2){
        Date d1 = stringToDate(data1);
        Date d2 = stringToDate(data2);
        if (d1.compareTo(d2) > 0){
            return false;
        }
        else{
            return true;
        }
    }

    public Date stringToDate(String data1) {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        f.setLenient(false);
        java.util.Date d1 = null;
        try {
            d1 = f.parse(data1);
        } catch (ParseException e) {}
        return d1;
    }


}
