package edu.example.pi.ConsumoCalorias;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import edu.example.pi.R;

public class AdicionarCalorias extends AppCompatActivity {

    Button addcal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumo_caloria_layout);

        addcal = findViewById(R.id.btnadd);

        addcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(AdicionarCalorias.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.calorias_modal_adicionar);
                dialog.show();


                EditText edittextcalorias = dialog.findViewById(R.id.editaddquantcal);
                Button btnaddcal = dialog.findViewById(R.id.btnaddcal);

                btnaddcal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int calorias = Integer.parseInt(edittextcalorias.getText().toString());
                        Log.d("Calorias", String.valueOf(calorias));

                    }
                });



            }
        });

    }
}
