package edu.example.pi.Imc;

import android.app.Dialog;
import android.content.Context;
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
import java.util.ArrayList;
import java.util.List;

import edu.example.pi.EditProfile;
import edu.example.pi.R;
import edu.example.pi.Imc.ImcAdapter;
import edu.example.pi.Imc.ImcResponse;
import edu.example.pi.Imc.ImcService;
import edu.example.pi.Receitas.ReceitasAdapter;
import edu.example.pi.Receitas.ReceitasSalvas;
import edu.example.pi.Receitas.RecipeResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdicionarImc extends AppCompatActivity {

    Button btnadiciona;
    RecyclerView recyclerView;
    private List<ImcResponse> imcList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imc_grafico_layout);

        btnadiciona = findViewById(R.id.btnaddimc);
        recyclerView = findViewById(R.id.recyclerViewImc);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnadiciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(AdicionarImc.this);

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.imc_modal_adicionar);
                dialog.show();

                ImageButton btncloseaddimc = dialog.findViewById(R.id.btncloseaddimc);
                Button btnaddimcmodal = dialog.findViewById(R.id.btnaddimcmodal);
                EditText editaddaltura = dialog.findViewById(R.id.editaddaltura);
                EditText editaddpeso = dialog.findViewById(R.id.editaddpeso);

                btnaddimcmodal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int altura = Integer.valueOf(editaddaltura.getText().toString());
                        Double peso = Double.valueOf(editaddpeso.getText().toString());

                        Log.d("peso", String.valueOf(peso));
                        Log.d("Altura", String.valueOf(altura));

                        SharedPreferences sharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
                        String accessToken = sharedPreferences.getString("token", "");


                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("https://healthyvibes-rest-api-back-end-production.up.railway.app/") // substitua pela URL correta do seu backend
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        ImcService imcService = retrofit.create(ImcService.class);
                        ImcRequest imcRequest = new ImcRequest(peso, altura);

                        Call<Void> call = imcService.enviarImc(accessToken, imcRequest);
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(AdicionarImc.this, "IMC adicionado com sucesso!", Toast.LENGTH_SHORT).show();

                                } else {
                                    try {
                                        // Tente obter a mensagem de erro do corpo da resposta
                                        String errorBody = response.errorBody().string();
                                        Log.e("Erro", "Corpo da resposta: " + errorBody);
                                        Toast.makeText(AdicionarImc.this, "Erro ao tentar adicionar: " + errorBody, Toast.LENGTH_SHORT).show();
                                    } catch (IOException e) {
                                        // Se houver um problema ao obter o corpo da resposta, trate o erro aqui
                                        e.printStackTrace();
                                        Toast.makeText(AdicionarImc.this, "Erro ao tentar adicionar", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(AdicionarImc.this, "Falha na Requisição", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });


                btncloseaddimc.setOnClickListener(new View.OnClickListener() {
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
        ImcService imcService = retrofit.create(ImcService.class);

        // Faça a chamada para obter a receita específica do usuário
        Call<List<ImcResponse>> call = imcService.getImc(token);

        call.enqueue(new Callback<List<ImcResponse>>() {
            @Override
            public void onResponse(Call<List<ImcResponse>> call, Response<List<ImcResponse>> response) {
                if (response.isSuccessful()) {
                    imcList.addAll(response.body());
                    if (!imcList.isEmpty()) {
                        // Configurar o RecyclerView com o adapter e lista
                        ImcAdapter adapter = new ImcAdapter(imcList);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(AdicionarImc.this, "Lista de IMCS vazia", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("API_RESPONSE", "Error response: " + errorBody);
                        Toast.makeText(AdicionarImc.this, "Falha ao obter IMC: " + errorBody, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ImcResponse>> call, Throwable t) {
                if (!isFinishing()) {
                    Toast.makeText(AdicionarImc.this, "Erro de conexão com a API: " + t.getCause(), Toast.LENGTH_SHORT).show();
                }            }
        });
//show        Imc i1 = new Imc();
//        i1.peso = 81.5;
//        i1.altura = 170;
//        i1.valor = (i1.altura * i1.altura) / i1.peso;
//
//
//        Imc i2 = new Imc();
//        i2.peso = 31.5;
//        i2.altura = 190;
//        i2.valor = (i2.altura * i2.altura) / i2.peso;
//
//        imcs.add(i1);
//        imcs.add(i2);
//
//
//
//        RecyclerView recyclerView = findViewById(R.id.recyclerViewImc);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new ImcAdapter(this, imcs);
//        recyclerView.setAdapter(adapter);
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
