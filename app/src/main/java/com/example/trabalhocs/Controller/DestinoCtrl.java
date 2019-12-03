package com.example.trabalhocs.Controller;


import com.example.trabalhocs.DAO.DestinoDAO;
import com.example.trabalhocs.Model.ModeloDestino;
import com.example.trabalhocs.dbhelper.ConexaoSQlite;

import java.util.List;

public class DestinoCtrl {

    private final DestinoDAO DestinoDAO;

    public DestinoCtrl(ConexaoSQlite pConexaoSQLite){
        DestinoDAO = new DestinoDAO(pConexaoSQLite);
    }

    public long salvarDestinoCtrl(ModeloDestino d){
        return this.DestinoDAO.salvarDestinoDAO(d);
    }

    public List<ModeloDestino> getListaDestinoCtrl(){
        return this.DestinoDAO.getListaDestinoDAO();
    }

    public boolean excluirDestinoCtrl(long fcodDestino){
        return this.DestinoDAO.excluirDestinoDAO(fcodDestino);
    }

    public boolean atualizarDestinoCtrl (ModeloDestino d){
        return this.DestinoDAO.atualizarDestinoDAO(d);
    }

    public List<ModeloDestino> procurarControler(String keyword){
        return this.DestinoDAO.search(keyword);
    }

}
