package edu.example.pi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import edu.example.pi.videos.FlexaoMediana;
import edu.example.pi.videos.FlexaoSimples;
import edu.example.pi.videos.Triceps;

public class MembrosSuperiores extends AppCompatActivity {

    ImageButton btntriceps;
    ImageButton btnFlexaoMediana;
    ImageButton btnFlexaoSimples;
    ImageButton btnVoltar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercicios_membros_superiores);

        btntriceps = findViewById(R.id.btnsprinter);
        btnFlexaoMediana = findViewById(R.id.btnsalamba);
        btnFlexaoSimples = findViewById(R.id.btnalongcobra);
        btnVoltar = findViewById(R.id.btnvoltar);

        btnVoltar.setOnClickListener(e->{
            Intent intent = new Intent(MembrosSuperiores.this, Exercicio.class);
            startActivity(intent);
        });

        btntriceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MembrosSuperiores.this, Triceps.class);
                startActivity(intent);
            }
        });

        btnFlexaoMediana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MembrosSuperiores.this, FlexaoMediana.class);
                startActivity(intent);
            }
        });
        btnFlexaoSimples.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MembrosSuperiores.this, FlexaoSimples.class);
                startActivity(intent);
            }
        });

    }
}

