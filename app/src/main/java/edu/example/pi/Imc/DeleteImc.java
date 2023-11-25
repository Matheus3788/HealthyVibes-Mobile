package edu.example.pi.Imc;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;

import edu.example.pi.Imc.ImcService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeleteImc implements OnImcDeleteListener {

    private final String token;

    private final String user;


    public DeleteImc(Context context) {
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

    // Implementação do método da interface
    @Override
    public void onImcDelete(String Imcid) {
        ImcService ImcService = createImcService();

        String id = Imcid;


        Call<Void> call = ImcService.deleteImc(token, id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.v("Erro1", "Receita excluída com sucesso!");
                } else {
                    Log.e("Erro1", "Falha ao excluir a receita. Código de resposta: " + response.code());

                    try {
                        Log.e("Erro", "Corpo da resposta: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Erro2", "Erro na conexão com o servidor!", t);
            }
        });
    }
}

