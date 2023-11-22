package edu.example.pi.Imc;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import edu.example.pi.EditProfile;
import edu.example.pi.R;
import edu.example.pi.Receitas.DeleteRecipe;

public class AdicionarImc extends AppCompatActivity {

    Button btnadiciona;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imc_grafico_layout);

        btnadiciona = findViewById(R.id.btnaddimc);

        btnadiciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(AdicionarImc.this);

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.imc_modal_adicionar);
                dialog.show();


            }
        });
    }
}
