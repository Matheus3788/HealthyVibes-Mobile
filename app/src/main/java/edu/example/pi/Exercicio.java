package edu.example.pi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Exercicio extends AppCompatActivity {

    ImageButton btnsuperior;
    ImageButton btninferior;
    ImageButton btnfuncional;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercicios_principal);

        btnsuperior = findViewById(R.id.btnsuperior);
        btninferior = findViewById(R.id.btninferior);
        btnfuncional = findViewById(R.id.btnfuncional);

        btnsuperior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Exercicio.this, MembrosSuperiores.class);
                startActivity(intent);
            }
        });

        btninferior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Exercicio.this, MembrosInferioresABS.class);
                startActivity(intent);
            }
        });

        btnfuncional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Exercicio.this, FuncionalAlong.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected  void onStart() {
        super.onStart();
        Log.d("ActivityPrincipal", "on Start executado");
    }

    @Override
    protected void onResume( ){
        super.onResume();
        Log.d("ActivityPrincipal", "on Resume executado");

    }

    @Override
    protected void onPause( ){
        super.onPause();
        Log.d("ActivityPrincipal", "on Pause executado");

    }

    @Override
    protected void onStop( ){
        super.onStop();
        Log.d("ActivityPrincipal", "on Destroy executado");
    }

    @Override
    protected void onDestroy( ){
        super.onDestroy();
        Log.d("ActivityPrincipal", "on Destroy  executado");
    }

    @Override
    protected void onRestart( ){
        super.onRestart();
        Log.d("ActivityPrincipal", "on Restart  executado");
    }
}
