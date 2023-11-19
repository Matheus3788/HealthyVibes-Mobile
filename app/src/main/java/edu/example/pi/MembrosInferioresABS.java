package edu.example.pi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import edu.example.pi.videos.Abdominal;
import edu.example.pi.videos.Agachamento_Simples;
import edu.example.pi.videos.Prancha;

public class MembrosInferioresABS extends AppCompatActivity {

    ImageButton btnAbdominal;
    ImageButton btnPrancha;
    ImageButton btnAgachamento;
    ImageButton btnVoltar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercicios_membros_inferiores_abs);

        btnVoltar = findViewById(R.id.btnvoltar);
        btnAbdominal = findViewById(R.id.btnsalamba);
        btnPrancha = findViewById(R.id.btnalongcobra);
        btnAgachamento = findViewById(R.id.btnsprinter);

        btnVoltar.setOnClickListener(e->{
            Intent intent = new Intent(MembrosInferioresABS.this, Exercicio.class);
            startActivity(intent);
        });

        btnAbdominal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MembrosInferioresABS.this, Abdominal.class);
                startActivity(intent);
            }
        });

        btnPrancha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MembrosInferioresABS.this, Prancha.class);
                startActivity(intent);
            }
        });
        btnAgachamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MembrosInferioresABS.this, Agachamento_Simples.class);
                startActivity(intent);
            }
        });
    }
}
