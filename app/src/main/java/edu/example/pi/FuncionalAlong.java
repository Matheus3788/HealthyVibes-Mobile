package edu.example.pi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import edu.example.pi.videos.Abdominal;
import edu.example.pi.videos.AlongamentoCobra;
import edu.example.pi.videos.Salamba;
import edu.example.pi.videos.Sprinter;

public class FuncionalAlong extends AppCompatActivity {

    ImageButton btnAlongCobra;
    ImageButton btnSprinter;
    ImageButton btnSalamba;
    ImageButton btnVoltar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercicios_funcional_alongamento);

        btnAlongCobra = findViewById(R.id.btnalongcobra);
        btnSprinter = findViewById(R.id.btnsprinter);
        btnSalamba = findViewById(R.id.btnsalamba);
        btnVoltar = findViewById(R.id.btnvoltar);

        btnVoltar.setOnClickListener(e->{
            Intent intent = new Intent(FuncionalAlong.this, Exercicio.class);
            startActivity(intent);
        });

        btnAlongCobra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FuncionalAlong.this, AlongamentoCobra.class);
                startActivity(intent);
            }
        });

        btnSprinter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FuncionalAlong.this, Sprinter.class);
                startActivity(intent);
            }
        });

        btnSalamba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FuncionalAlong.this, Salamba.class);
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
