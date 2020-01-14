package com.example.trabalhocs.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.trabalhocs.Model.ModeloGasto;
import com.example.trabalhocs.View.Login;
import com.example.trabalhocs.dbhelper.ConexaoSQlite;

import java.util.ArrayList;
import java.util.List;

public class GastoDAO {

    private final ConexaoSQlite conexaoSQlite;

    Login login = new Login();
    final int cod_pessoa = Login.codusuario;

    public GastoDAO(ConexaoSQlite conexaoSQlite) {
        this.conexaoSQlite = conexaoSQlite;
    }

    public long salvarGastoDAO (ModeloGasto r){

        SQLiteDatabase db = conexaoSQlite.getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put("valor",r.getValor());
            values.put("data", r.getDate());
            values.put("cod_destino", r.getCod_destino());
            values.put("cod_pessoa", cod_pessoa);


            long gastoinserido = db.insert("gasto", null, values);

            return gastoinserido;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(db != null){
                db.close();
            }
        }
        return 0;
    }

    public boolean atualizarGastoDAO(ModeloGasto r){
        SQLiteDatabase db = null;
        try{
            db = this.conexaoSQlite.getWritableDatabase();

            ContentValues gastoatt = new ContentValues();
            gastoatt.put("valor", r.getValor());
            gastoatt.put("data", r.getDate());
            gastoatt.put("cod_destino", r.getCod_destino());
            gastoatt.put("cod_pessoa", cod_pessoa);


            int atualizou = db.update("gasto",
                    gastoatt,
                    "cod_gasto = ? AND cod_pessoa = ?",
                    new String[]{String.valueOf(r.getCodgasto()), String.valueOf(cod_pessoa)}
            );

            if (atualizou > 0){
                return true;
            }
            else{
                return false;
            }


        }catch(Exception e){
            Log.d("GASTODAO", "Não foi possivel atualizar o Gasto");
            return false;
        }finally {
            if (db != null){
                db.close();
            }
        }
    }

    public boolean excluirGastoDAO (long rcodDestino){
        SQLiteDatabase db = null;

        try{
            db = this.conexaoSQlite.getWritableDatabase();

            db.delete(
                    "gasto",
                    "cod_gasto = ? AND cod_pessoa = ?",
                    new String[]{String.valueOf(rcodDestino), String.valueOf(cod_pessoa)}
            );


        }catch(Exception e){
            Log.d("GASTODAO", "Não foi possivel deletar Gasto");
            return false;
        }
        finally {
            if(db != null){
                db.close();
            }
        }
        return true;
    }

    public List<ModeloGasto> search(String keyword, int selecionar) {
        List<ModeloGasto> contacts = null;
        try {
            SQLiteDatabase sqLiteDatabase = this.conexaoSQlite.getReadableDatabase();

            Cursor cursor = sqLiteDatabase.rawQuery("select valor,data, * from gasto where data like ? and cod_destino = ? and cod_pessoa = ?",
                    new String[] { "%" + keyword + "%", String.valueOf(selecionar), String.valueOf(cod_pessoa)});
            if (cursor.moveToFirst()) {
                contacts = new ArrayList<ModeloGasto>();
                do {
                    ModeloGasto contact = new ModeloGasto();
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

    public List<ModeloGasto> getListaGastoDAO(int selecionar){

        List<ModeloGasto> listaGasto = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor;

        String query = "SELECT * FROM gasto WHERE cod_destino = '" + selecionar + "'";

        try{

            db = this.conexaoSQlite.getReadableDatabase();

            cursor = db.rawQuery("SELECT * FROM gasto WHERE cod_pessoa = ? AND cod_destino = ?", new String[] {String.valueOf(cod_pessoa), String.valueOf(selecionar)});

            if(cursor.moveToFirst()){

                ModeloGasto gastoTemporario = null;

                do{

                    gastoTemporario = new ModeloGasto();
                    gastoTemporario.setCodgasto(cursor.getInt(0));
                    gastoTemporario.setValor(cursor.getFloat(1));
                    gastoTemporario.setDate(cursor.getString(2));
                    gastoTemporario.setCod_destino(cursor.getInt(3));

                    listaGasto.add(gastoTemporario);

                }while(cursor.moveToNext());
            }

        }catch (Exception e){
            Log.d("Erro Lista Gasto", "Erro ao retornar os Gastos");
            return null;
        }finally {
            if(db != null){
                db.close();
            }
        }
        return listaGasto;
    }


}
