
package edu.example.pi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import edu.example.pi.Consumption.ConsumoAgua.AdicionarAgua;
import edu.example.pi.Consumption.ConsumoCalorias.AdicionarCalorias;
import edu.example.pi.Imc.AdicionarImc;

public class Home extends AppCompatActivity {

    TextView userNameTextView ;
    Button btnCalculadora;
    Button btnCalorias;
    Button btnImc;
    Button btnAgua;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

         btnCalculadora = findViewById(R.id.btnCalculadora);
         btnCalorias = findViewById(R.id.btnCalorias);
         btnImc = findViewById(R.id.btnImc);
         btnAgua = findViewById(R.id.btnAgua);

        userNameTextView  = findViewById(R.id.txtUserName);

        String userN = getIntent().getStringExtra("userName");

        setUserName(userN);

        btnCalculadora.setOnClickListener( e->{
            Intent irCalculadora = new Intent(Home.this, Calculadora.class);
            startActivity(irCalculadora);
        });

        btnCalorias.setOnClickListener( e->{
            Intent irCalorias = new Intent(Home.this, AdicionarCalorias.class);
            startActivity(irCalorias);
        });

        btnAgua.setOnClickListener(e->{
            Intent irAgua = new Intent(Home.this, AdicionarAgua.class);
            startActivity(irAgua);
        });

        btnImc.setOnClickListener(e->{
            Intent irIMC = new Intent(Home.this, AdicionarImc.class);
            startActivity(irIMC);

        });

    }

    @Override
    protected  void onStart() {
        super.onStart();
        Log.d("ActivityPrincipal", "on Start executado");
    }

    @Override
    protected void onResume( ){
        super.onResume();
        Log.d("ActivityPrincipal", "on Resume executado");

    }

    @Override
    protected void onPause( ){
        super.onPause();
        Log.d("ActivityPrincipal", "on Pause executado");

    }

    @Override
    protected void onStop( ){
        super.onStop();
        Log.d("ActivityPrincipal", "on Destroy executado");
    }

    @Override
    protected void onDestroy( ){
        super.onDestroy();
        Log.d("ActivityPrincipal", "on Destroy  executado");
    }

    @Override
    protected void onRestart( ){
        super.onRestart();
        Log.d("ActivityPrincipal", "on Restart  executado");
    }

    public void setUserName(String userName) {
        userNameTextView .setText(userName);
    }
}
