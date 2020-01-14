package com.example.trabalhocs.Model;

public class ModeloGrupo {
    int cod_grupo;
    int cod_destino;
    String nome;
    int cod_pessoa;

    public ModeloGrupo() {
    }


    public int getCod_grupo() {
        return cod_grupo;
    }

    public void setCod_grupo(int cod_grupo) {
        this.cod_grupo = cod_grupo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCod_destino() {
        return cod_destino;
    }

    public void setCod_destino(int cod_destino) {
        this.cod_destino = cod_destino;
    }

    public int getCod_pessoa() {
        return cod_pessoa;
    }

    public void setCod_pessoa(int cod_pessoa) {
        this.cod_pessoa = cod_pessoa;
    }


    @Override
    public String toString() {
        return "ModeloGrupo{" +
                "cod_grupo=" + cod_grupo +
                "cod_destino=" +cod_destino+
                ", nome='" + nome +
                ", cod_pessoa='" + cod_pessoa +'\'' +
                '}';
    }

}
