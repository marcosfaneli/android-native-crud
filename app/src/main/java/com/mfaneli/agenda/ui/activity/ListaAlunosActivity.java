package com.mfaneli.agenda.ui.activity;

import static com.mfaneli.agenda.ui.activity.ConstantsActivities.ALUNO_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mfaneli.agenda.R;
import com.mfaneli.agenda.dao.AlunoDao;
import com.mfaneli.agenda.model.Aluno;

import java.util.List;
import java.util.stream.Collectors;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITLE = "Lista de Alunos";
    private final AlunoDao dao = new AlunoDao();
    private ListView listaAlunos;
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(TITLE);
        setContentView(R.layout.activity_lista_alunos);

        config();
        dao.salvar(new Aluno("Marcos", "marcosfaneli@gmail.com", "1188888888"));
        dao.salvar(new Aluno("Paola", "paolasbasso@gmail.com", "11999999999"));
    }

    private void config() {
        configListView();
        configClickFabNewAluno();
    }

    private void configListView() {
        listaAlunos = findViewById(R.id.activity_lista_alunos_listview);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listaAlunos.setAdapter(adapter);

        configClickItemList();
        configLongClickItemList();
    }

    private void configLongClickItemList() {
        listaAlunos.setOnItemLongClickListener((adapterView, view, position, id) -> {
            Aluno aluno = (Aluno) adapterView.getItemAtPosition(position);
            Log.i("Open Edit", aluno.toString());
            dao.remover(aluno);
            adapter.remove(aluno);
            return true;
        });
    }

    private void configClickFabNewAluno() {
        FloatingActionButton btnFab = findViewById(R.id.activity_lista_alunos_novo_aluno);
        btnFab.setOnClickListener(view -> openInsertAluno());
    }

    private void configClickItemList() {
        listaAlunos.setOnItemClickListener((adapterView, view, position, id) -> {
            Aluno aluno = (Aluno) adapterView.getItemAtPosition(position);
            openEditAluno(aluno);
        });
    }

    private void openInsertAluno() {
        startActivity(new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class));
    }

    private void openEditAluno(Aluno aluno) {
        Intent intent = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        intent.putExtra(ALUNO_KEY, aluno);
        Log.i("Open Edit", aluno.toString());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter.clear();
        adapter.addAll(dao.todos());
    }
}
