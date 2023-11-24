package edu.example.pi.Imc;

import java.util.List;

import edu.example.pi.Cadastro.UserResponse;
import edu.example.pi.Receitas.RecipeResponse;
import edu.example.pi.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ImcService {
    @POST("/imcs")
    Call<Void> enviarImc(@Header("Authorization") String token, @Body ImcRequest imcRequest);

    @GET("/imcs/{id}")
    Call<List<ImcResponse>> getImcById(@Header("Authorization") String token, @Path("id") String id);

}
