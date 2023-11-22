package edu.example.pi.Imc;

import edu.example.pi.Cadastro.UserResponse;
import edu.example.pi.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ImcService {
    @POST("/imcs")
    Call<Void> enviarImc(@Header("Authorization") String token, @Body ImcRequest imcRequest);

}
