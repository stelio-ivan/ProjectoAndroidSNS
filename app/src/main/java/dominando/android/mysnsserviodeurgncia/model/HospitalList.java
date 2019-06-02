package dominando.android.mysnsserviodeurgncia.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class HospitalList {
    @SerializedName("data")
    private List<Hospital> hospitais;

    public HospitalList(){
        this.hospitais = new ArrayList<>();
    }
    public List<Hospital> getHospitais() {
        return hospitais;
    }
}
