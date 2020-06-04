package com.tindermusical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void proximaTela(View view){

        Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
        startActivity(intent);
    }

    public void onClickNotificacaoSimples(View view) {
        int id = 1;
        String contentTitle = "FindYourBand";
        String contentText  = "Fulano deu Match com voce!!";
        Intent intent = new Intent(getBaseContext(), MessageActivity.class);
        intent.putExtra("mensagem", "Oi , toco guitarra");
        NotificacaoUtil.create(getBaseContext(), intent,contentTitle,contentText,id);

    }
}
