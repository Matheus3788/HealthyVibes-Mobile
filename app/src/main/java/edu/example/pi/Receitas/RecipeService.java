package edu.example.pi.Receitas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RecipeService {
    @POST("/recipes")
    Call<RecipeResponse> createRecipe(@Header("Authorization") String token,
                                      @Body RecipeRequest recipe);
//    @GET("/recipes/{id}")
//    Call<RecipeResponse> getRecipeById(@Header("Authorization") String token, @Path("id") String _id);
//
//

    @GET("/recipes/recipesWithUser/{id}")
    Call<List<RecipeResponse>> getRecipeById(@Header("Authorization") String token, @Path("id") String id);

}