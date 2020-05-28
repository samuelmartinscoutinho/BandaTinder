package com.tindermusical;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView texto = (TextView) findViewById(R.id.text);
        String mensagem = getIntent().getStringExtra("mensagem");
        texto.setText(mensagem);
    }
}
