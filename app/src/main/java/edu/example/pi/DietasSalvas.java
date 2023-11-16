package edu.example.pi;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


import edu.example.pi.Dietas.Dieta;
import edu.example.pi.Dietas.DietasAdapter;

public class DietasSalvas extends AppCompatActivity {

    Button btnAddDietas;
    private ArrayList<Dieta> listaDie = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dietas_salvas_layout);

        btnAddDietas = findViewById(R.id.btnAddDieta);


        btnAddDietas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modalAddDieta();
            }
        });
    }


    void modalAddDieta(){
        final Dialog dialog = new Dialog(DietasSalvas.this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dietas_modal_adicionar);

        ImageButton btnvoltaradd = dialog.findViewById(R.id.btnvoltaradd);


        btnvoltaradd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
