package com.example.trabalhocs.Model;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.trabalhocs.Utils.Constants;
import com.example.trabalhocs.Utils.Utilidades;
import com.orm.SugarRecord;

public class ModeloRecurso extends SugarRecord {

    private String nome;
    private String descricao;
    private int tipoMedida;
    private int inventario;

    public ModeloRecurso() {
    }

    public ModeloRecurso(String nome, String descricao, int tipoMedida, int inventario) {
        this.nome = nome;
        this.descricao = descricao;
        this.tipoMedida = tipoMedida;
        this.inventario = inventario;
    }

    public void incrementarInventario(int quantidade) {
        this.inventario += quantidade;
    }

    public void decrementarInventario(int quantidade) {
        this.inventario -= quantidade;
    }

    public String getTextoEstoqueAbrev(Context context) {
        return inventario + " " + Utilidades.getMedidaAbrev(context, tipoMedida);
    }

    public String getTextoEstoque(Context context) {
        return inventario + " " + Utilidades.getMedidaText(context, tipoMedida);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getInventario() {
        return inventario;
    }

    public void setInventario(int inventario) {
        this.inventario = inventario;
    }

    public int getTipoMedida() {
        return tipoMedida;
    }

    @NonNull
    @Override
    public String toString() {
        String retorno = "";

        retorno += "Recurso # " + getId()  + " " + nome + "\n";
        retorno += "Em inventário: " + inventario;

        return retorno;
    }

    public boolean testeRecursoValido() {
        boolean validade = true;

        if (this.getNome().isEmpty()) validade = false;
        if (this.getInventario() < 0) validade = false;

        if (this.getTipoMedida() != Constants.TIPO_MEDIDA_GRAMAS &&
            this.getTipoMedida() != Constants.TIPO_MEDIDA_MILILITRO &&
            this.getTipoMedida() != Constants.TIPO_MEDIDA_UNIDADE) validade = false;

        return  validade;
    }
}
