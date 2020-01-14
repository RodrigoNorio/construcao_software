package com.example.trabalhocs.View;

import android.content.DialogInterface;
import android.os.Bundle;

import com.example.trabalhocs.Adapter.AdapterListaDestino;
import com.example.trabalhocs.Controller.DestinoCtrl;
import com.example.trabalhocs.Controller.GastoCtrl;
import com.example.trabalhocs.Model.ModeloDestino;
import com.example.trabalhocs.Model.ModeloGasto;
import com.example.trabalhocs.R;
import com.example.trabalhocs.View.Fragments.FragmentHome;
import com.example.trabalhocs.dbhelper.ConexaoSQlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ListarDestinoAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_destino_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listardestinos();
        Button adddestino = (Button) findViewById(R.id.adicionardestino1);
        adddestino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertdialogadddestino();
                listardestinos();
            }
        });


    }

    public void listardestinos(){

        ListView lsvDestino;
        List<ModeloDestino> destinoList;
        final AdapterListaDestino adapterListaDestino;

        final DestinoCtrl destinoCtrl = new DestinoCtrl(ConexaoSQlite.getInstanciaConexao(ListarDestinoAdd.this));

        destinoList = destinoCtrl.getListaDestinoCtrl();

        lsvDestino = (ListView) findViewById(R.id.listardestinoadd);

        adapterListaDestino = new AdapterListaDestino(ListarDestinoAdd.this, destinoList);

        lsvDestino.setAdapter(adapterListaDestino);

        lsvDestino.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int posicao, long id) {
                adapterListaDestino.select(posicao);
                ModeloDestino destinoSelecionado = (ModeloDestino) adapterListaDestino.getItem(posicao);
                final int cod_destino = destinoSelecionado.getCoddestino();
                adicionarvalor(cod_destino);
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
                    DestinoCtrl destinoCtrl = new DestinoCtrl(ConexaoSQlite.getInstanciaConexao(ListarDestinoAdd.this));
                    destinoCtrl.salvarDestinoCtrl(destinoACadastrar);
                    Toast.makeText(ListarDestinoAdd.this, "Destino cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    listardestinos();
                }
                else {
                    Toast.makeText(ListarDestinoAdd.this, "Preencha o campo corretamente!", Toast.LENGTH_SHORT).show();
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

    private void adicionarvalor(final int cod_destino){
        final GastoCtrl gastoCtrl = new GastoCtrl(ConexaoSQlite.getInstanciaConexao(ListarDestinoAdd.this));
        final EditText destinotxt;
        FragmentHome mainActivity = new FragmentHome();
        String var = FragmentHome.valgasto;
        final Float valor = Float.parseFloat(var);
        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        builder.setMessage("Digite a data: ");
        destinotxt = new EditText(this);
        builder.setView(destinotxt);
        destinotxt.setInputType(InputType.TYPE_CLASS_DATETIME);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                ModeloGasto gastoACadastrar = new ModeloGasto();
                gastoACadastrar.setValor(valor);
                gastoACadastrar.setDate(destinotxt.getText().toString());
                gastoACadastrar.setCodgasto(cod_destino);
                gastoCtrl.salvarGastoCtrl(gastoACadastrar);
                Toast.makeText(ListarDestinoAdd.this, "Valor adicionado com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
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
    }
}
