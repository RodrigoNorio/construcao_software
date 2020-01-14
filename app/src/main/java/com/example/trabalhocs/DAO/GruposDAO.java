package com.example.trabalhocs.DAO;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.trabalhocs.Model.ModeloGrupo;

import com.example.trabalhocs.Model.ModeloLogin;
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
            values.put("cod_pessoa", cod_pessoa);

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

    public boolean atualizarGruposDAO(ModeloGrupo f){
        SQLiteDatabase db = null;
        try{
            db = this.conexaoSQlite.getWritableDatabase();

            ContentValues gruponome = new ContentValues();

            gruponome.put("nome", f.getNome());
            gruponome.put("cod_pessoa", cod_pessoa);

            int atualizou = db.update("grupos",
                    gruponome,
                    "cod_grupo = ? AND cod_pessoa",
                    new String[]{String.valueOf(f.getNome()),String.valueOf(cod_pessoa)}
            );

            if (atualizou > 0){
                return true;
            }
            else{
                return false;
            }


        }catch(Exception e){
            Log.d("GRUPODAO", "Não foi possivel atualizar o grupo");
            return false;
        }finally {
            if (db != null){
                db.close();
            }
        }
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

    public List<ModeloLogin> getListaUsuarioDAO(int cod_grupo){

        List<ModeloLogin> listaUsuario = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor;
        try{

            db = this.conexaoSQlite.getReadableDatabase();

            cursor = db.rawQuery("SELECT usuario FROM pessoa,grupos WHERE grupos.cod_pessoa = pessoa.cod_pessoa AND grupos.cod_grupo = ?", new String[]{String.valueOf(cod_grupo)});
            if(cursor.moveToFirst()){

                ModeloLogin fonteTemporaria = null;

                do{

                    fonteTemporaria = new ModeloLogin();
                    fonteTemporaria.setUsuario(cursor.getString(0));

                    listaUsuario.add(fonteTemporaria);

                }while(cursor.moveToNext());
            }

        }catch (Exception e){
            Log.d("Erro Lista Login", "Erro ao retornar as Login");
            return null;
        }finally {
            if(db != null){
                db.close();
            }
        }
        return listaUsuario;
    }

    public boolean excluirGrupoDAO (long rcodGrupo){
        exluirDadosGruposDAO(rcodGrupo);
        SQLiteDatabase db = null;

        try{
            db = this.conexaoSQlite.getWritableDatabase();

            db.delete(
                    "grupos",
                    "cod_grupo = ? AND cod_pessoa = ?",
                    new String[]{String.valueOf(rcodGrupo), String.valueOf(cod_pessoa)}
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

    public boolean exluirDadosGruposDAO (long fcodGrupo){
        SQLiteDatabase db = null;

        try{
            db = this.conexaoSQlite.getWritableDatabase();

            db.delete(
                    "grupos",
                    "cod_grupo = ? AND cod_pessoa = ?",
                    new String[]{String.valueOf(fcodGrupo), String.valueOf(cod_pessoa)}
            );

        }catch(Exception e){
            Log.d("GRUPODAO", "Não foi possivel deletar grupo");
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

            Cursor cursor = sqLiteDatabase.rawQuery("select * from grupos where nome like ? and cod_pessoa = ?", new String[] { "%" + keyword + "%", String.valueOf(cod_pessoa) });
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

    public List<ModeloGrupo> getListaGruposDAO(){

        List<ModeloGrupo> listaGrupos = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor;

        String query = "SELECT *FROM grupos;";

        try{

            db = this.conexaoSQlite.getReadableDatabase();

            cursor = db.rawQuery("SELECT * FROM grupos WHERE cod_pessoa = ?", new String[] {String.valueOf(cod_pessoa)});

            if(cursor.moveToFirst()){

                ModeloGrupo grupoTemporaria = null;

                do{

                    grupoTemporaria = new ModeloGrupo();
                    grupoTemporaria.setNome(cursor.getString(2));

                    listaGrupos.add(grupoTemporaria);

                }while(cursor.moveToNext());
            }

        }catch (Exception e){
            Log.d("Erro Lista Grupos", "Erro ao retornar os grupos");
            return null;
        }finally {
            if(db != null){
                db.close();
            }
        }
        return listaGrupos;
    }

    public int verusuario(String usuario) {
        SQLiteDatabase db = null;
        db = this.conexaoSQlite.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT *FROM pessoa",null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                if (usuario.equals(cursor.getString(1))){
                    return cursor.getInt(0);
                }
            }
            while(cursor.moveToNext());
            return -1;
        }
        else{
            return -1;
        }
    }

}
