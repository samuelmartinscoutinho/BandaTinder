package com.tindermusical;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UsuarioDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;
    private String nome = "nome";
    private String cpf = "cpf";
    private String telefone = "telefone";
    private String nome_tabela = "usuario";

    public UsuarioDAO() {
    }

    public void setConexao(Conexao con){
        conexao = con;
        banco   = conexao.getWritableDatabase();

    }

    public long inserir(Usuario usuario){
        ContentValues  values = new ContentValues();
        values.put(nome, usuario.getNome());
        values.put(cpf, usuario.getCpf());
        values.put(telefone, usuario.getTelefone());
        return banco.insert(nome_tabela, null, values);
    }


    public Usuario consultar(int id){
        Usuario a = new Usuario();
        String querry = "SELECT " + nome + ", " + cpf + ", " + telefone + " FROM " + nome_tabela + " WHERE id="+id;
        Cursor cursor = banco.rawQuery(querry,null);

        while(cursor.moveToNext()){
            a.setNome(cursor.getString(0));
            a.setCpf(cursor.getString(1));
            a.setTelefone(cursor.getString(2));
        }

        return a;
    }
}
