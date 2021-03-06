package com.example.trabalhocs.Controller;

import com.example.trabalhocs.DAO.ReceitaDAO;
import com.example.trabalhocs.Model.ModeloReceita;
import com.example.trabalhocs.dbhelper.ConexaoSQlite;

import java.util.List;

public class ReceitaCtrl {

    private final ReceitaDAO receitaDAO;

    public ReceitaCtrl(ConexaoSQlite pConexaoSQLite){
        receitaDAO = new ReceitaDAO(pConexaoSQLite);
    }

    public long salvarReceitaCtrl(ModeloReceita r){
        return this.receitaDAO.salvarReceitaDAO(r);
    }

    public List<ModeloReceita> getListaReceitaCtrl(int selecionar){
        return this.receitaDAO.getListaReceitaDAO(selecionar);
    }

    public boolean excluirReceitaCtrl(long rcodFonte){
        return this.receitaDAO.excluirReceitaDAO(rcodFonte);
    }

    public boolean atualizarReceitaCtrl (ModeloReceita r){
        return this.receitaDAO.atualizarReceitaDAO(r);
    }

    public List<ModeloReceita> procurarControler(String keyword, int selecionar){
        return this.receitaDAO.search(keyword, selecionar);
    }

    public String verificartotalReceitaCtrl(String data1, String data2, int selecionar){
        return this.receitaDAO.receitatotalDAO(data1,data2, selecionar);
    }

}
