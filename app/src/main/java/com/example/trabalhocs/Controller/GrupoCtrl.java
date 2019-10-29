package com.example.trabalhocs.Controller;



import com.example.trabalhocs.DAO.FonteDAO;
import com.example.trabalhocs.DAO.GruposDAO;
import com.example.trabalhocs.Model.ModeloFonte;
import com.example.trabalhocs.Model.ModeloGrupo;
import com.example.trabalhocs.dbhelper.ConexaoSQlite;

import java.util.List;

public class GrupoCtrl {

    private final GruposDAO grupoDAO;

    public GrupoCtrl(ConexaoSQlite pConexaoSQLite){
        grupoDAO = new GruposDAO(pConexaoSQLite);
    }

    public long salvarGruposDAOCtrl(ModeloGrupo f){
        return this.grupoDAO.salvarGruposDAO(f);
    }

    public List<ModeloGrupo> getListaGruposCtrl(){
        return this.grupoDAO.getListaGrupoDAO();
    }

    public boolean excluirGrupoCtrl(long fcodGrupo){
        return this.grupoDAO.excluirGrupoDAO(fcodGrupo);
    }


    public List<ModeloGrupo> procurarControler(String keyword){
        return this.grupoDAO.search(keyword);
    }

}
