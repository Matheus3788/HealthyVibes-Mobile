package edu.example.pi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        Button btnCalculadora = findViewById(R.id.btnCalculadora);
        Button btnTeste = findViewById(R.id.btnCalorias);

        btnCalculadora.setOnClickListener( e->{
            Intent irCalculadora = new Intent(Home.this, Calculadora.class);
            startActivity(irCalculadora);
        });


    }
}
