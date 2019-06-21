package dominando.android.mysnsserviodeurgncia.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dominando.android.mysnsserviodeurgncia.model.Hospital;
import dominando.android.mysnsserviodeurgncia.model.Urgencia;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HospitalProvider {
    // Singleton pattern

    private static HospitalProvider _instance;
    private List<Hospital> hospitalData;

    private HospitalProvider(List<Hospital> hospitals) {
        Collections.sort(hospitals);
        this.hospitalData = hospitals;
        initializeData();
    }

    public static HospitalProvider getInstance(List<Hospital> hospitals) {
        if (_instance == null) {
            _instance = new HospitalProvider(hospitals);
        }
        return _instance;
    }


    private void initializeData() {

    }

    public List<Hospital> getHospitais() {
        Collections.sort(hospitalData);
        return hospitalData;
    }

    public Hospital getHospital(String hospitalUuid) {
        for (int i=0; i < hospitalData.size(); i++) {
            if (hospitalData.get(i).getId() != null && String.valueOf(hospitalData.get(i).getId()).equalsIgnoreCase(hospitalUuid)) {
                return hospitalData.get(i);
            }
        }
        return null;
    }

    public List<Hospital> getSugestao(final String gravidade, final String tipoUrgencia){
        Collections.sort(hospitalData);
        List<Hospital> hospitaisSugestao=new ArrayList<>();
        for(int idx=0; idx<3; idx++ ){
            hospitaisSugestao.add(hospitalData.get(idx));
        }
        return  hospitaisSugestao;
    }


}
