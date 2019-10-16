package com.example.trabalhocs.dbhelper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexaoSQlite extends SQLiteOpenHelper {

    private static ConexaoSQlite INSTANCIA_CONEXAO;
    private static final int VERSAO_DB = 1;
    private static final String NOME_DB = "bl_fontes_app";

    public ConexaoSQlite(Context context){
        super(context,NOME_DB,null,VERSAO_DB);
    }

    public static ConexaoSQlite getInstanciaConexao(Context context){
        if(INSTANCIA_CONEXAO == null){
            INSTANCIA_CONEXAO = new ConexaoSQlite(context);
        }
        return INSTANCIA_CONEXAO;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlTabelaFonte =
                "CREATE TABLE IF NOT EXISTS fonte" +
                        "(" +
                        "cod_fonte INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "descricao TEXT NOT NULL);";
        String sqlTabelaReceita =
                "CREATE TABLE IF NOT EXISTS receita" +
                        "(" +
                        "cod_receita INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "valor FLOAT, " +
                        "data TEXT NOT NULL," +
                        "cod_fonte INTEGER," +
                        "CONSTRAINT fk_cod_fonte FOREIGN KEY (cod_fonte) REFERENCES fonte (cod_fonte));";
        sqLiteDatabase.execSQL(sqlTabelaFonte);
        sqLiteDatabase.execSQL(sqlTabelaReceita);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
