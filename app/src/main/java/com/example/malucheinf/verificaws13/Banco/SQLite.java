package com.example.malucheinf.verificaws13.Banco;


/**
 * Created by MalucheInf on 21/03/2018.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class SQLite extends SQLiteOpenHelper{
    public static final String NOME_BANCO = "webservice.db";
    public static final String TABELA = "webservices";
    public static final String ID = "_id";
    public static final String nome = "nome";
    public static final String url = "url";
    public static final String Ativo = "ativo";
    public static final int VERSAO = 15;
    public static final int VERSAO_NEW = 6;

    public SQLite(Context context){
        super(context,NOME_BANCO,null,VERSAO);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String sql = "CREATE TABLE webservices (_id interger primary key, nome text, url text, ativo text)";
        String sql = "CREATE TABLE "+TABELA+"("
                + ID + " integer primary key autoincrement,"
                + nome + " text,"
                + url + " text,"
                + Ativo + " text" +
                ")";
        Log.i("SQLite", sql);
        db.execSQL(sql);
        Log.i("SQLite", "Banco Criado");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("SQLite","Deu Drop");
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }

}