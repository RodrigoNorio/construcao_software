package com.example.trabalhocs.Controller;

import com.example.trabalhocs.DAO.GastoDAO;
import com.example.trabalhocs.Model.ModeloGasto;
import com.example.trabalhocs.dbhelper.ConexaoSQlite;

import java.util.List;

public class GastoCtrl {

    private final GastoDAO gastoDAO;

    public GastoCtrl(ConexaoSQlite pConexaoSQLite){
        gastoDAO = new GastoDAO(pConexaoSQLite);
    }

    public long salvarGastoCtrl(ModeloGasto r){
        return this.gastoDAO.salvarGastoDAO(r);
    }

    public List<ModeloGasto> getListaGastoCtrl(int selecionada){
        return this.gastoDAO.getListaGastoDAO(selecionada);
    }

    public boolean excluirGastoCtrl(long rcodDestino){
        return this.gastoDAO.excluirGastoDAO(rcodDestino);
    }

    public boolean atualizarGastoCtrl (ModeloGasto r){
        return this.gastoDAO.atualizarGastoDAO(r);
    }

    public List<ModeloGasto> procurarControler(String keyword, int selecionar){
        return this.gastoDAO.search(keyword, selecionar);
    }
}
