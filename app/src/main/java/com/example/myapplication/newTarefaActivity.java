package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class newTarefaActivity extends AppCompatActivity {
    EditText etTarefa;
    CheckBox cbStatus;
    Tarefa t;
    BD bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tarefa);
        etTarefa = findViewById(R.id.editTextTextPersonName);
        cbStatus = findViewById(R.id.cbStatusNova);
        cbStatus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(cbStatus.isChecked()){
                    cbStatus.setText("Concluída");
                }else {
                    cbStatus.setText("Não concluída");
                }
            }
        });
        bd = new BD(this);
        Button btnCriarTarefa = findViewById(R.id.btnCriarTarefa);
        btnCriarTarefa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                inserirTarefa();
            }
        });
    }
    public void inserirTarefa() {
        int status;
        if(cbStatus.isChecked()){
            status = 1;
        }else {
            status = 0;
        }
        t = new Tarefa(etTarefa.getText().toString(), status);
        bd.cadastrarTarefa(t);
        Toast.makeText(this, "Cadastrada a tarefa", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}