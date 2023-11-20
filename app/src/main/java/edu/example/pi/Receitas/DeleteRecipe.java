package edu.example.pi.Receitas;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import edu.example.pi.EditProfile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeleteRecipe implements OnRecipeDeleteListener {

    private final String token;

    private final String user;


    public DeleteRecipe(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Login", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        user = sharedPreferences.getString("id", "");

    }


    // Método para criar uma instância do Retrofit
    private RecipeService createRecipeService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://healthyvibes-rest-api-back-end-production.up.railway.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(RecipeService.class);
    }

    // Implementação do método da interface
    @Override
    public void onRecipeDelete(String recipeId) {
        RecipeService recipeService = createRecipeService();

        String id = recipeId;

        Log.d("Id da receita", id);
        Log.d("Token do Usuário", token);

        Call<Void> call = recipeService.deleteRecipe(token, id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.v("Erro1", "Receita excluída com sucesso!");

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
                Log.e("Erro2", "Erro na conecxão com o servidor!");

            }
        });
    }
}
