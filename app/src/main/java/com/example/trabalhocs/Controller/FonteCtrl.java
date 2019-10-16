package com.example.trabalhocs.Controller;



import com.example.trabalhocs.DAO.FonteDAO;
import com.example.trabalhocs.Model.ModeloFonte;
import com.example.trabalhocs.dbhelper.ConexaoSQlite;

import java.util.List;

public class FonteCtrl {

    private final FonteDAO fonteDAO;

    public FonteCtrl(ConexaoSQlite pConexaoSQLite){
        fonteDAO = new FonteDAO(pConexaoSQLite);
    }

    public long salvarFonteCtrl(ModeloFonte f){
        return this.fonteDAO.salvarFonteDAO(f);
    }

    public List<ModeloFonte> getListaFontesCtrl(){
        return this.fonteDAO.getListaFontesDAO();
    }

    public boolean excluirFonteCtrl(long fcodFonte){
        return this.fonteDAO.excluirFonteDAO(fcodFonte);
    }

    public boolean atualizarFonteCtrl (ModeloFonte f){
        return this.fonteDAO.atualizarFonteDAO(f);
    }

    public List<ModeloFonte> procurarControler(String keyword){
        return this.fonteDAO.search(keyword);
    }

}
