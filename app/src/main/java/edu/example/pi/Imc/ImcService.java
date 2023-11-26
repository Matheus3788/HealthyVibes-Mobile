package edu.example.pi.Imc;

import java.util.List;

import edu.example.pi.Cadastro.UserResponse;
import edu.example.pi.Receitas.RecipeRequest;
import edu.example.pi.Receitas.RecipeResponse;
import edu.example.pi.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ImcService {
    @POST("/imcs")
    Call<Void> enviarImc(@Header("Authorization") String token, @Body ImcRequest imcRequest);

    @GET("/imcs/myImcs")
    Call<List<ImcResponse>> getImc(@Header("Authorization") String token);

    @DELETE("/imcs/{id}")
    Call<Void> deleteImc(@Header("Authorization") String token, @Path("id") String _id);

    @PATCH("/imcs/{id}")
    Call<Void> atualizarImc(@Header("Authorization") String token, @Path("id") String _id, @Body ImcRequest imcRequest
    );


}
