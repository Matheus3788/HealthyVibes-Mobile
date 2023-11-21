package edu.example.pi.Receitas;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import edu.example.pi.Cadastro.UserService;
import edu.example.pi.R;
import edu.example.pi.login.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditarReceitas extends AppCompatActivity {
    EditText editnomeedit;
    EditText editdificuldadeedit;
    EditText editporcoesedit;
    EditText editperiodoedit;
    EditText editpriningredienteedit, editsegingredienteedit, editteringredienteedit, editquartingredienteedit;
    EditText editcaloriasedit, editcarboidratosedit, editproteinaedit, editgorduraedit;
    EditText editmododepreparoedit;

    Button btnatualiza;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receitas_editar);


        editnomeedit = findViewById(R.id.edittextituloedit);
        editdificuldadeedit = findViewById(R.id.edittextdificuldadeedit);
        editporcoesedit = findViewById(R.id.edittextporcoesedit);
        editperiodoedit = findViewById(R.id.edittextperiodoedit);
        editpriningredienteedit = findViewById(R.id.edittextingredpriedit);
        editsegingredienteedit = findViewById(R.id.edittextingredsegedit);
        editteringredienteedit = findViewById(R.id.edittextingredteredit);
        editquartingredienteedit = findViewById(R.id.edittextingredquaredit);
        editcaloriasedit = findViewById(R.id.edittextcaloriasedit);
        editcarboidratosedit = findViewById(R.id.edittextcarboedit);
        editproteinaedit = findViewById(R.id.edittextproteinaedit);
        editgorduraedit = findViewById(R.id.edittextgorduraedit);
        editmododepreparoedit = findViewById(R.id.edittextpreparoedit);
        btnatualiza = findViewById(R.id.btnatualizarecipe);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Obtenha os valores dos extras
            String titulo = extras.getString("titulo", "");
            String PeriodoRef = extras.getString("PeriodoRef", "");
            String ingredientesJson = extras.getString("ingredientes", "[]");
            int calorias = extras.getInt("calorias", 0);
            int carboidratos = extras.getInt("carboidratos", 0);
            int proteina = extras.getInt("proteina", 0);
            int gordura = extras.getInt("gordura", 0);
            String mododepreparo = extras.getString("mododepreparo", "");

            editnomeedit.setText(titulo);
            editperiodoedit.setText(PeriodoRef);

            Type listType = new TypeToken<List<Ingredient>>(){}.getType();
            List<Ingredient> ingredientes = new Gson().fromJson(ingredientesJson, listType);

            editpriningredienteedit.setText(ingredientes.get(0).getNome());
            editsegingredienteedit.setText(ingredientes.get(1).getNome());
            editteringredienteedit.setText(ingredientes.get(2).getNome());
            editquartingredienteedit.setText(ingredientes.get(3).getNome());

            editcaloriasedit.setText(String.valueOf(calorias));
            editcarboidratosedit.setText(String.valueOf(carboidratos));
            editproteinaedit.setText(String.valueOf(proteina));
            editgorduraedit.setText(String.valueOf(gordura));

            editmododepreparoedit.setText(mododepreparo);
        }


        btnatualiza.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String novotitulo = editnomeedit.getText().toString();
                String periodoRef = editperiodoedit.getText().toString();

                String nomeIngrediente1 = editpriningredienteedit.getText().toString();
                String nomeIngrediente2 = editsegingredienteedit.getText().toString();
                String nomeIngrediente3 = editteringredienteedit.getText().toString();
                String nomeIngrediente4 = editquartingredienteedit.getText().toString();

                int calorias = Integer.parseInt(editcaloriasedit.getText().toString());
                int carboidratos = Integer.parseInt(editcarboidratosedit.getText().toString());
                int proteina = Integer.parseInt(editproteinaedit.getText().toString());
                int gordura = Integer.parseInt(editgorduraedit.getText().toString());

                String modoDePreparo = editmododepreparoedit.getText().toString();


                Ingredient ingrediente1 = new Ingredient(nomeIngrediente1);
                Ingredient ingrediente2 = new Ingredient(nomeIngrediente2);
                Ingredient ingrediente3 = new Ingredient(nomeIngrediente3);
                Ingredient ingrediente4 = new Ingredient(nomeIngrediente4);

                List<Ingredient> ingredientes = new ArrayList<>();
                ingredientes.add(ingrediente1);
                ingredientes.add(ingrediente2);
                ingredientes.add(ingrediente3);
                ingredientes.add(ingrediente4);


                String id = extras.getString("idreceita", "");

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://healthyvibes-rest-api-back-end-production.up.railway.app/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                SharedPreferences sharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
                String accessToken = sharedPreferences.getString("token", "");
                String User = sharedPreferences.getString("id", "");


                RecipeService recipeService = retrofit.create(RecipeService.class);
                RecipeRequest recipeRequest = new RecipeRequest(periodoRef, novotitulo, ingredientes, calorias, carboidratos, gordura, proteina, modoDePreparo);



                Call<Void> call = recipeService.atualizarReceita(accessToken, id, recipeRequest);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Log.v("Sucess", "Receita excluída com sucesso!");
                            Log.d("kkp", novotitulo);

                        } else {
                            Log.e("Erro1", "Receita não encontrada");
                            try {
                                Log.e("Erro", "Corpo da resposta: " + response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        // Ocorreu uma falha na requisição
                        // Adicione lógica para lidar com falhas de comunicação
                    }
                });




            }
            }));

    }
}
