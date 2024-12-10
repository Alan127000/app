package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zetoniazzo on 21/10/15.
 */
public class BD {
    private SQLiteDatabase bd;
    private Context context;

    public BD(Context ctx){
        //instancio o Core
        this.context = ctx;
        BDCore auxBd = new BDCore(ctx);
        //inicializo o Database com permissão de escrita
        bd = auxBd.getWritableDatabase();
    }

    //agora posso fazer meu CRUD
    //---------------------------------------------
    //   CRUD PARA A TABELA USUARIO
    //---------------------------------------------
    public void cadastrarTarefa(Tarefa t) {
        ContentValues dados = new ContentValues();
        dados.put("tarefa", t.getTarefa());
        dados.put("status", t.getStatus());
        long id = bd.insert("tarefas", null, dados);
        Log.i("Banco", "Inseriu");
    }

    public void atualizarTarefa(Tarefa tarefa){
        ContentValues dados = new ContentValues();
        dados.put("tarefa", tarefa.getTarefa());
        if(tarefa.getStatus() == 0){
            dados.put("status", 1);
        }else {
            dados.put("status", 0);
        }
        bd.update("tarefas", dados, "_id = " + tarefa.getId(), null);
        Log.d("Banco","---- Atualizou Registro -----");
    }//fim do update

    public void deletarTarefa(long id) {
        bd.delete("tarefas", "_id = " +id, null);
        Log.d("Banco","---- Deletou Registro -----");

    }

    //retornar todos os usuarios
    public List<Tarefa> buscarTarefas(){
        //criando uma lista vazia para evitar NullPointerException
        List<Tarefa> lista = new ArrayList<Tarefa>();
        String[] colunas = new String[]{"_id","tarefa","status"};
        //criamos um cursor para buscar os dados
        Cursor cursor = bd.query("tarefas",colunas, null, null, null, null,"_id ASC");
        Log.i("Cheguei", "Cheguei aqui");
        if(cursor.getCount() > 0) {
            cursor.moveToFirst(); //não esquecer de posicionar no primeiro
            do {
                Tarefa tmp = new Tarefa();
                tmp.setId(cursor.getLong(0)); //pega coluna pelo índice
                tmp.setTarefa(cursor.getString(1));
                tmp.setStatus(cursor.getInt(2));
                lista.add(tmp);

            } while(cursor.moveToNext());

        }
        Log.d("Banco","---- Executou consulta -----");
        return lista;
    }

    public Tarefa buscarTarefaPorId(long id){
        Tarefa t = new Tarefa();
        String[] colunas = new String[]{"_id","tarefa", "status"};

        Cursor cursor = bd.query("tarefas",colunas,"_id ="+id,null,null,null,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            t.setId(cursor.getLong(0));
            t.setTarefa(cursor.getString(1));
            t.setStatus(cursor.getInt(2));
        }
        Log.d("Banco","------ Consultou um usuário ------");
        return t;
    }
    public Tarefa buscarTarefaPorNome(String nome){
        Tarefa t = new Tarefa();
        String[] whereArgs = new String[] { "%" + nome + "%" };
        String[] colunas = new String[]{"_id", "tarefa", "status"};
        Cursor cursor = bd.query("tarefas", colunas, "tarefa like ?", whereArgs, null, null, null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            t.setId(cursor.getLong(0));
            t.setTarefa(cursor.getString(1));
            t.setStatus(cursor.getInt(2));
        }
        return t;
    }

}
