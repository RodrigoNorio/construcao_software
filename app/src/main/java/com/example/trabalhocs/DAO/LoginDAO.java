package com.example.trabalhocs.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.trabalhocs.Model.ModeloLogin;
import com.example.trabalhocs.dbhelper.ConexaoSQlite;

public class LoginDAO {

    private final ConexaoSQlite conexaoSQlite;

    public LoginDAO(ConexaoSQlite conexaoSQlite) {
        this.conexaoSQlite = conexaoSQlite;
    }

    public long salvarLoginDAO (ModeloLogin l){

        SQLiteDatabase db = conexaoSQlite.getWritableDatabase();

        try{
            ContentValues values = new ContentValues();
            values.put("usuario",l.getUsuario());
            values.put("pass",l.getPassword());

            long logincadastrado = db.insert("pessoa", null, values);

            return logincadastrado;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(db != null){
                db.close();
            }
        }
        return 0;
    }

    public Boolean checarLoginDAO (String verlogin){
        SQLiteDatabase sqLiteDatabase = this.conexaoSQlite.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM pessoa WHERE usuario=?", new String[] {verlogin});
        if(cursor.getCount()>0)
            return false;
        else
            return true;
    }
    public Boolean checarLogineEmailDAO (String verlogin,String versenha){
        SQLiteDatabase sqLiteDatabase = this.conexaoSQlite.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM pessoa WHERE usuario=? AND pass=?", new String[] {verlogin,versenha});
        if(cursor.getCount()>0)
            return false;
        else
            return true;
    }
}
