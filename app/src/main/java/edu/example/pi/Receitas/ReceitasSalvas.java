package edu.example.pi.Receitas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import edu.example.pi.R;

public class ReceitasSalvas extends AppCompatActivity {

    Button btnaddreceita;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receitas_salvas_layout);

        btnaddreceita = findViewById(R.id.btnaddreceita);


        btnaddreceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReceitasSalvas.this, AdicionarReceita.class);
                startActivity(intent);
            }
        });
    }

}
