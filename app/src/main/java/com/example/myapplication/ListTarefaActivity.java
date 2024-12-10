package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class ListTarefaActivity extends ListActivity {
    private BD bd;
    private List<Tarefa> lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_tarefa);
        bd = new BD(this);
        lista = bd.buscarTarefas();
        setListAdapter(new TarefaAdapter(this, lista));
    }

}