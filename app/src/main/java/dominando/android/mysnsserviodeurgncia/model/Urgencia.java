package dominando.android.mysnsserviodeurgncia.model;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Date;

public class Urgencia {

    private String tipo;
    private String gravidade;
    private String tempoEspera;

    public Urgencia(String tipo, String gravidade, String tempoEspera){
        this.tipo = tipo;
        this.gravidade = gravidade;
        this.tempoEspera = tempoEspera;
    }

    public void setGravidade(String gravidade) {
        this.gravidade = gravidade;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTempoEspera(String tempoEspera) {
        this.tempoEspera = tempoEspera;
    }
    
    public String getTempoEspera() {
        return this.tempoEspera;
    }

    public String getGravidade() {
        return gravidade;
    }

    public String getTipo() {
        return tipo;
    }




}
