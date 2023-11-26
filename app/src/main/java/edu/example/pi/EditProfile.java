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
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.IOException;

import edu.example.pi.Cadastro.UserService;
import edu.example.pi.login.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditProfile extends AppCompatActivity {
    EditText editnome;
    EditText editemail;
    EditText editsenha;
    EditText editconfirmarsenha;

    Button btnatualizar;
    Button btnexcliur;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile_layout);

        editnome = findViewById(R.id.editnomeedit);
        editemail = findViewById(R.id.editemailedit);
        editsenha = findViewById(R.id.editsenhaedit);
        editconfirmarsenha = findViewById(R.id.editconfimasenhaedit);
        btnatualizar = findViewById(R.id.btnatualizar);


        btnatualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editnome.getText().toString();
                String email = editemail.getText().toString();
                String password = editsenha.getText().toString();
                String confirmPassword = editconfirmarsenha.getText().toString();

                // Verifique se algum campo está vazio
                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(EditProfile.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                    if (username.isEmpty()) {
                        editnome.setBackground(ContextCompat.getDrawable(EditProfile.this, R.drawable.edittext_border));
                    }
                    if (email.isEmpty()) {
                        editemail.setBackground(ContextCompat.getDrawable(EditProfile.this, R.drawable.edittext_border));
                    }
                    if (password.isEmpty()) {
                        editsenha.setBackground(ContextCompat.getDrawable(EditProfile.this, R.drawable.edittext_border));
                    }
                    if (confirmPassword.isEmpty()) {
                        editconfirmarsenha.setBackground(ContextCompat.getDrawable(EditProfile.this, R.drawable.edittext_border));
                    }
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    // Senhas diferentes, exiba uma mensagem de erro
                    Toast.makeText(EditProfile.this, "As senhas não coincidem", Toast.LENGTH_SHORT).show();
                    editconfirmarsenha.setBackground(ContextCompat.getDrawable(EditProfile.this, R.drawable.edittext_border));
                    return; // Saia do método para evitar o envio da solicitação
                }

                User updatedUser = new User(username, email, password);

                atualizarPerfil(updatedUser);
            }
        });


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
                    .baseUrl("https://healthyvibes-rest-api-back-end-production.up.railway.app/")
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


    private void atualizarPerfil(User user) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://healthyvibes-rest-api-back-end-production.up.railway.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SharedPreferences sharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("token", "");
        String User = sharedPreferences.getString("id", "");

        LoginResponse loginResponse = new LoginResponse(accessToken,User);

        UserService userService = retrofit.create(UserService.class);

        // Certifique-se de passar o ID do usuário correto (pode ser obtido do LoginResponse)
        Call<Void> call = userService.atualizarPerfil(loginResponse.getAccessToken(), loginResponse.getUser(), user);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    String username = editnome.getText().toString();

                    Toast.makeText(EditProfile.this, "Perfil atualizado com sucesso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditProfile.this, Home.class);
                    intent.putExtra("userName", username);
                    startActivity(intent);
                    finish();
                } else {
                    // Lidar com falha na atualização
                    Toast.makeText(EditProfile.this, "Falha ao atualizar perfil", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Lidar com falha na comunicação com o servidor
                Toast.makeText(EditProfile.this, "Falha na comunicação com o servidor", Toast.LENGTH_SHORT).show();
            }
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
