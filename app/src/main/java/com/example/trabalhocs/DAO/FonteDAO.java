package com.example.trabalhocs.DAO;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.trabalhocs.Model.ModeloFonte;
import com.example.trabalhocs.dbhelper.ConexaoSQlite;

import java.util.ArrayList;
import java.util.List;

public class FonteDAO {

    private final ConexaoSQlite conexaoSQlite;

    public FonteDAO(ConexaoSQlite conexaoSQlite) {
        this.conexaoSQlite = conexaoSQlite;
    }

    public long salvarFonteDAO (ModeloFonte f){

        SQLiteDatabase db = conexaoSQlite.getWritableDatabase();

        try{
            ContentValues values = new ContentValues();
            values.put("descricao",f.getDescricao());

            long coddescricaoinserida = db.insert("fonte", null, values);

            return coddescricaoinserida;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(db != null){
                db.close();
            }
        }
        return 0;
    }

    public boolean atualizarFonteDAO(ModeloFonte f){
        SQLiteDatabase db = null;
        try{
            db = this.conexaoSQlite.getWritableDatabase();

            ContentValues fontedescricao = new ContentValues();

            fontedescricao.put("descricao", f.getDescricao());

            int atualizou = db.update("fonte",
                    fontedescricao,
                    "cod_fonte = ?",
                    new String[]{String.valueOf(f.getCodfonte())}
                    );

            if (atualizou > 0){
                return true;
            }
            else{
                return false;
            }


        }catch(Exception e){
            Log.d("FONTEDAO", "Não foi possivel atualizar a fonte");
            return false;
        }finally {
            if (db != null){
                db.close();
            }
        }
    }

    public boolean excluirFonteDAO (long fcodFonte){
        exluirDadosReceitaDAO(fcodFonte);
        SQLiteDatabase db = null;

        try{
            db = this.conexaoSQlite.getWritableDatabase();

            db.delete(
                    "fonte",
                    "cod_fonte = ?",
                    new String[]{String.valueOf(fcodFonte)}
            );

        }catch(Exception e){
            Log.d("FONTEDAO", "Não foi possivel deletar fonte");
            return false;
        }
        finally {
            if(db != null){
                db.close();
            }
        }
        return true;
    }

    public boolean exluirDadosReceitaDAO (long fcodFonte){
        SQLiteDatabase db = null;

        try{
            db = this.conexaoSQlite.getWritableDatabase();

            db.delete(
                    "receita",
                    "cod_fonte = ?",
                    new String[]{String.valueOf(fcodFonte)}
            );

        }catch(Exception e){
            Log.d("FONTEDAO", "Não foi possivel deletar receita");
            return false;
        }
        finally {
            if(db != null){
                db.close();
            }
        }
        return true;
    }

    public List<ModeloFonte> getListaFontesDAO(){

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

    public List<ModeloFonte> search(String keyword) {
        List<ModeloFonte> contacts = null;
        try {
            SQLiteDatabase sqLiteDatabase = this.conexaoSQlite.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from fonte where descricao like ?", new String[] { "%" + keyword + "%" });
            if (cursor.moveToFirst()) {
                contacts = new ArrayList<ModeloFonte>();
                do {
                    ModeloFonte contact = new ModeloFonte();
                    contact.setDescricao(cursor.getString(1));
                    contacts.add(contact);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            contacts = null;
        }
        return contacts;
    }

}
