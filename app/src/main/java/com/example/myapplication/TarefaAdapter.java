package com.example.myapplication;

import static androidx.core.content.ContextCompat.startActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TarefaAdapter extends BaseAdapter {
    private Context context;
    private List<Tarefa> lista;
    BD bd;

    //construtor
    public TarefaAdapter(Context context, List<Tarefa> lista){
        this.context = context;
        this.lista = lista;
        bd = new BD(context);
    }
    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lista.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int posicaoAux = position; //para tornar position final
        //Vamos criar um LayoutInflater: insere um XML dentro de outro
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final LinearLayout layout = (LinearLayout)
                inflater.inflate(R.layout.tarefa,null);

        //preciso colocar o textView do nome do usuario
        TextView tv = (TextView) layout.findViewById(R.id.nomeTarefa);
        tv.setText(lista.get(posicaoAux).getTarefa());
        Button btnRemover = layout.findViewById(R.id.btnRemoverT);
        btnRemover.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.i("Cliquei", "Cliquei no " + lista.get(position).getId());
                bd.deletarTarefa(lista.get(position).getId());
                Toast.makeText(context, "A tarefa foi removida com sucesso", Toast.LENGTH_LONG).show();
            }
        });
        CheckBox cbStatus = layout.findViewById(R.id.cbStatusT);
        cbStatus.setOnClickListener(new CheckBox.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.i("Cliquei", "Cliquei no " + lista.get(position).getId());
                //Aqui atualiza
                bd.atualizarTarefa(lista.get(position));
                Toast.makeText(context, "A tarefa foi atualizada com sucesso", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                view.getContext().startActivity(intent);
            }
        });
        if(lista.get(position).getStatus() == 1){
            cbStatus.setChecked(true);
            cbStatus.setText("Concluída");
        }else {
            cbStatus.setChecked(false);
            cbStatus.setText("Não concluída");
        }
        return layout;
    }
}
