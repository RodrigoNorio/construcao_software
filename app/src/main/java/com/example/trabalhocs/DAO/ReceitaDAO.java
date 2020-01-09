package com.example.trabalhocs.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.trabalhocs.Model.ModeloReceita;
import com.example.trabalhocs.View.Login;
import com.example.trabalhocs.dbhelper.ConexaoSQlite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReceitaDAO {


    private final ConexaoSQlite conexaoSQlite;

    Login login = new Login();
    final int cod_pessoa = Login.codusuario;

    public ReceitaDAO(ConexaoSQlite conexaoSQlite) {
        this.conexaoSQlite = conexaoSQlite;
    }

    public long salvarReceitaDAO (ModeloReceita r){

        SQLiteDatabase db = conexaoSQlite.getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put("valor",r.getValor());
            values.put("data", r.getDate());
            values.put("cod_fonte", r.getCodfonte());
            values.put("cod_pessoa", cod_pessoa);

            long receitainserida = db.insert("receita", null, values);

            return receitainserida;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(db != null){
                db.close();
            }
        }
        return 0;
    }

    public boolean atualizarReceitaDAO(ModeloReceita r){
        SQLiteDatabase db = null;
        try{
            db = this.conexaoSQlite.getWritableDatabase();

            ContentValues receitaatt = new ContentValues();
            receitaatt.put("valor", r.getValor());
            receitaatt.put("data", r.getDate());
            receitaatt.put("cod_fonte", r.getCodfonte());
            receitaatt.put("cod_pessoa", cod_pessoa);

            int atualizou = db.update("receita",
                    receitaatt,
                    "cod_receita = ? AND cod_pessoa = ?",
                    new String[]{String.valueOf(r.getCodreceita()),String.valueOf(cod_pessoa)}
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

    public boolean excluirReceitaDAO (long rcodFonte){
        SQLiteDatabase db = null;

        try{
            db = this.conexaoSQlite.getWritableDatabase();

            db.delete(
                    "receita",
                    "cod_receita = ? AND cod_pessoa =?",
                    new String[]{String.valueOf(rcodFonte), String.valueOf(cod_pessoa)}
            );

        }catch(Exception e){
            Log.d("RECEITADAO", "Não foi possivel deletar receita");
            return false;
        }
        finally {
            if(db != null){
                db.close();
            }
        }
        return true;
    }

    public List<ModeloReceita> search(String keyword, int selecionar) {
        List<ModeloReceita> contacts = null;
        try {
            SQLiteDatabase sqLiteDatabase = this.conexaoSQlite.getReadableDatabase();

            Cursor cursor = sqLiteDatabase.rawQuery("select valor,data, * from receita where data like ? and cod_fonte = ? and cod_pessoa = ?",
                    new String[] { "%" + keyword + "%",String.valueOf(selecionar), String.valueOf(cod_pessoa)});
            if (cursor.moveToFirst()) {
                contacts = new ArrayList<ModeloReceita>();
                do {
                    ModeloReceita contact = new ModeloReceita();
                    contact.setValor(cursor.getFloat(0));
                    contact.setDate(cursor.getString(1));
                    contacts.add(contact);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            contacts = null;
        }
        return contacts;
    }

    public List<ModeloReceita> getListaReceitaDAO(int selecionar){

        List<ModeloReceita> listaReceitas = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor;

        String query = "SELECT * FROM receita WHERE cod_fonte = ?'" + selecionar + "'";

        try{

            db = this.conexaoSQlite.getReadableDatabase();

            cursor = db.rawQuery("SELECT * FROM receita WHERE cod_pessoa = ? AND cod_fonte = ?", new String[] {String.valueOf(cod_pessoa),String.valueOf(selecionar)});

            if(cursor.moveToFirst()){

                ModeloReceita receitaTemporaria = null;

                do{

                    receitaTemporaria = new ModeloReceita();
                    receitaTemporaria.setCodreceita(cursor.getInt(0));
                    receitaTemporaria.setValor(cursor.getFloat(1));
                    receitaTemporaria.setDate(cursor.getString(2));
                    receitaTemporaria.setCodfonte(cursor.getInt(3));

                    listaReceitas.add(receitaTemporaria);

                }while(cursor.moveToNext());
            }

        }catch (Exception e){
            Log.d("Erro Lista Receita", "Erro ao retornar as receitas");
            return null;
        }finally {
            if(db != null){
                db.close();
            }
        }
        return listaReceitas;
    }

    public String receitatotalDAO(String data1, String data2, int selecionar) {
        SQLiteDatabase db = null;
        Date d1 = stringToDate(data1);
        Date d2 = stringToDate(data2);
        Date converter;
        db = this.conexaoSQlite.getWritableDatabase();
        float total = 0;
        Cursor cursor = db.rawQuery("SELECT data, valor FROM receita WHERE cod_pessoa = ? AND cod_fonte = ?",
                new String[] {String.valueOf(cod_pessoa), String.valueOf(selecionar)});
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
