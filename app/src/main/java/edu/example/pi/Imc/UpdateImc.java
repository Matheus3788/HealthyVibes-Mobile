package edu.example.pi.Imc;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;

import edu.example.pi.Receitas.RecipeRequest;
import edu.example.pi.Receitas.RecipeService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateImc implements OnImcUpdateListener{

    private final String token;

    private final String user;


    public UpdateImc(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Login", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        user = sharedPreferences.getString("id", "");

    }

    private ImcService createImcService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://healthyvibes-rest-api-back-end-production.up.railway.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ImcService.class);
    }

    @Override
    public void onImcUpdate(String Imcid, int novaaltura,  double novopeso) {
        ImcService ImcService = createImcService();

        String id = Imcid;

        double peso = novopeso;


        ImcRequest imcRequest = new ImcRequest(peso, novaaltura);


        Call<Void> call = ImcService.atualizarImc(token, id, imcRequest);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.v("Sucess", "Imc excluída com sucesso!");
                } else {
                    Log.e("Erro1", "Imc não encontrada");
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
}
