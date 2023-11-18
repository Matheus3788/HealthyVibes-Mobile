package edu.example.pi.Receitas;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.IOException;

import edu.example.pi.Cadastro.UserResponse;
import edu.example.pi.Cadastro.UserService;
import edu.example.pi.EditProfile;
import edu.example.pi.R;
import edu.example.pi.Register;
import edu.example.pi.User;
import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;
import java.util.ArrayList;


public class AdicionarReceita extends AppCompatActivity {
    EditText editnome;
    EditText editdificuldade;
    EditText editporcoes;
    EditText editperiodo;
    EditText editpriningrediente, editsegingrediente, editteringrediente, editquartingrediente;
    EditText editcalorias, editcarboidratos, editproteina, editgordura;
    EditText editmododepreparo;

    Button btnadiciona;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receitas_adicionar);

        btnadiciona = findViewById(R.id.btnadiciona);


        editperiodo = findViewById(R.id.editperiodocreceitaadd);
        editnome = findViewById(R.id.editnomereceitaadd);
        editpriningrediente = findViewById(R.id.editpringrereceitaadd);
        editsegingrediente = findViewById(R.id.editsegingrereceitaadd);
        editteringrediente = findViewById(R.id.editteringrereceitaadd);
        editquartingrediente = findViewById(R.id.editquartingrereceitaadd);
        editcalorias = findViewById(R.id.editcaloriasreceitaadd);
        editcarboidratos = findViewById(R.id.editcarboidratosreceitaadd);
        editproteina = findViewById(R.id.editproteinasreceitaadd);
        editgordura = findViewById(R.id.editgordurareceitaadd);
        editmododepreparo = findViewById(R.id.editpreparoreceitaadd);

        btnadiciona.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String periodoRef = editperiodo.getText().toString();
                String titulo = editnome.getText().toString();
                String nomeIngrediente1 = editpriningrediente.getText().toString();
                String nomeIngrediente2 = editsegingrediente.getText().toString();
                String nomeIngrediente3 = editteringrediente.getText().toString();
                String nomeIngrediente4 = editquartingrediente.getText().toString();
                Ingredient ingrediente1 = new Ingredient(nomeIngrediente1);
                Ingredient ingrediente2 = new Ingredient(nomeIngrediente2);
                Ingredient ingrediente3 = new Ingredient(nomeIngrediente3);
                Ingredient ingrediente4 = new Ingredient(nomeIngrediente4);

                List<Ingredient> ingredientes = new ArrayList<>();
                ingredientes.add(ingrediente1);
                ingredientes.add(ingrediente2);
                ingredientes.add(ingrediente3);
                ingredientes.add(ingrediente4);

                int calorias = Integer.parseInt(editcalorias.getText().toString());
                int carboidratos = Integer.parseInt(editcarboidratos.getText().toString());
                int gordura = Integer.parseInt(editgordura.getText().toString());
                int proteina = Integer.parseInt(editproteina.getText().toString());

                String modoDePreparo = editmododepreparo.getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
                String accessToken = sharedPreferences.getString("token", "");

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.1.109:3333/") // substitua pela URL correta do seu backend
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RecipeService recipeService = retrofit.create(RecipeService.class);
                RecipeRequest recipeRequest = new RecipeRequest(periodoRef, titulo, ingredientes, calorias, carboidratos, gordura, proteina, modoDePreparo);



                Call<RecipeResponse> call = recipeService.createRecipe(accessToken, recipeRequest);

                call.enqueue(new Callback<RecipeResponse>() {
                    @Override
                    public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(AdicionarReceita.this, "Receita Adicionada com Sucesso", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                String errorBody = response.errorBody().string();
                                Log.e("Erro ao Adicionar Receita: ", errorBody);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(AdicionarReceita.this, "Erro ao Adicionar Receita", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RecipeResponse> call, Throwable t) {
                        Toast.makeText(AdicionarReceita.this, "Erro de Api: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }));
    }
}

