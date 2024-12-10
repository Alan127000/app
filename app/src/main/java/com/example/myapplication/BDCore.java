package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zetoniazzo on 21/10/15.
 */
public class BDCore extends SQLiteOpenHelper {
    public static final String NOME_BD = "teste";
    public static final int VERSAO_BD = 3;

    //construtor da classe
    public BDCore(Context ctx){
        //aqui de fato inicializo o banco
        super(ctx,NOME_BD,null,VERSAO_BD);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //vamos criar a tabela de usuarios
        db.execSQL("create table tarefas(" +
                "_id integer primary key autoincrement," +
                "tarefa text," +
                "status integer);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //quando atualizar o banco vai fazer isso
        //antes de apagar seria conveniente salvar os dados
        //1 - apaga o banco atual
        db.execSQL("drop table tarefas;");
        //2 - cria novamente
        onCreate(db);
        //poderia popular a tabela com os dados
    }

    public void salvarDados(){}
    public void popularTabelas(){}

}
