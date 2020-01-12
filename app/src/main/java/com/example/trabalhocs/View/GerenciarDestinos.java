package com.example.trabalhocs.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.trabalhocs.Adapter.AdapterListaDestino;
import com.example.trabalhocs.Controller.DestinoCtrl;
import com.example.trabalhocs.Model.ModeloDestino;
import com.example.trabalhocs.R;
import com.example.trabalhocs.Tutorial.Tutorial_Gerenciar_Destinos;
import com.example.trabalhocs.dbhelper.ConexaoSQlite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GerenciarDestinos extends AppCompatActivity {

    private ListView lsvDestino;
    private List<ModeloDestino> destinoList;
    private AdapterListaDestino adapterListaDestino;
    public static int selecionardestino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_destinos);
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
        Button btnverificardestinomes = (Button) findViewById(R.id.verificarmes);
        btnverificardestinomes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertdialogverificardestinomes();
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

    public boolean verificartamanhostring(String s){
        if (s.length() > 0 && s.length() < 15){
            return true;
        }
        return false;
    }

    private void alertdialogadddestino() {
        final EditText destinotxt;
        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        builder.setMessage("Digite o nome do destino(até 15 caracteres): ");
        destinotxt = new EditText(this);
        builder.setView(destinotxt);

        //OK
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ModeloDestino destinoACadastrar = new ModeloDestino();

                destinoACadastrar.setDescricao(destinotxt.getText().toString());

                if (verificartamanhostring(destinotxt.getText().toString())){
                    DestinoCtrl destinoCtrl = new DestinoCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarDestinos.this));
                    destinoCtrl.salvarDestinoCtrl(destinoACadastrar);
                    Toast.makeText(GerenciarDestinos.this, "Destino cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    listardestinos();
                }
                else {
                    Toast.makeText(GerenciarDestinos.this, "Preencha o campo corretamente!", Toast.LENGTH_SHORT).show();
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
        final DestinoCtrl destinoCtrl = new DestinoCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarDestinos.this));
        final EditText destinotxt;
        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        builder.setMessage("Digite o novo nome do destino(até 15 caracteres): ");
        destinotxt = new EditText(this);
        builder.setView(destinotxt);

        //OK
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ModeloDestino destinoACadastrar = new ModeloDestino();
                destinoACadastrar.setCoddestino(cod_destino);
                destinoACadastrar.setDescricao(destinotxt.getText().toString());
                if (verificartamanhostring(destinotxt.getText().toString())){
                    destinoCtrl.atualizarDestinoCtrl(destinoACadastrar);
                    Toast.makeText(GerenciarDestinos.this, "Destino alterado com sucesso!", Toast.LENGTH_SHORT).show();
                    listardestinos();
                }
                else {
                    Toast.makeText(GerenciarDestinos.this, "Preencha o campo corretamente!", Toast.LENGTH_SHORT).show();
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
        final DestinoCtrl destinoCtrl = new DestinoCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarDestinos.this));
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final AlertDialog.Builder alert2 = new AlertDialog.Builder(this);
        alert.setMessage("Deseja realmente excluir este destino?");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alert2.setMessage("Se você excluir este destino, todas os gastos relacionadas irão ser excluidos, REALMENTE DESEJA EXCLUIR ESTE DESTINO?");
                alert2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        destinoCtrl.excluirDestinoCtrl(cod_destino);
                        Toast.makeText(GerenciarDestinos.this, "Destino removida com sucesso!",Toast.LENGTH_SHORT).show();;
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

    /*private void alertdialogverificardin(final int cod_destino){
        selecionardestino = cod_destino;
        Intent it = new Intent (GerenciarDestinos.this, GerenciarGastos.class);
        startActivity(it);
    }*/

    public void listardestinos(){

        //BUSCAR TODOS OS DESTINOS DO BANCO

        final DestinoCtrl destinoCtrl = new DestinoCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarDestinos.this));

        this.destinoList = new ArrayList<>();
        destinoList = destinoCtrl.getListaDestinoCtrl();

        this.lsvDestino = (ListView) findViewById(R.id.listardestinos);

        this.adapterListaDestino = new AdapterListaDestino(GerenciarDestinos.this, destinoList);

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
                        //alertdialogverificardin(cod_destino);
                    }
                });
            }
        });
    }

    private void searchContact(String keyword) {
        final DestinoCtrl destinoCtrl = new DestinoCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarDestinos.this));
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

    private void alertdialogverificardestinomes(){
        final EditText destinotxt,destinotxt2;
        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        AlertDialog.Builder builder3 = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        builder.setMessage("Digite a data inicial que deseja verificar: ");
        destinotxt = new EditText(this);
        destinotxt2 = new EditText(this);
        destinotxt.setInputType(InputType.TYPE_CLASS_DATETIME);
        destinotxt2.setInputType(InputType.TYPE_CLASS_DATETIME);
        builder.setView(destinotxt);
        final DestinoCtrl destinoCtrl = new DestinoCtrl(ConexaoSQlite.getInstanciaConexao(GerenciarDestinos.this));
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (verdata(destinotxt.getText().toString()) == true){
                    builder2.setMessage("Digite a data final que deseja verificar: ");
                    builder2.setView(destinotxt2);
                    builder2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (verdata(destinotxt2.getText().toString()) == true && verificarmenor(destinotxt.getText().toString(), destinotxt2.getText().toString()) == true){
                                builder3.setMessage("Seu gasto foi de " + destinoCtrl.verificartotalDestinoCtrl(destinotxt.getText().toString(),
                                        destinotxt2.getText().toString()) + " reais, durante a data entre " + destinotxt.getText().toString() + " e "
                                        + destinotxt2.getText().toString());
                                builder3.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                final AlertDialog ad3 = builder3.create();
                                ad3.show();
                                destinotxt2.setText("");
                            }
                            else{
                                Toast.makeText(GerenciarDestinos.this, "Data final menor que a inicial ou inválida!", Toast.LENGTH_SHORT).show();
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
                    destinotxt2.setText("");
                }
                else{
                    Toast.makeText(GerenciarDestinos.this, "Data invalida!", Toast.LENGTH_SHORT).show();
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
        destinotxt.setText("");
    }

    private boolean verdata(String data) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            sdf.parse(data);
            return true;
        } catch (ParseException ex) {
            return false;
        }
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

    public void tutorialdestino (View view){
        Intent it = new Intent (GerenciarDestinos.this, Tutorial_Gerenciar_Destinos.class);
        startActivity(it);
    }
}
