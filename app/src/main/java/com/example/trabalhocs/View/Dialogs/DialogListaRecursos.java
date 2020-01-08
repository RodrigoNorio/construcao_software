package com.example.trabalhocs.View.Dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.Adapter.AdapterListaRecursos;
import com.example.trabalhocs.Model.ModeloRecurso;
import com.example.trabalhocs.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogListaRecursos extends AlertDialog.Builder implements AdapterListaRecursos.AdapterCompraRecursoListener {

    @BindView(R.id.rv_recursos)
    RecyclerView rvRecursos;

    @BindView(R.id.btn_fechar)
    AppCompatImageButton btnFechar;

    private AlertDialog dialog;

    private Context context;
    private List<ModeloRecurso> recursoList;
    private int origemID; //Constants

    public DialogListaRecursos(Context context, List<ModeloRecurso> recursoList, int origemID) {
        super(context);
        this.context = context;
        this.recursoList = recursoList;
        this.origemID = origemID;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_lista_recursos, null);
        setCancelable(true);

        ButterKnife.bind(this, view);

        config();

        setView(view);
    }

    @Override
    public AlertDialog create() {
        this.dialog = super.create();
        return this.dialog;
    }

    private void config() {
        AdapterListaRecursos adapterListaRecursos = new AdapterListaRecursos(recursoList, this, context, origemID);

        rvRecursos.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        rvRecursos.setAdapter(adapterListaRecursos);
    }

    @OnClick(R.id.btn_fechar)
    void onClickBtnFechar() {
        dialog.dismiss();
    }

    @Override
    public void fecharDialog() {
        dialog.dismiss();
    }
}
