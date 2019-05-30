package dominando.android.mysnsserviodeurgncia.services;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HospitalService {

    String BASE_URL = "http://tempos.min-saude.pt/api.php/";

    @GET("institution")
    Call<Result> getHospitais();
}
