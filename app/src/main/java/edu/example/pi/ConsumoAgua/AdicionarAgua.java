package edu.example.pi.ConsumoAgua;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import edu.example.pi.Imc.AdicionarImc;
import edu.example.pi.R;

public class AdicionarAgua extends AppCompatActivity {

    Button btnadd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumo_agua_layout);


        btnadd = findViewById(R.id.btnaddagua);


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(AdicionarAgua.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.agua_modal_adicionar);
                dialog.show();

                EditText edittextagua = dialog.findViewById(R.id.editaddquant);
                Button btnaddagua = dialog.findViewById(R.id.btnaddagua);


                btnaddagua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int agua = Integer.parseInt(edittextagua.getText().toString());
                        Log.d("Agua", String.valueOf(agua));
                    }
                });


            }
        });
    }
}
