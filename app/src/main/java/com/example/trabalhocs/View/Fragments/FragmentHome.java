package com.example.trabalhocs.View.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.trabalhocs.MainActivity;
import com.example.trabalhocs.Model.ModeloGasto;
import com.example.trabalhocs.Model.ModeloReceita;
import com.example.trabalhocs.R;
import com.example.trabalhocs.View.GerenciarGrupo;
import com.example.trabalhocs.View.ListarDestinoAdd;
import com.example.trabalhocs.View.ListarFontesAdd;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentHome extends Fragment {
    public static String val;
    public static String valgasto;


    @BindView(R.id.addreceita)
    Button addreceita;

    @BindView(R.id.addgasto)
    Button addgasto;


    public FragmentHome() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);



        addreceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText receitatxt;
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Light_Dialog_Alert);
                builder.setMessage("Digite o valor da receita: ");
                receitatxt = new EditText(getContext());
                builder.setView(receitatxt);
                receitatxt.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

                //OK
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ModeloReceita receitaACadastrar = new ModeloReceita();
                        val = receitatxt.getText().toString();
                        receitaACadastrar.setValor(Float.parseFloat(val));
                        if (receitatxt.getText().length() != 0){
                            val = receitatxt.getText().toString();
                            Intent it = new Intent (getContext(), ListarFontesAdd.class);
                            startActivity(it);
                        }
                        else {
                            Toast.makeText(getContext(), "Preencha o campo corretamente!", Toast.LENGTH_SHORT).show();
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
                receitatxt.setText("");
            }
        });

        addgasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText gastotxt;
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Light_Dialog_Alert);
                builder.setMessage("Digite o valor do gasto: ");
                gastotxt = new EditText(getContext());
                builder.setView(gastotxt);
                gastotxt.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

                //OK
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ModeloGasto gastoACadastrar = new ModeloGasto();
                        valgasto = gastotxt.getText().toString();
                        gastoACadastrar.setValor(Float.parseFloat(valgasto));
                        if (gastotxt.getText().length() != 0){
                            valgasto = gastotxt.getText().toString();
                            Intent it = new Intent (getContext(), ListarDestinoAdd.class);
                            startActivity(it);
                        }
                        else {
                            Toast.makeText(getContext(), "Preencha o campo corretamente!", Toast.LENGTH_SHORT).show();
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
                gastotxt.setText("");
            }
        });

        return view;
    }

}
