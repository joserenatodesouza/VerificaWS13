package com.example.malucheinf.verificaws13.Banco;


/**
 * Created by MalucheInf on 21/03/2018.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLite extends SQLiteOpenHelper{
    private static final String NOME_BANCO = "banco.db";
    public static final String TABELA = "webservices";
    private static final String ID = "id";
    public static final String nome = "nome";
    public static final String url = "url";
    public static final String Ativo = "ativo";
    private static final int VERSAO = 1;

    public SQLite(Context context){
        super(context,NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"("
                + ID + " integer primary key autoincrement,"
                + nome + " text,"
                + url + " text,"
                + Ativo + " text" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABELA);
        onCreate(db);
    }

}
