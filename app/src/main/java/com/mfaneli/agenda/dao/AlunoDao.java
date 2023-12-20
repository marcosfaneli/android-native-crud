package com.mfaneli.agenda.dao;

import com.mfaneli.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlunoDao {

    private static final List<Aluno> data = new ArrayList<>();
    public void salvar(Aluno aluno) {
        this.remover(aluno);
        data.add(aluno);
    }

    public List<Aluno> todos() {
        return Collections.unmodifiableList(data);
    }

    public void remover(Aluno aluno) {
        data.stream()
                .filter(alunoExisting -> alunoExisting.getId().equals(aluno.getId()))
                .findFirst()
                .ifPresent(data::remove);
    }
}
