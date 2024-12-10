package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class buscarTarefaActivity extends AppCompatActivity {
    CheckBox cbId;
    CheckBox cbNome;
    EditText etId;
    EditText etNome;
    Button btnBuscarTarefa;
    BD bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_tarefa);
        bd = new BD(this);
        cbId = findViewById(R.id.cbId);
        cbNome = findViewById(R.id.cbNome);
        etId = findViewById(R.id.etId);
        etNome = findViewById(R.id.etNome);
        btnBuscarTarefa = findViewById(R.id.btnBuscarTarefa);
        cbId.setOnClickListener(new CheckBox.OnClickListener(){
            @Override
            public void onClick(View view){
                if(cbId.isChecked()){
                    cbNome.setEnabled(false);
                    etNome.setEnabled(false);
                }else {
                    etId.setText("");
                    cbNome.setEnabled(true);
                    etNome.setEnabled(true);
                }
            }
        });
        cbNome.setOnClickListener(new CheckBox.OnClickListener(){
            @Override
            public void onClick(View view){
                if(cbNome.isChecked()){
                    cbId.setEnabled(false);
                    etId.setEnabled(false);
                }else {
                    etNome.setText("");
                    cbId.setEnabled(true);
                    etId.setEnabled(true);
                }
            }
        });
        btnBuscarTarefa.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view){
                if(!cbId.isChecked() && !cbNome.isChecked()){
                    Toast.makeText(view.getContext(), "Selecione uma das opções", Toast.LENGTH_LONG).show();
                }else {
                    if(cbId.isChecked()){
                        //Busca pelo id
                        if(etId.getText().toString() != ""){
                            Tarefa t = bd.buscarTarefaPorId(Integer.parseInt(etId.getText().toString()));
                            if(t.getTarefa() == "")
                                Toast.makeText(view.getContext(), "Essa tarefa não existe", Toast.LENGTH_LONG).show();
                            else
                                iniciarNovaIntent(t);
                        }
                    }else {
                        //Busca pelo nome
                        if(etNome.getText().toString() != ""){
                            if(etNome.getText().toString() != ""){
                                Tarefa t = bd.buscarTarefaPorNome(etNome.getText().toString());
                                if(t.getTarefa() == "")
                                    Toast.makeText(view.getContext(), "Essa tarefa não existe", Toast.LENGTH_LONG).show();
                                else
                                    iniciarNovaIntent(t);
                            }
                        }
                    }
                }
            }
        });
    }
    public void iniciarNovaIntent(Tarefa t){
        Intent intent = new Intent(this, ExibirTarefaActivity.class);
        intent.putExtra("Tarefa", t.getTarefa());
        intent.putExtra("Status", Integer.toString(t.getStatus()));
        intent.putExtra("Id", Long.toString(t.getId()));
        startActivity(intent);
    }

}