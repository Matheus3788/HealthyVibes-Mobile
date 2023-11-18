package edu.example.pi.Receitas;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RecipeService {
    @POST("/recipes")
    Call<RecipeResponse> createRecipe(@Header("Authorization") String token,
                                      @Body RecipeRequest recipe);
}
