package edu.example.pi.Consumption.ConsumoCalorias;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import edu.example.pi.Consumption.ConsumptionService;
import edu.example.pi.Consumption.ConsumptionsRequest;
import edu.example.pi.Consumption.ConsumptionsResponse;
import edu.example.pi.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdicionarCalorias extends AppCompatActivity {

    Button addcal;

    RecyclerView recyclerView;
    private List<ConsumptionsResponse> calList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumo_caloria_layout);

        addcal = findViewById(R.id.btnadd);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
                ImageButton btncloseaddcal = dialog.findViewById(R.id.btncloseaddcal);

                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                // Formata e imprime a data atual
                String formattedDate = currentDate.format(formatter);

                btnaddcal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String caloriasText = edittextcalorias.getText().toString().trim();

                        if (caloriasText.isEmpty()) {
                            Toast.makeText(AdicionarCalorias.this, "Digite a quantidade de calorias", Toast.LENGTH_SHORT).show();
                            return; // Exit the method if the field is empty
                        }

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
                        ConsumptionsRequest consumptionsRequest = new ConsumptionsRequest(quantidade, tipoConsumo, formattedDate);

                        Call<Void> call = consumptionService.adicionarconsumptions(accessToken, consumptionsRequest);
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(AdicionarCalorias.this, "Consumo de Calorias adicionado com sucesso!", Toast.LENGTH_SHORT).show();

                                    Intent recarregar = new Intent(AdicionarCalorias.this, AdicionarCalorias.class);
                                    startActivity(recarregar);

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

                btncloseaddcal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });

        //Começar a puxar as informações
        SharedPreferences sharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://healthyvibes-rest-api-back-end-production.up.railway.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crie uma instância do seu serviço
        ConsumptionService consumptionService = retrofit.create(ConsumptionService.class);

        // Faça a chamada para obter a receita específica do usuário
        Call<List<ConsumptionsResponse>> call = consumptionService.getCalorias(token);


        call.enqueue(new Callback<List<ConsumptionsResponse>>() {
            @Override
            public void onResponse(Call<List<ConsumptionsResponse>> call, Response<List<ConsumptionsResponse>> response) {
                if (response.isSuccessful()) {
                    calList.addAll(response.body());
                    if (!calList.isEmpty()) {
                        // Configurar o RecyclerView com o adapter e lista
                        CaloriaAdapter adapter = new CaloriaAdapter(calList);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(AdicionarCalorias.this, "Lista de Calorias vazia", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("API_RESPONSE", "Error response: " + errorBody);
                        Toast.makeText(AdicionarCalorias.this, "Falha ao obter Calorias: " + errorBody, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ConsumptionsResponse>> call, Throwable t) {
                if (!isFinishing()) {
                    Toast.makeText(AdicionarCalorias.this, "Erro de conexão com a API: " + t.getCause(), Toast.LENGTH_SHORT).show();
                }            }
        });
    }
    @Override
    protected  void onStart() {
        super.onStart();
        Log.d("ActivityPrincipal", "on Start executado");
    }

    @Override
    protected void onResume( ){
        super.onResume();
        Log.d("ActivityPrincipal", "on Resume executado");

    }

    @Override
    protected void onPause( ){
        super.onPause();
        Log.d("ActivityPrincipal", "on Pause executado");

    }

    @Override
    protected void onStop( ){
        super.onStop();
        Log.d("ActivityPrincipal", "on Destroy executado");
    }

    @Override
    protected void onDestroy( ){
        super.onDestroy();
        Log.d("ActivityPrincipal", "on Destroy  executado");
    }

    @Override
    protected void onRestart( ){
        super.onRestart();
        Log.d("ActivityPrincipal", "on Restart  executado");
    }
}
