package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    public BD bd;
    public Tarefa t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnNovaTarefa = findViewById(R.id.btnNovaTarefa);
        btnNovaTarefa.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                newUserClick();
            }
        });
        Button btnListaTarefas = findViewById(R.id.btnListaTarefa);
        btnListaTarefas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                listaTarefasClick();
            }
        });
        Button btnBuscar = findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                buscarTarefaClick();
            }
        });
    }
    public void newUserClick(){
        Intent intent = new Intent(this, newTarefaActivity.class);
        startActivity(intent);
    }
    public void listaTarefasClick() {
        Intent intent = new Intent(this, ListTarefaActivity.class);
        startActivity(intent);
    }
    public void buscarTarefaClick() {
        Intent intent = new Intent(this, buscarTarefaActivity.class);
        startActivity(intent);
    }
}
