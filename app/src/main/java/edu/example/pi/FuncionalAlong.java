package edu.example.pi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import edu.example.pi.videos.Abdominal;
import edu.example.pi.videos.AlongamentoCobra;

public class FuncionalAlong extends AppCompatActivity {

    ImageButton btnAlongCobra;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercicios_funcional_alongamento);

        btnAlongCobra = findViewById(R.id.btnalongcobra);


        btnAlongCobra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FuncionalAlong.this, AlongamentoCobra.class);
                startActivity(intent);
            }
        });
    }
}
