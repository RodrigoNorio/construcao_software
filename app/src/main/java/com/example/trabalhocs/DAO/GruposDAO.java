package com.example.trabalhocs.DAO;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.trabalhocs.Model.ModeloFonte;
import com.example.trabalhocs.Model.ModeloGrupo;
import com.example.trabalhocs.dbhelper.ConexaoSQlite;

import java.util.ArrayList;
import java.util.List;

public class GruposDAO {

    private final ConexaoSQlite conexaoSQlite;

    public GruposDAO(ConexaoSQlite conexaoSQlite) {
        this.conexaoSQlite = conexaoSQlite;
    }

    public long salvarGruposDAO (ModeloGrupo f){

        SQLiteDatabase db = conexaoSQlite.getWritableDatabase();

        try{
            ContentValues values = new ContentValues();
            values.put("descricao",f.getNome());

            long grupoadd = db.insert("grupos", null, values);

            return grupoadd;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(db != null){
                db.close();
            }
        }
        return 0;
    }



    public List<ModeloFonte> getListaGrupoDAO(){

        List<ModeloFonte> listaFontes = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor;

        String query = "SELECT *FROM fonte;";

        try{

            db = this.conexaoSQlite.getReadableDatabase();

            cursor = db.rawQuery(query, null);

            if(cursor.moveToFirst()){

                ModeloFonte fonteTemporaria = null;

                do{

                    fonteTemporaria = new ModeloFonte();
                    fonteTemporaria.setCodfonte(cursor.getInt(0));
                    fonteTemporaria.setDescricao(cursor.getString(1));

                    listaFontes.add(fonteTemporaria);

                }while(cursor.moveToNext());
            }

        }catch (Exception e){
            Log.d("Erro Lista Fontes", "Erro ao retornar as fontes");
            return null;
        }finally {
            if(db != null){
                db.close();
            }
        }
        return listaFontes;
    }

}
