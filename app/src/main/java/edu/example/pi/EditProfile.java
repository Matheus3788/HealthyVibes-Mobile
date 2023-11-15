package edu.example.pi;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import edu.example.pi.Cadastro.UserService;
import edu.example.pi.login.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditProfile extends AppCompatActivity {

    Button btnatualizar;
    Button btnexcliur;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile_layout);

        btnexcliur = findViewById(R.id.btnexcluir);

        btnexcliur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modalconfirmacao();
            }
        });

    }

    void  modalconfirmacao(){
        final Dialog dialog = new Dialog(EditProfile.this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.profile_modal_confirmar_exclusao);

        Button btnclosefirst = dialog.findViewById(R.id.btnfirstdelete);

        btnclosefirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Quando o botão "Confirmar Exclusão" é clicado, exibe o segundo modal
                confirmaexclusao();
                dialog.dismiss();  // Fecha o modal "confirmar exclusão"
            }
        });
        dialog.show();
    }

    void confirmaexclusao() {
        final Dialog segundoDialog = new Dialog(EditProfile.this);
        segundoDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        segundoDialog.setCancelable(true);
        segundoDialog.setContentView(R.layout.profile_modal_senha_exclusao);


        Button btndelete = segundoDialog.findViewById(R.id.btndeletedefinitivo);

        btndelete.setOnClickListener(e -> {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.109:3333/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            SharedPreferences sharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
            String accessToken = sharedPreferences.getString("token", "");
            String user = sharedPreferences.getString("id", "");

            LoginResponse loginResponse = new LoginResponse(accessToken,user);


            if (loginResponse != null) {
                UserService userService = retrofit.create(UserService.class);

                Call<Void> call = userService.deleteUser(loginResponse.getAccessToken(), loginResponse.getUser());
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(EditProfile.this, "Exclusão bem sucedida", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EditProfile.this, Firstpage.class);
                            startActivity(intent);
                        } else {
                            // Exclusão falhou
                            try {
                                String errorBody = response.errorBody().string();
                                Log.e("Erro de delete", errorBody);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(EditProfile.this, "Erro na exclusão", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(EditProfile.this, "Erro de exclusão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



            } else {
                // LoginResponse é nulo, lidar com isso conforme necessário
                Toast.makeText(EditProfile.this, "Erro: LoginResponse é nulo", Toast.LENGTH_SHORT).show();
            }


        });
        segundoDialog.show();
    }


}
