package edu.example.pi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    private Button btnreglog;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


        TextView textView = findViewById(R.id.textView7);

        // Defina um OnClickListener para a parte clicável (a palavra "aqui")
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ação a ser executada quando "aqui" é clicado
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });



    }
}
