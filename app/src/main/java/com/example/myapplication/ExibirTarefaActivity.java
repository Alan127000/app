package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class ExibirTarefaActivity extends AppCompatActivity {
    TextView tvTarefa;
    CheckBox cbStatus;
    Button btnRemover;
    Tarefa t;
    BD bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_tarefa);
        bd = new BD(this);
        Intent intent = getIntent();
        tvTarefa = findViewById(R.id.tvExibirT);
        cbStatus = findViewById(R.id.cbExibirS);
        btnRemover = findViewById(R.id.btnRemoverTarefa);
        t = new Tarefa(intent.getStringExtra("Tarefa"), Integer.parseInt(intent.getStringExtra("Status")));
        t.setId(Long.parseLong(intent.getStringExtra("Id")));
        tvTarefa.setText("Tarefa: " + t.getTarefa());
        if(t.getStatus() == 1){
            cbStatus.setChecked(true);
            cbStatus.setText("Concluída");
        }else {
            cbStatus.setChecked(false);
            cbStatus.setText("Não concluída");
        }
        cbStatus.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View view){
                bd.atualizarTarefa(t);
                Toast.makeText(view.getContext(), "Tarefa atualizada com sucesso", Toast.LENGTH_LONG).show();
                refreshIntent(view);
            }
        });
        btnRemover.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view){
                bd.deletarTarefa(t.getId());
                Toast.makeText(view.getContext(), "Tarefa removida com sucesso", Toast.LENGTH_LONG).show();
                iniciarIntentMain(view);
            }
        });
    }
    public void iniciarIntentMain(View view) {
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        startActivity(intent);
    }
    public void refreshIntent(View view){
        Intent intent = new Intent(view.getContext(), ExibirTarefaActivity.class);
        intent.putExtra("Id", Long.toString(t.getId()));
        intent.putExtra("Tarefa", t.getTarefa());
        if(t.getStatus() == 1){
            intent.putExtra("Status", Integer.toString(0));
        }else {
            intent.putExtra("Status", Integer.toString(1));
        }
        startActivity(intent);
    }
}