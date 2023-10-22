package edu.example.pi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;
import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.Handler;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;
import com.github.kittinunf.fuel.core.FuelError;
import com.google.gson.Gson;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import edu.example.pi.interfac.UserResponse;
import edu.example.pi.interfac.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import edu.example.pi.interfac.UserResponse;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;


public class Register extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        final EditText edtName = findViewById(R.id.editName);
        final EditText edtEmail = findViewById(R.id.editemail);
        final EditText edtPassword = findViewById(R.id.editPassword);
        Button btnRegister = findViewById(R.id.btnRegister);



        TextView textView = findViewById(R.id.textView7);


        btnRegister.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String senha = edtPassword.getText().toString();


// //localhost
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.1.104:3333/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                UserService userService = retrofit.create(UserService.class);

                User user = new User(nome, email, senha);

                Call<UserResponse> call = userService.createUser(user);
                call.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, retrofit2.Response<UserResponse> response) {
                        if (response.isSuccessful()) {
                            UserResponse userResponse = (UserResponse) response.body();
                            String message = userResponse.getMessage();
                            Toast.makeText(Register.this, "Registro bem-sucedido: " + message, Toast.LENGTH_SHORT).show();
                        }else {
                            // Registro falhou
                            try {
                                String errorBody = response.errorBody().string();
                                Log.e("Erro de Registro", errorBody);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(Register.this, "Erro de Registro", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Toast.makeText(Register.this, "Erro de Registro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }));



        // Defina um OnClickListener para a parte clicável (a palavra "aqui")
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ação a ser executada quando "aqui" é clicado
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });



    }
}
