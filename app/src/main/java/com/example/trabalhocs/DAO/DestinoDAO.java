package com.example.trabalhocs.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.trabalhocs.Model.ModeloDestino;
import com.example.trabalhocs.View.Login;
import com.example.trabalhocs.dbhelper.ConexaoSQlite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DestinoDAO {

    private final ConexaoSQlite conexaoSQlite;

    Login login = new Login();
    final int cod_pessoa = Login.codusuario;

    public DestinoDAO(ConexaoSQlite conexaoSQlite) {
        this.conexaoSQlite = conexaoSQlite;
    }

    public long salvarDestinoDAO (ModeloDestino d){

        SQLiteDatabase db = conexaoSQlite.getWritableDatabase();

        try{
            ContentValues values = new ContentValues();
            values.put("descricao",d.getDescricao());
            values.put("cod_pessoa", cod_pessoa);

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
            destinodescricao.put("cod_pessoa", cod_pessoa);

            int atualizou = db.update("destino",
                    destinodescricao,
                    "cod_destino = ? AND cod_pessoa = ?",
                    new String[]{String.valueOf(d.getCoddestino()),String.valueOf(cod_pessoa)}
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
        exluirDadosGastosDAO(fcodDestino);                   //COMENTADO PQ NAO EXISTE OS GASTOS AINDA
        SQLiteDatabase db = null;

        try{
            db = this.conexaoSQlite.getWritableDatabase();

            db.delete(
                    "destino",
                    "cod_destino = ? and cod_pessoa = ?",
                    new String[]{String.valueOf(fcodDestino), String.valueOf(cod_pessoa)}
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
                    "cod_destino = ? and cod_pessoa",
                    new String[]{String.valueOf(fcodDestino),String.valueOf(cod_pessoa)}
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

            cursor = db.rawQuery("SELECT * FROM destino WHERE cod_pessoa = ?", new String[] {String.valueOf(cod_pessoa)});

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
            Cursor cursor = sqLiteDatabase.rawQuery("select * from destino where descricao like ? and cod_pessoa = ?",
                    new String[] { "%" + keyword + "%",String.valueOf(cod_pessoa)});
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


    public String destinototalDAO(String data1, String data2) {
        SQLiteDatabase db = null;
        Date d1 = stringToDate(data1);
        Date d2 = stringToDate(data2);
        Date converter;
        db = this.conexaoSQlite.getWritableDatabase();
        float total = 0;
        Cursor cursor = db.rawQuery("SELECT data, valor FROM destino WHERE cod_pessoa = ?",new String[] {String.valueOf(cod_pessoa)});
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                converter = stringToDate(cursor.getString(0));
                if (converter.compareTo(d1) >= 0 && converter.compareTo(d2) <= 0){
                    total = total + Float.parseFloat(cursor.getString(1));
                }
            }
            while(cursor.moveToNext());
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
