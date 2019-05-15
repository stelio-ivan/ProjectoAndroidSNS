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

    public Urgencia(){

    }

    public void setGravidade(String gravidade) {
        this.gravidade = gravidade;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setTempoEspera(Date hora) {
        DateFormat dateFormat= new SimpleDateFormat("HH:mm:ss");
       tempoEspera= dateFormat.format(hora);
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTempoEspera() {
        return tempoEspera;
    }

    public String getGravidade() {
        return gravidade;
    }

    public String getTipo() {
        return tipo;
    }




}
