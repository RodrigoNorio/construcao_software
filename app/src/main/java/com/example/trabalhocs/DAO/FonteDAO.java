package com.example.trabalhocs.DAO;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.trabalhocs.Model.ModeloFonte;
import com.example.trabalhocs.View.Login;
import com.example.trabalhocs.dbhelper.ConexaoSQlite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FonteDAO {

    private final ConexaoSQlite conexaoSQlite;

    Login login = new Login();
    final int cod_pessoa = Login.codusuario;

    public FonteDAO(ConexaoSQlite conexaoSQlite) {
        this.conexaoSQlite = conexaoSQlite;
    }

    public long salvarFonteDAO (ModeloFonte f){

        SQLiteDatabase db = conexaoSQlite.getWritableDatabase();

        try{
            ContentValues values = new ContentValues();
            values.put("descricao",f.getDescricao());
            values.put("cod_pessoa", cod_pessoa);

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
            fontedescricao.put("cod_pessoa", cod_pessoa);

            int atualizou = db.update("fonte",
                    fontedescricao,
                    "cod_fonte = ? AND cod_pessoa",
                    new String[]{String.valueOf(f.getCodfonte()),String.valueOf(cod_pessoa)}
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
                    "cod_fonte = ? AND cod_pessoa = ?",
                    new String[]{String.valueOf(fcodFonte), String.valueOf(cod_pessoa)}
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
                    "cod_fonte = ? AND cod_pessoa = ?",
                    new String[]{String.valueOf(fcodFonte), String.valueOf(cod_pessoa)}
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

            cursor = db.rawQuery("SELECT * FROM fonte WHERE cod_pessoa = ?", new String[] {String.valueOf(cod_pessoa)});

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

    public String fontetotalDAO(String data1, String data2) {
        SQLiteDatabase db = null;
        Date d1 = stringToDate(data1);
        Date d2 = stringToDate(data2);
        Date converter;
        db = this.conexaoSQlite.getWritableDatabase();
        float total = 0;
        //Cursor cursor = db.rawQuery("SELECT data, valor FROM receita WHERE data = ?", new String[]{data1});
        Cursor cursor = db.rawQuery("SELECT data, valor FROM receita",null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                converter = stringToDate(cursor.getString(0));
                if (converter.compareTo(d1) >= 0 && converter.compareTo(d2) <= 0){
                    total = total + Float.parseFloat(cursor.getString(1));
                }
            }
            while(cursor.moveToNext() && cursor.getString(0) != data2);
            return String.valueOf(total);
        }
        else{
            return "0";
        }
    }

    public Date stringToDate(String data1) {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        f.setLenient(false);
        java.util.Date d1 = null;
        try {
            d1 = f.parse(data1);
        } catch (ParseException e) {}
        return d1;
    }

}
