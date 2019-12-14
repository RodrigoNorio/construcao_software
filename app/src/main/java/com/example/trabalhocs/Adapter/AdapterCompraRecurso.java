package com.example.trabalhocs.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalhocs.Model.ModeloRecurso;
import com.example.trabalhocs.R;
import com.example.trabalhocs.View.Dialogs.DialogCompraRecurso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterCompraRecurso extends RecyclerView.Adapter<AdapterCompraRecurso.CustomViewHolder> {

    private Context context;
    private AdapterCompraRecursoListener listener;
    private List<ModeloRecurso> recursoList;

    public AdapterCompraRecurso(List<ModeloRecurso> recursoList, AdapterCompraRecursoListener listener, Context context) {
        this.context = context;
        this.listener = listener;
        this.recursoList = recursoList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.itemlist_recurso_compra, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        final ModeloRecurso recurso = recursoList.get(position);

        holder.tvNome.setText(recurso.getNome());

        holder.itemView.setOnClickListener(v -> {
            abrirDialogRecurso(recurso);
            listener.fecharDialog();
        });
    }

    private void abrirDialogRecurso(ModeloRecurso recurso) {
        DialogCompraRecurso dialogCompraRecurso = new DialogCompraRecurso(context, recurso);
        final AlertDialog dialog = dialogCompraRecurso.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public int getItemCount() {
        return recursoList != null ? recursoList.size() : 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_nome)
        TextView tvNome;

        CustomViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface AdapterCompraRecursoListener {
        void fecharDialog();
    }
}
