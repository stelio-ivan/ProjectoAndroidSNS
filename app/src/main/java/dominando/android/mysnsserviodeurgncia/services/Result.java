package dominando.android.mysnsserviodeurgncia.services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import dominando.android.mysnsserviodeurgncia.model.Hospital;

public class Result {
    @SerializedName("Result")
    @Expose
    public List<Hospital> hospitals;
}