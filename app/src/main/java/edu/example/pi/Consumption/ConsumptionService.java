package edu.example.pi.Consumption;

import java.util.List;

import edu.example.pi.Imc.ImcRequest;
import edu.example.pi.Imc.ImcResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ConsumptionService {

    @POST("/consumptions")
    Call<Void> adicionarconsumptions(@Header("Authorization") String token,
                            @Body ConsumptionsRequest consumptionsRequest);

    @GET("/consumptions/myConsumptions/water")
    Call<List<ConsumptionsResponse>> getAgua(@Header("Authorization") String token);

}
