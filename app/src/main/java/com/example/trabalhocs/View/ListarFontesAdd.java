package com.example.trabalhocs.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.trabalhocs.Adapter.AdapterListaFonte;
import com.example.trabalhocs.Controller.FonteCtrl;
import com.example.trabalhocs.Controller.ReceitaCtrl;
import com.example.trabalhocs.MainActivity;
import com.example.trabalhocs.Model.ModeloFonte;
import com.example.trabalhocs.Model.ModeloReceita;
import com.example.trabalhocs.R;
import com.example.trabalhocs.View.Fragments.FragmentHome;
import com.example.trabalhocs.dbhelper.ConexaoSQlite;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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

public class ListarFontesAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_fontes_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listarfontes();
        Button addfonte = (Button) findViewById(R.id.adicionarfonte3);
        addfonte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertdialogaddfonte();
                listarfontes();
            }
        });

    }
    public void listarfontes(){

        ListView lsvFontes;
        List<ModeloFonte> fonteList;
        final AdapterListaFonte adapterListaFonte;

        final FonteCtrl fonteCtrl = new FonteCtrl(ConexaoSQlite.getInstanciaConexao(ListarFontesAdd.this));

        fonteList = fonteCtrl.getListaFontesCtrl();

        lsvFontes = (ListView) findViewById(R.id.listarfonteadd);

        adapterListaFonte = new AdapterListaFonte(ListarFontesAdd.this, fonteList);

        lsvFontes.setAdapter(adapterListaFonte);

        lsvFontes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int posicao, long id) {
                adapterListaFonte.select(posicao);
                ModeloFonte fonteSelecionada = (ModeloFonte) adapterListaFonte.getItem(posicao);
                final int cod_fonte = fonteSelecionada.getCodfonte();
                adicionarvalor(cod_fonte);
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
                    FonteCtrl fonteCtrl = new FonteCtrl(ConexaoSQlite.getInstanciaConexao(ListarFontesAdd.this));
                    fonteCtrl.salvarFonteCtrl(fonteACadastrar);
                    Toast.makeText(ListarFontesAdd.this, "Fonte cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
                    listarfontes();
                }
                else {
                    Toast.makeText(ListarFontesAdd.this, "Preencha o campo corretamente!", Toast.LENGTH_SHORT).show();
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

    private void adicionarvalor(final int cod_fonte){
        final ReceitaCtrl receitaCtrl = new ReceitaCtrl(ConexaoSQlite.getInstanciaConexao(ListarFontesAdd.this));
        final EditText fontetxt;
        FragmentHome mainActivity = new FragmentHome();
        String var = FragmentHome.val;
        final Float valor = Float.parseFloat(var);
        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        builder.setMessage("Digite a data: ");
        fontetxt = new EditText(this);
        builder.setView(fontetxt);
        fontetxt.setInputType(InputType.TYPE_CLASS_DATETIME);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                ModeloReceita receitaACadastrar = new ModeloReceita();
                receitaACadastrar.setValor(valor);
                receitaACadastrar.setDate(fontetxt.getText().toString());
                receitaACadastrar.setCodfonte(cod_fonte);
                receitaCtrl.salvarReceitaCtrl(receitaACadastrar);
                Toast.makeText(ListarFontesAdd.this, "Valor adicionado com sucesso!", Toast.LENGTH_SHORT).show();
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
