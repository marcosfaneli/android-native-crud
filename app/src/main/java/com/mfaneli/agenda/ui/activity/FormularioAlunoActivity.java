package com.mfaneli.agenda.ui.activity;

import static com.mfaneli.agenda.ui.activity.ConstantsActivities.ALUNO_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mfaneli.agenda.R;
import com.mfaneli.agenda.dao.AlunoDao;
import com.mfaneli.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITLE_NEW = "Novo Aluno";
    public static final String TITLE_EDIT = "Edita Aluno";
    private final AlunoDao dao = new AlunoDao();
    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtTelefone;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(TITLE_NEW);
        setContentView(R.layout.activity_formulario_aluno);

        init();
        config();

        loadData();
    }

    private void loadData() {
        Intent intent = getIntent();

        if (!intent.hasExtra(ALUNO_KEY)) return;

        setTitle(TITLE_EDIT);
        aluno = (Aluno) intent.getSerializableExtra(ALUNO_KEY);
        edtNome.setText(aluno.getNome());
        edtEmail.setText(aluno.getEmail());
        edtTelefone.setText(aluno.getTelefone());

    }

    private void config() {
        Button btnSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        btnSalvar.setOnClickListener(view -> salvarAluno());
    }

    private void init() {
        aluno = new Aluno();

        edtNome = findViewById(R.id.activity_formulario_aluno_nome);
        edtEmail = findViewById(R.id.activity_formulario_aluno_email);
        edtTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
    }

    private void salvarAluno() {
        aluno.update(edtNome.getText().toString(), edtEmail.getText().toString(), edtTelefone.getText().toString());
        dao.salvar(aluno);

        Toast.makeText(FormularioAlunoActivity.this, "Aluno " + aluno.getNome() + " salvo com sucesso!", Toast.LENGTH_SHORT).show();
        finish();
    }
}