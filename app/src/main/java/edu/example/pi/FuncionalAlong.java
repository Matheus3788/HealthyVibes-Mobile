package edu.example.pi;

import android.content.Intent;
import android.os.Bundle;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercicios_funcional_alongamento);

        btnAlongCobra = findViewById(R.id.btnalongcobra);
        btnSprinter = findViewById(R.id.btnsprinter);
        btnSalamba = findViewById(R.id.btnsalamba);


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
}
