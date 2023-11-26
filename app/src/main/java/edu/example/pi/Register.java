package edu.example.pi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import edu.example.pi.Cadastro.UserResponse;
import edu.example.pi.Cadastro.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import java.io.IOException;


public class Register extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        final EditText edtName = findViewById(R.id.editemaillog);
        final EditText edtEmail = findViewById(R.id.editemail);
        final EditText edtPassword = findViewById(R.id.editPassword);
        final EditText edtconfpassword = findViewById(R.id.editconpassword);
        Button btnRegister = findViewById(R.id.btnRegister);



        TextView textView = findViewById(R.id.textView7);


        btnRegister.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String senha = edtPassword.getText().toString();
                String confirmarSenha = edtconfpassword.getText().toString();

                if (!senha.equals(confirmarSenha)) {
                    // Senhas diferentes, exiba uma mensagem de erro
                    Toast.makeText(Register.this, "As senhas não coincidem", Toast.LENGTH_SHORT).show();
                    edtconfpassword.setBackground(ContextCompat.getDrawable(Register.this, R.drawable.edittext_border));
                    return; // Saia do método para evitar o envio da solicitação
                }

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://healthyvibes-rest-api-back-end-production.up.railway.app/")
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
}
