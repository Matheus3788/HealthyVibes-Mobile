package edu.example.pi.Consumption.ConsumoAgua;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;

import edu.example.pi.Consumption.ConsumptionService;
import edu.example.pi.Consumption.ConsumptionsRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateAgua implements OnAguaUpdateListener {


    private final String token;

    private final String user;


    public UpdateAgua(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Login", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        user = sharedPreferences.getString("id", "");

    }

    private ConsumptionService createAguaService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://healthyvibes-rest-api-back-end-production.up.railway.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ConsumptionService.class);

    }

    @Override
    public void onAguaUpdate(String Aguaid, int novaquant) {

        ConsumptionService ConsumptionService = createAguaService();

        String id = Aguaid;

        int quant = novaquant;

        ConsumptionsRequest consumptionsRequest = new ConsumptionsRequest(quant);

        Call<Void> call = ConsumptionService.atualizar(token, id, consumptionsRequest);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.v("Sucess", "Água Atualzada com sucesso!");
                } else {
                    Log.e("Erro1", "Água não encontrada");
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
