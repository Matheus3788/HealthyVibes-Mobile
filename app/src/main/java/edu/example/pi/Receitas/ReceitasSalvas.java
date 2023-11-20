package edu.example.pi.Receitas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.example.pi.Login;
import edu.example.pi.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReceitasSalvas extends AppCompatActivity {
    private RecyclerView recyclerView;
    private  List<RecipeResponse> recipeList = new ArrayList<>();

    Button btnaddreceita;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receitas_salvas_layout);

        btnaddreceita = findViewById(R.id.btnaddreceita);
        recyclerView = findViewById(R.id.recyclerViewDietas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Pegando id e token
        SharedPreferences sharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        String id = sharedPreferences.getString("id", "");



        btnaddreceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReceitasSalvas.this, AdicionarReceita.class);
                startActivity(intent);
            }
        });

//         String _token = "JWT eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY1MDI0ZDg4MWZmYjliMGM1MGFkMTJiOSIsImlhdCI6MTcwMDQwODY3OCwiZXhwIjoxNzAwNDk1MDc4fQ.3xLOOywruNXOwltyAsVTlWTdOeDclJGWTnPeMMr7T-I";
//        String _id = "6558f6a8c3a196ddd47d2b44";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://healthyvibes-rest-api-back-end-production.up.railway.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crie uma instância do seu serviço
        RecipeService recipeService = retrofit.create(RecipeService.class);

        // Faça a chamada para obter a receita específica do usuário
        Call<List<RecipeResponse>> call = recipeService.getRecipeById(token, id);
        call.enqueue(new Callback<List<RecipeResponse>>() {
            @Override
            public void onResponse(Call<List<RecipeResponse>> call, Response<List<RecipeResponse>> response) {
                if (response.isSuccessful()) {
                    recipeList.addAll(response.body());
                        if (!recipeList.isEmpty()) {
                            // Configurar o RecyclerView com o adapter e lista
                            ReceitasAdapter adapter = new ReceitasAdapter(recipeList);
                            recyclerView.setAdapter(adapter);
                        } else {
                            Toast.makeText(ReceitasSalvas.this, "Lista de receitas vazia", Toast.LENGTH_SHORT).show();
                        }
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("API_RESPONSE", "Error response: " + errorBody);
                        Toast.makeText(ReceitasSalvas.this, "Falha ao obter receita: " + errorBody, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<RecipeResponse>> call, Throwable t) {
                Toast.makeText(ReceitasSalvas.this, "Erro de Login2: " + t.getCause(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
