package com.example.trabalhocs.View;

import android.content.DialogInterface;
import android.os.Bundle;

import com.example.trabalhocs.Adapter.AdapterListaGasto;
import com.example.trabalhocs.Controller.GastoCtrl;
import com.example.trabalhocs.Model.ModeloGasto;
import com.example.trabalhocs.R;
import com.example.trabalhocs.dbhelper.ConexaoSQlite;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

public class GerenciarValoresGasto extends AppCompatActivity {

    private ListView lsvGastos;
    private List<ModeloGasto> gastoList;
    private AdapterListaGasto adapterListaGasto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_valoresgasto);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        GerenciarGasto GerenciarGasto = new GerenciarGasto();
        final int selecionar = GerenciarGasto.selecionardestino;
        listargastos(selecionar);

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


    public void alertdialogeeditargastodata(final int cod_gasto, final int selecionar, final String valor) {
        final GastoCtrl gastoCtrl = new GastoCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarValoresGasto.this));
        final EditText destinotxtdata;
        AlertDialog.Builder builderdata = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        builderdata.setMessage("Digite o nova data do gasto: ");
        destinotxtdata = new EditText(this);
        builderdata.setView(destinotxtdata);
        destinotxtdata.setInputType(InputType.TYPE_CLASS_DATETIME);
        //OK
        builderdata.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ModeloGasto gastoACadastrar = new ModeloGasto();
                gastoACadastrar.setCodgasto(cod_gasto);
                gastoACadastrar.setValor(Float.parseFloat(valor));
                gastoACadastrar.setDate(destinotxtdata.getText().toString());
                gastoACadastrar.setCod_destino(selecionar);
                if (destinotxtdata.getText().length() != 0) {
                    if (verdata((destinotxtdata.getText().toString())) == true){
                        gastoCtrl.atualizarGastoCtrl(gastoACadastrar);
                        listargastos(selecionar);
                        Toast.makeText(GerenciarValoresGasto.this, "Gasto alterado com sucesso!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(GerenciarValoresGasto.this, "Data invalida!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                } else {
                    Toast.makeText(GerenciarValoresGasto.this, "Preencha o campo corretamente!", Toast.LENGTH_SHORT).show();
                    alertdialogeeditargastodata(cod_gasto, selecionar, valor);
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
        destinotxtdata.setText("");

    }

    private void alertdialogeeditargasto(final int cod_gasto, final int selecionar) {
        final EditText destinotxtvalor;
        AlertDialog.Builder buildervalor = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        buildervalor.setMessage("Digite o novo valor da gasto: ");
        destinotxtvalor = new EditText(this);
        buildervalor.setView(destinotxtvalor);
        destinotxtvalor.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        //OK
        buildervalor.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Float v = Float.parseFloat(destinotxtvalor.getText().toString());
                String valor;
                valor = destinotxtvalor.getText().toString();
                if (destinotxtvalor.getText().length() !=0 && verificarvalor(v)) {
                    alertdialogeeditargastodata(cod_gasto, selecionar, valor);
                } else {
                    Toast.makeText(GerenciarValoresGasto.this, "Preencha o campo corretamente!", Toast.LENGTH_SHORT).show();
                    alertdialogeeditargasto(cod_gasto, selecionar);
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
        destinotxtvalor.setText("");
    }

    public boolean verificarvalor(Float v){
        if (v >= 0){
            return true;
        }
        return false;
    }

    private void alertdialogexcluirgasto(final int cod_gasto, final int selecionar) {
        final GastoCtrl gastoCtrl = new GastoCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarValoresGasto.this));
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Deseja realmente exluir este gasto?");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gastoCtrl.excluirGastoCtrl(cod_gasto);
                Toast.makeText(GerenciarValoresGasto.this, "Gasto removido com sucesso!",Toast.LENGTH_SHORT).show();;
                listargastos(selecionar);
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
        final GastoCtrl gastoCtrl = new GastoCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarValoresGasto.this));
        List<ModeloGasto> gastos = (List<ModeloGasto>) gastoCtrl.procurarControler(keyword,selecionar);
        if (gastos != null) {
            this.lsvGastos = (ListView) findViewById(R.id.listagerenciarvaloresgasto);
            lsvGastos.setAdapter(new AdapterListaGasto(getApplicationContext(),gastos));
        }
        else {
            this.lsvGastos = (ListView) findViewById(R.id.listagerenciarvaloresgasto);
            lsvGastos.setAdapter(null);
        }
    }

    public void listargastos(final int selecionar){

        //BUSCAR TODOS OS GASTOS DO BANCO

        final GastoCtrl gastoCtrl = new GastoCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarValoresGasto.this));

        this.gastoList = new ArrayList<>();
        gastoList = gastoCtrl.getListaGastoCtrl(selecionar);

        this.lsvGastos = (ListView) findViewById(R.id.listagerenciarvaloresgasto);

        this.adapterListaGasto = new AdapterListaGasto(GerenciarValoresGasto.this, gastoList);

        this.lsvGastos.setAdapter(this.adapterListaGasto);

        this.lsvGastos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int posicao, long id) {

                adapterListaGasto.select(posicao);
                Button btnexcluir = findViewById(R.id.excluirvalgasto);
                Button btneditar = findViewById(R.id.editarvalgasto);

                ModeloGasto gastoSelecionada = (ModeloGasto) adapterListaGasto.getItem(posicao);
                final int cod_gasto = gastoSelecionada.getCodgasto();
                btnexcluir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertdialogexcluirgasto(cod_gasto, selecionar);
                        listargastos(selecionar);
                    }
                });
                btneditar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertdialogeeditargasto(cod_gasto, selecionar);
                        listargastos(selecionar);
                    }
                });

            }
        });
    }




}
