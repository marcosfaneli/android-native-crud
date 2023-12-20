package com.mfaneli.agenda.model;

import java.io.Serializable;
import java.util.UUID;

public class Aluno implements Serializable {

    private UUID id;
    private String nome;
    private String email;
    private String telefone;

    public Aluno(String nome, String email, String telefone) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public Aluno() {
        this.id = UUID.randomUUID();
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return nome;
    }

    public void update(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }
}
