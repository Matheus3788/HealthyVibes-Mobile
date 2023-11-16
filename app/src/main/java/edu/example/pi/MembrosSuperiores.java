package edu.example.pi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import edu.example.pi.videos.Triceps;

public class MembrosSuperiores extends AppCompatActivity {

    ImageButton btntriceps;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercicios_membros_superiores);

        btntriceps = findViewById(R.id.btnsprinter);

        btntriceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MembrosSuperiores.this, Triceps.class);
                startActivity(intent);
            }
        });

    }
}

