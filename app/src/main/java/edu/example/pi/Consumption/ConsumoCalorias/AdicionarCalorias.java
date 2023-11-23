package edu.example.pi.Consumption.ConsumoCalorias;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import edu.example.pi.Consumption.ConsumoAgua.AdicionarAgua;
import edu.example.pi.Consumption.ConsumptionService;
import edu.example.pi.Consumption.ConsumptionsRequest;
import edu.example.pi.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdicionarCalorias extends AppCompatActivity {

    Button addcal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumo_caloria_layout);

        addcal = findViewById(R.id.btnadd);

        addcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(AdicionarCalorias.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.calorias_modal_adicionar);
                dialog.show();


                EditText edittextcalorias = dialog.findViewById(R.id.editaddquantcal);
                Button btnaddcal = dialog.findViewById(R.id.btnaddcal);

                btnaddcal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int quantidade = Integer.parseInt(edittextcalorias.getText().toString());
                        Log.d("Calorias", String.valueOf(quantidade));

                        String tipoConsumo = "Calorias";

                        SharedPreferences sharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
                        String accessToken = sharedPreferences.getString("token", "");

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("https://healthyvibes-rest-api-back-end-production.up.railway.app/") // substitua pela URL correta do seu backend
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        ConsumptionService consumptionService = retrofit.create(ConsumptionService.class);
                        ConsumptionsRequest consumptionsRequest = new ConsumptionsRequest(quantidade, tipoConsumo);

                        Call<Void> call = consumptionService.adicionarconsumptions(accessToken, consumptionsRequest);
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(AdicionarCalorias.this, "Consumo de Calorias adicionado com sucesso!", Toast.LENGTH_SHORT).show();

                                } else {
                                    try {
                                        // Tente obter a mensagem de erro do corpo da resposta
                                        String errorBody = response.errorBody().string();
                                        Log.e("Erro", "Corpo da resposta: " + errorBody);
                                        Toast.makeText(AdicionarCalorias.this, "Erro ao tentar adicionar: " + errorBody, Toast.LENGTH_SHORT).show();
                                    } catch (IOException e) {
                                        // Se houver um problema ao obter o corpo da resposta, trate o erro aqui
                                        e.printStackTrace();
                                        Toast.makeText(AdicionarCalorias.this, "Erro ao tentar adicionar", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(AdicionarCalorias.this, "Falha na Requisição", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            }
        });

    }
}
