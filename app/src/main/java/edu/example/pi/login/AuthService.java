package edu.example.pi.login;

import edu.example.pi.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("/users/login")
    Call<LoginResponse> loginUser(@Body User user);
}
