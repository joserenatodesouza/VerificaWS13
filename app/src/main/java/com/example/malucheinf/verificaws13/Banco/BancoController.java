package com.example.malucheinf.verificaws13.Banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by MalucheInf on 21/03/2018.
 */

public class BancoController {

    private SQLiteDatabase db;
    private SQLite banco;
    List<Model> models = new LinkedList<Model>();
    public BancoController(Context context){
        banco = new SQLite(context);
    }

    public String insereDado(String id, String nome, String url, String ativo){
        ContentValues valores;
        long resultado;


        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(SQLite.nome, nome);
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
        String[] campos =  {SQLite.nome, SQLite.Ativo};
        db = banco.getReadableDatabase();
        cursor = db.query(SQLite.TABELA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}