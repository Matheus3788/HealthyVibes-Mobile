package edu.example.pi;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.text.SpannableStringBuilder;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        TextView textView = findViewById(R.id.textView7);

        Button btnTemporario = findViewById(R.id.btnlogprin);

        // Defina um OnClickListener para a parte clicável (a palavra "aqui")
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ação a ser executada quando "aqui" é clicado
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        btnTemporario.setOnClickListener( e ->{
            Intent intent = new Intent(Login.this, Home.class);
            startActivity(intent);
        });
    }
}

