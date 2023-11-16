
package edu.example.pi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    TextView userNameTextView ;
    Button btnCalculadora;
    Button btnCalorias;
    Button btnImc;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

         btnCalculadora = findViewById(R.id.btnCalculadora);
         btnCalorias = findViewById(R.id.btnCalorias);
         btnImc = findViewById(R.id.btnImc);

        userNameTextView  = findViewById(R.id.txtUserName);

        String userN = getIntent().getStringExtra("userName");

        setUserName(userN);

        btnCalculadora.setOnClickListener( e->{
            Intent irCalculadora = new Intent(Home.this, Calculadora.class);
            startActivity(irCalculadora);
        });





    }

    public void setUserName(String userName) {
        userNameTextView .setText(userName);
    }
}
