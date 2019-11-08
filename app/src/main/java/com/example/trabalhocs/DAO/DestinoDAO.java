package com.example.trabalhocs.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.trabalhocs.Model.ModeloDestino;
import com.example.trabalhocs.dbhelper.ConexaoSQlite;

import java.util.ArrayList;
import java.util.List;

public class DestinoDAO {

    private final ConexaoSQlite conexaoSQlite;

    public DestinoDAO(ConexaoSQlite conexaoSQlite) {
        this.conexaoSQlite = conexaoSQlite;
    }

    public long salvarDestinoDAO (ModeloDestino d){

        SQLiteDatabase db = conexaoSQlite.getWritableDatabase();

        try{
            ContentValues values = new ContentValues();
            values.put("descricao",d.getDescricao());

            long coddescricaoinserida = db.insert("destino", null, values);

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

    public boolean atualizarDestinoDAO(ModeloDestino d){
        SQLiteDatabase db = null;
        try{
            db = this.conexaoSQlite.getWritableDatabase();

            ContentValues destinodescricao = new ContentValues();

            destinodescricao.put("descricao", d.getDescricao());

            int atualizou = db.update("destino",
                    destinodescricao,
                    "cod_destino = ?",
                    new String[]{String.valueOf(d.getCoddestino())}
            );

            if (atualizou > 0){
                return true;
            }
            else{
                return false;
            }


        }catch(Exception e){
            Log.d("DESTINODAO", "Não foi possivel atualizar o destino");
            return false;
        }finally {
            if (db != null){
                db.close();
            }
        }
    }

    public boolean excluirDestinoDAO (long fcodDestino){
        //exluirDadosGastosDAO(fcodDestino);                   //COMENTADO PQ NAO EXISTE OS GASTOS AINDA
        SQLiteDatabase db = null;

        try{
            db = this.conexaoSQlite.getWritableDatabase();

            db.delete(
                    "destino",
                    "cod_destino = ?",
                    new String[]{String.valueOf(fcodDestino)}
            );

        }catch(Exception e){
            Log.d("DESTINODAO", "Não foi possivel deletar destino");
            return false;
        }
        finally {
            if(db != null){
                db.close();
            }
        }
        return true;
    }

    public boolean exluirDadosGastosDAO (long fcodDestino){
        SQLiteDatabase db = null;

        try{
            db = this.conexaoSQlite.getWritableDatabase();

            db.delete(
                    "gastos",
                    "cod_destino = ?",
                    new String[]{String.valueOf(fcodDestino)}
            );

        }catch(Exception e){
            Log.d("DESTINODAO", "Não foi possivel deletar gastos");
            return false;
        }
        finally {
            if(db != null){
                db.close();
            }
        }
        return true;
    }

    public List<ModeloDestino> getListaDestinoDAO(){

        List<ModeloDestino> listaDestinos = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor;

        String query = "SELECT *FROM destino;";

        try{

            db = this.conexaoSQlite.getReadableDatabase();

            cursor = db.rawQuery(query, null);

            if(cursor.moveToFirst()){

                ModeloDestino destinoTemporaria = null;

                do{

                    destinoTemporaria = new ModeloDestino();
                    destinoTemporaria.setCoddestino(cursor.getInt(0));
                    destinoTemporaria.setDescricao(cursor.getString(1));

                    listaDestinos.add(destinoTemporaria);

                }while(cursor.moveToNext());
            }

        }catch (Exception e){
            Log.d("Erro Lista Destino", "Erro ao retornar os destinos");
            return null;
        }finally {
            if(db != null){
                db.close();
            }
        }
        return listaDestinos;
    }

    public List<ModeloDestino> search(String keyword) {
        List<ModeloDestino> contacts = null;
        try {
            SQLiteDatabase sqLiteDatabase = this.conexaoSQlite.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from destino where descricao like ?", new String[] { "%" + keyword + "%" });
            if (cursor.moveToFirst()) {
                contacts = new ArrayList<ModeloDestino>();
                do {
                    ModeloDestino contact = new ModeloDestino();
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
