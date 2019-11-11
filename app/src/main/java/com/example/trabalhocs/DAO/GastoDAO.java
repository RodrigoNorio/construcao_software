package com.example.trabalhocs.DAO;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.trabalhocs.Model.ModeloGasto;
import com.example.trabalhocs.dbhelper.ConexaoSQlite;

public class GastoDAO {

    private final ConexaoSQlite conexaoSQlite;

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

            int atualizou = db.update("receita",
                    gastoatt,
                    "cod_gasto = ?",
                    new String[]{String.valueOf(r.getCodgasto())}
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
                    "cod_gasto = ?",
                    new String[]{String.valueOf(rcodDestino)}
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




}
