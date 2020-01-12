package com.example.trabalhocs.Controller;

import com.example.trabalhocs.DAO.LoginDAO;
import com.example.trabalhocs.Model.ModeloLogin;
import com.example.trabalhocs.dbhelper.ConexaoSQlite;

public class LoginCtrl {
    private final LoginDAO loginDAO;

    public LoginCtrl(ConexaoSQlite pConexaoSQLite){
        loginDAO = new LoginDAO(pConexaoSQLite);
    }
    public long salvarLoginCtrl(ModeloLogin d){
        return this.loginDAO.salvarLoginDAO(d);
    }
    public Boolean verificarLoginCtrl(String login){
        return this.loginDAO.checarLoginDAO(login);
    }
    public Boolean verificarLogineSenhaCtrl(String login, String senha){
        return this.loginDAO.checarLogineEmailDAO(login,senha);
    }
    public int retornarcodCtrl(String login, String senha){
        return this.loginDAO.recuperarcodloginDAO(login,senha);
    }
}
