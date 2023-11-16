package edu.example.pi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import edu.example.pi.videos.Abdominal;

public class MembrosInferioresABS extends AppCompatActivity {

    ImageButton btnAbdominal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercicios_membros_inferiores_abs);

        btnAbdominal = findViewById(R.id.btnsalamba);


        btnAbdominal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MembrosInferioresABS.this, Abdominal.class);
                startActivity(intent);
            }
        });
    }
}
