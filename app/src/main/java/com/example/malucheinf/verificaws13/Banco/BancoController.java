package com.example.malucheinf.verificaws13.Banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by MalucheInf on 21/03/2018.
 */

public class BancoController {

    private SQLiteDatabase db;
    private SQLite banco;

    public BancoController(Context context){
        banco = new SQLite(context);
    }

    public String insereDado(String nome, String url, String ativo){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(SQLite.nome, nome);
        valores.put(SQLite.url, url);
        valores.put(SQLite.Ativo, ativo);

        resultado = db.insert(SQLite.TABELA, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else {
            return "Registro Inserido com sucesso";
        }

    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {SQLite.nome,SQLite.Ativo};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}