package edu.example.pi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.example.pi.login.AuthService;
import edu.example.pi.login.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    EditText emaillog;
    EditText senhalog;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        TextView textView = findViewById(R.id.textView7);
        Button btnlogin = findViewById(R.id.btnlogprin);
        emaillog = findViewById(R.id.editemaillog);
        senhalog = findViewById(R.id.editsenhalog);;


        btnlogin.setOnClickListener( e ->{
            String email = emaillog.getText().toString();
            String senha = senhalog.getText().toString();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://healthyvibes-rest-api-back-end-production.up.railway.app/")
                    .addConverterFactory(GsonConverterFactory.create())
                .build();
            Log.d("Retrofit", "Retrofit criado com sucesso");
            AuthService authService = retrofit.create(AuthService.class);

            User user = new User(email, senha);

        Call<LoginResponse> call = authService.loginUser(user);
            Log.d("Retrofit", "Iniciando chamada enqueue");

            call.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    String accessToken = loginResponse.getAccessToken();
                    String user = loginResponse.getUser();
                    String message = loginResponse.getMessage();
                    String userName = loginResponse.getUserName();


                    // Armazene o token de acesso (accessToken) e o ID do usuário (userId) conforme necessário
                    // Exiba uma mensagem ou redirecione para a próxima tela
                    if (accessToken != null && !accessToken.isEmpty()) {
                        accessToken = loginResponse.getAccessToken();
                        user = loginResponse.getUser();

                        SharedPreferences sharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        String token = loginResponse.getAccessToken();
                        String id = loginResponse.getUser();
                        editor.putString("token", token);
                        editor.putString("id", id);
                        editor.commit();

                        Intent intent = new Intent(Login.this, Home.class);
                        intent.putExtra("userName", userName);
                        startActivity(intent);
                        finish(); // Finaliza a LoginActivity para que o usuário não possa voltar pressionando o botão "Voltar"
                    } else {
                        // Token de acesso não disponível, trate conforme necessário
                    }
                } else {
                    // O login falhou
                    Toast.makeText(Login.this, "Erro de Login1", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // O login falhou devido a uma exceção
                Toast.makeText(Login.this, "Erro de Login2: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        });





        // Defina um OnClickListener para a parte clicável (a palavra "aqui")
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ação a ser executada quando "aqui" é clicado
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

    }
}

