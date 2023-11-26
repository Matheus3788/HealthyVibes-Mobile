package edu.example.pi.Consumption.ConsumoAgua;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;

import edu.example.pi.Consumption.ConsumptionService;
import edu.example.pi.Imc.ImcService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeleteAgua implements OnAguaDeleteListener{

    private final String token;

    private final String user;



    public DeleteAgua(Context context) {
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
    public void onAguaDelete(String Aguaid) {
        ConsumptionService consumptionService = createAguaService();

        String id = Aguaid;

        Log.d("uinha", Aguaid);
        Log.d("N aguinha", id);
        Log.d("Token", token);



        Call<Void> call = consumptionService.deleteAgua(token, id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.v("Erro1", "Agua excluída com sucesso!");
                } else {
                    Log.e("Erro1", "Falha ao excluir a Água. Código de resposta: " + response.code());

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
