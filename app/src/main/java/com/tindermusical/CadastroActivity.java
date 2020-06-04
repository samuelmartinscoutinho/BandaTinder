package com.tindermusical;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("Registered")
public class CadastroActivity extends AppCompatActivity {


    private EditText nome;
    private EditText cpf;
    private EditText telefone;
    private UsuarioDAO dao;


    private Button btnConsultar;
    private EditText edtID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Button btnSalvar = findViewById(R.id.btnSalvar);
        nome = findViewById(R.id.edtNome);
        cpf  = findViewById(R.id.edtCPF);
        telefone = findViewById(R.id.edtTelefone);

        Conexao conexao = new Conexao(this);
        dao = new UsuarioDAO();
        dao.setConexao(conexao);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });



        //Consultar
        edtID = findViewById(R.id.edtID);
        btnConsultar = findViewById(R.id.btnConsultar);

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(edtID.getText().toString());
                consultar(id);
            }
        });

    }


    public void salvar(){
        Usuario a = new Usuario();
        a.setNome(nome.getText().toString());
        a.setCpf(cpf.getText().toString());
        a.setTelefone(telefone.getText().toString());
        long id = dao.inserir(a);
        Toast.makeText(this, "Aluno com id: " + id, Toast.LENGTH_LONG).show();
    }


    public void consultar(int id){
        Usuario alunoconsulta = dao.consultar(id);
        nome.setText(alunoconsulta.getNome());
        cpf.setText(alunoconsulta.getCpf());
        telefone.setText(alunoconsulta.getTelefone());
    }
}
