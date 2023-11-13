
package edu.example.pi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    TextView userNameTextView ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        Button btnCalculadora = findViewById(R.id.btnCalculadora);
        Button btnTeste = findViewById(R.id.btnCalorias);
        userNameTextView  = findViewById(R.id.txtUserName);

        String userN = getIntent().getStringExtra("userName");

        setUserName(userN);

        btnCalculadora.setOnClickListener( e->{
            Intent irCalculadora = new Intent(Home.this, Calculadora.class);
            startActivity(irCalculadora);
        });

        btnTeste.setOnClickListener(e->{
            Intent irEditProfile = new Intent(Home.this, EditProfile.class);
            startActivity(irEditProfile);
        });

    }

    public void setUserName(String userName) {
        userNameTextView .setText(userName);
    }
}
