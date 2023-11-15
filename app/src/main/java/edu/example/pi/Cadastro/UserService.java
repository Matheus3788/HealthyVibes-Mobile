package edu.example.pi.Cadastro;

import edu.example.pi.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @POST("/users")
    Call<UserResponse> createUser(@Body User user);

    @DELETE("/users/{id}")
    Call<Void> deleteUser(
            @Header("Authorization") String accessToken,
            @Path("id") String user
    );
}
