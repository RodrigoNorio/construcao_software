package com.example.trabalhocs.Model;

public class ModeloLogin {
    int codpessoa;
    String usuario;
    String pass;

    public ModeloLogin() {
    }

    public int getCodpessoa() {
        return codpessoa;
    }

    public void setCodpessoa(int codpessoa) {
        this.codpessoa = codpessoa;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return pass;
    }

    public void setPassword(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "ModeloDestino{" +
                "codpessoa=" + codpessoa +
                "pass=" + pass +
                ", usuario='" + usuario + '\'' +
                '}';
    }
}
