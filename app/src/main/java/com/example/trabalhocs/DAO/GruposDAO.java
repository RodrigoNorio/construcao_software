package com.example.trabalhocs.DAO;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.trabalhocs.Model.ModeloGrupo;

import com.example.trabalhocs.Model.ModeloReceita;
import com.example.trabalhocs.View.Login;

import com.example.trabalhocs.dbhelper.ConexaoSQlite;

import java.util.ArrayList;
import java.util.List;

public class GruposDAO {

    private final ConexaoSQlite conexaoSQlite;

    Login login = new Login();
    final int cod_pessoa = Login.codusuario;

    public GruposDAO(ConexaoSQlite conexaoSQlite) {
        this.conexaoSQlite = conexaoSQlite;
    }

    public long salvarGruposDAO (ModeloGrupo f){

        SQLiteDatabase db = conexaoSQlite.getWritableDatabase();

        try{
            ContentValues values = new ContentValues();
            values.put("nome",f.getNome());

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



    public List<ModeloGrupo> getListaGrupoDAO(){

        List<ModeloGrupo> listaGrupos = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor;

        String query = "SELECT *FROM grupos;";

        try{

            db = this.conexaoSQlite.getReadableDatabase();

            cursor = db.rawQuery(query, null);

            if(cursor.moveToFirst()){

                ModeloGrupo grupoTemporario = null;

                do{

                    grupoTemporario = new ModeloGrupo();
                    grupoTemporario.setCod_grupo(cursor.getInt(0));
                    grupoTemporario.setNome(cursor.getString(2));

                    listaGrupos.add(grupoTemporario);

                }while(cursor.moveToNext());
            }

        }catch (Exception e){
            Log.d("Erro Lista Grupo", "Erro ao retornar os grupos");
            return null;
        }finally {
            if(db != null){
                db.close();
            }
        }
        return listaGrupos;
    }

    public boolean excluirGrupoDAO (long rcodGrupo){
        SQLiteDatabase db = null;

        try{
            db = this.conexaoSQlite.getWritableDatabase();

            db.delete(
                    "grupos",
                    "cod_grupo = ?",
                    new String[]{String.valueOf(rcodGrupo)}
            );

        }catch(Exception e){
            Log.d("GrupoDAO", "Não foi possivel deletar Grupo");
            return false;
        }
        finally {
            if(db != null){
                db.close();
            }
        }
        return true;
    }

    public List<ModeloGrupo> search(String keyword) {
        List<ModeloGrupo> contacts = null;
        try {
            SQLiteDatabase sqLiteDatabase = this.conexaoSQlite.getReadableDatabase();

            Cursor cursor = sqLiteDatabase.rawQuery("select * from grupos where nome like ?", new String[] { "%" + keyword + "%" });
            if (cursor.moveToFirst()) {
                contacts = new ArrayList<ModeloGrupo>();
                do {
                    ModeloGrupo contact = new ModeloGrupo();
                    //contact.setNome(cursor.getFloat(2));
                    contact.setNome(cursor.getString(2));
                    contacts.add(contact);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            contacts = null;
        }
        return contacts;
    }


}
