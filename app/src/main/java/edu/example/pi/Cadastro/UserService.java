package edu.example.pi.Cadastro;

import edu.example.pi.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("/users")
    Call<UserResponse> createUser(@Body User user);
}
