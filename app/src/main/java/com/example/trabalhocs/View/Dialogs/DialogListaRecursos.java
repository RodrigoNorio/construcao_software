package com.example.trabalhocs.View.Dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.Adapter.AdapterCompraRecurso;
import com.example.trabalhocs.Model.ModeloRecurso;
import com.example.trabalhocs.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogListaRecursos extends AlertDialog.Builder implements AdapterCompraRecurso.AdapterCompraRecursoListener {

    @BindView(R.id.rv_recursos)
    RecyclerView rvRecursos;

    @BindView(R.id.btn_fechar)
    AppCompatImageButton btnFechar;

    private AlertDialog dialog;

    private Context context;
    private List<ModeloRecurso> recursoList;

    public DialogListaRecursos(Context context, List<ModeloRecurso> recursoList) {
        super(context);
        this.context = context;
        this.recursoList = recursoList;

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
        AdapterCompraRecurso adapterCompraRecurso = new AdapterCompraRecurso(recursoList, this, context);

        rvRecursos.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        rvRecursos.setAdapter(adapterCompraRecurso);
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
