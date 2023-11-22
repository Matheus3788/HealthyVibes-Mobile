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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;

import edu.example.pi.EditProfile;
import edu.example.pi.R;
import edu.example.pi.Receitas.AdicionarReceita;
import edu.example.pi.Receitas.DeleteRecipe;
import edu.example.pi.Receitas.RecipeService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdicionarImc extends AppCompatActivity {

    Button btnadiciona;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imc_grafico_layout);

        btnadiciona = findViewById(R.id.btnaddimc);

        btnadiciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(AdicionarImc.this);

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.imc_modal_adicionar);
                dialog.show();

                Button btnaddimcmodal = dialog.findViewById(R.id.btnaddimcmodal);
                EditText editaddaltura = dialog.findViewById(R.id.editaddaltura);
                EditText editaddpeso = dialog.findViewById(R.id.editaddpeso);

                btnaddimcmodal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Double altura = Double.valueOf(editaddaltura.getText().toString());
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


            }
        });
    }
}
