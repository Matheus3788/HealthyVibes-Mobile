package edu.example.pi.Consumption.ConsumoAgua;

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

    import java.time.LocalDate;
    import java.time.format.DateTimeFormatter;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.example.pi.Consumption.ConsumptionService;
import edu.example.pi.Consumption.ConsumptionsRequest;
import edu.example.pi.Consumption.ConsumptionsResponse;
import edu.example.pi.Imc.AdicionarImc;
import edu.example.pi.Imc.ImcAdapter;
import edu.example.pi.Imc.ImcRequest;
import edu.example.pi.Imc.ImcResponse;
import edu.example.pi.Imc.ImcService;
import edu.example.pi.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdicionarAgua extends AppCompatActivity {

    Button btnadd;

    RecyclerView recyclerView;
    private List<ConsumptionsResponse> aguaList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumo_agua_layout);


        btnadd = findViewById(R.id.btnaddagua);
        recyclerView = findViewById(R.id.recyclerViewAgua);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(AdicionarAgua.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.agua_modal_adicionar);
                dialog.show();

                EditText edittextagua = dialog.findViewById(R.id.editaddquant);
                Button btnaddagua = dialog.findViewById(R.id.btnaddagua);

                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                // Formata e imprime a data atual
                String formattedDate = currentDate.format(formatter);


                btnaddagua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int quantidade = Integer.parseInt(edittextagua.getText().toString());
                        Log.d("Agua", String.valueOf(quantidade));

                        ImageButton btncloseaddagua = dialog.findViewById(R.id.btncloseaddagua);

                        SharedPreferences sharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
                        String accessToken = sharedPreferences.getString("token", "");

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("https://healthyvibes-rest-api-back-end-production.up.railway.app/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        String tipoConsumo = "Água";

                        ConsumptionService consumptionService = retrofit.create(ConsumptionService.class);
                        ConsumptionsRequest consumptionsRequest = new ConsumptionsRequest(quantidade, tipoConsumo, formattedDate);

                        Call<Void> call = consumptionService.adicionarconsumptions(accessToken, consumptionsRequest);
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(AdicionarAgua.this, "Consumo de Água adicionado com sucesso!", Toast.LENGTH_SHORT).show();
                                    Intent recarregar = new Intent(AdicionarAgua.this, AdicionarAgua.class);
                                    startActivity(recarregar);
                                } else {
                                    try {
                                        // Tente obter a mensagem de erro do corpo da resposta
                                        String errorBody = response.errorBody().string();
                                        Log.e("Erro", "Corpo da resposta: " + errorBody);
                                        Toast.makeText(AdicionarAgua.this, "Erro ao tentar adicionar: " + errorBody, Toast.LENGTH_SHORT).show();
                                    } catch (IOException e) {
                                        // Se houver um problema ao obter o corpo da resposta, trate o erro aqui
                                        e.printStackTrace();
                                        Toast.makeText(AdicionarAgua.this, "Erro ao tentar adicionar", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(AdicionarAgua.this, "Falha na Requisição", Toast.LENGTH_SHORT).show();
                            }
                        });

                        btncloseaddagua.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
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
        Call<List<ConsumptionsResponse>> call = consumptionService.getAgua(token);


        call.enqueue(new Callback<List<ConsumptionsResponse>>() {
            @Override
            public void onResponse(Call<List<ConsumptionsResponse>> call, Response<List<ConsumptionsResponse>> response) {
                if (response.isSuccessful()) {
                    aguaList.addAll(response.body());
                    if (!aguaList.isEmpty()) {
                        // Configurar o RecyclerView com o adapter e lista
                        AguaAdapter adapter = new AguaAdapter(aguaList);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(AdicionarAgua.this, "Lista de Águas vazia", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("API_RESPONSE", "Error response: " + errorBody);
                        Toast.makeText(AdicionarAgua.this, "Falha ao obter Água: " + errorBody, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ConsumptionsResponse>> call, Throwable t) {
                if (!isFinishing()) {
                    Toast.makeText(AdicionarAgua.this, "Erro de conexão com a API: " + t.getCause(), Toast.LENGTH_SHORT).show();
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
