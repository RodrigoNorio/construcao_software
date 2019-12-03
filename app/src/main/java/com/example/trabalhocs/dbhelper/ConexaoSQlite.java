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
        if (INSTANCIA_CONEXAO == null) {
            INSTANCIA_CONEXAO = new ConexaoSQlite(context);
        }

        return INSTANCIA_CONEXAO;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sqlTabelaPessoa =
                "CREATE TABLE IF NOT EXISTS pessoa" +
                        "(" +
                        "cod_pessoa INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "usuario TEXT NOT NULL," +
                        "pass TEXT NOT NULL);";

        String sqlTabelaDestino =
                "CREATE TABLE IF NOT EXISTS destino" +
                        "(" +
                        "cod_destino INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "descricao TEXT NOT NULL," +
                        "cod_pessoa INTEGER NOT NULL,"+
                            "CONSTRAINT fk_cod_pessoa FOREIGN KEY (cod_pessoa) REFERENCES pessoa (cod_pessoa));";

        String sqlTabelaFonte =
                "CREATE TABLE IF NOT EXISTS fonte" +
                        "(" +
                        "cod_fonte INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "descricao TEXT NOT NULL,"+
                        "cod_pessoa INTEGER NOT NULL,"+
                            "CONSTRAINT fk_cod_pessoa FOREIGN KEY (cod_pessoa) REFERENCES pessoa (cod_pessoa));";

        String sqlTabelaReceita =
                "CREATE TABLE IF NOT EXISTS receita" +
                        "(" +
                        "cod_receita INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "valor FLOAT, " +
                        "data TEXT NOT NULL," +
                        "cod_fonte INTEGER," +
                        "cod_pessoa INTEGER NOT NULL,"+
                            "CONSTRAINT fk_cod_fonte FOREIGN KEY (cod_fonte) REFERENCES fonte (cod_fonte)," +
                            "CONSTRAINT fk_cod_pessoa FOREIGN KEY (cod_pessoa) REFERENCES pessoa (cod_pessoa));";


        String sqlTabelaGrupo =
                "CREATE TABLE IF NOT EXISTS grupos" +
                        "(" +
                        "cod_grupo INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "cod_destino INTEGER, " +
                        "nome TEXT NOT NULL," +
                        "CONSTRAINT fk_cod_destino FOREIGN KEY (cod_destino) REFERENCES destino (cod_destino));";

        String sqlTabelaProduto =
                "CREATE TABLE IF NOT EXISTS produto" +
                        "(" +
                        "id_produto INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "nome TEXT NOT NULL," +
                        "descricao TEXT," +
                        "inventario INTEGER NOT NULL," +
                        "valor_unitario REAL NOT NULL);";

        String sqlTabelaRecurso =
                "CREATE TABLE IF NOT EXISTS recurso" +
                        "(" +
                        "id_recurso INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "tipo_medida INTEGER NOT NULL," +
                        "nome TEXT NOT NULL," +
                        "descricao TEXT," +
                        "inventario INTEGER NOT NULL);";

        String sqlTabelaCompraDeRecurso =
                "CREATE TABLE IF NOT EXISTS compra_de_recurso" +
                        "(" +
                        "id_compra INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "qtd INTEGER NOT NULL," +
                        "custo REAL NOT NULL," +
                        "descricao TEXT,"+
                        "id_recurso INTEGER NOT NULL,"+
                        "CONSTRAINT fk_id_recurso FOREIGN KEY (id_recurso) REFERENCES recurso (id_recurso));";

        String sqlTabelaGasto =
                "CREATE TABLE IF NOT EXISTS gasto" +
                        "(" +
                        "cod_gasto INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "valor FLOAT, " +
                        "data TEXT NOT NULL," +
                        "cod_destino INTEGER," +
                        "CONSTRAINT fk_cod_destino FOREIGN KEY (cod_gasto) REFERENCES destino (cod_destino));";

//        String sqlTabelaRecursosProduto =
//                "CREATE TABLE IF NOT EXISTS recursos_produto " +
//                        "(" +
//                        "id_relacao INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                        "id_recurso INTEGER NOT NULL, " +
//                        "id_produto INTEGER NOT NULL, " +
//                        "CONSTRAINT fk_id_recurso FOREIGN KEY (id_recurso) REFERENCES recurso (id_recurso)," +
//                        "CONSTRAINT fk_id_produto FOREIGN KEY (id_produto) REFERENCES produto (id_produto)" +
//                        ");";

        sqLiteDatabase.execSQL(sqlTabelaPessoa);
        sqLiteDatabase.execSQL(sqlTabelaDestino);
        sqLiteDatabase.execSQL(sqlTabelaFonte);
        sqLiteDatabase.execSQL(sqlTabelaReceita);
        sqLiteDatabase.execSQL(sqlTabelaGrupo);
        sqLiteDatabase.execSQL(sqlTabelaProduto);
        sqLiteDatabase.execSQL(sqlTabelaRecurso);
        sqLiteDatabase.execSQL(sqlTabelaCompraDeRecurso);
//        sqLiteDatabase.execSQL(sqlTabelaRecursosProduto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
