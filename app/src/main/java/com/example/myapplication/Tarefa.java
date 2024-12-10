package com.example.myapplication;

public class Tarefa {
    private long id;
    private int status;
    private String tarefa;

    public Tarefa(String tarefa, int status){
        this.status = status;
        this.tarefa = tarefa;
    }
    public Tarefa() {
        this.tarefa = "";
        this.status = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }
}
