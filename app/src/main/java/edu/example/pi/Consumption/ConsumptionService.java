package edu.example.pi.Consumption;

import edu.example.pi.Imc.ImcRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ConsumptionService {

    @POST("/consumptions")
    Call<Void> adicionarconsumptions(@Header("Authorization") String token,
                            @Body ConsumptionsRequest consumptionsRequest);

}
