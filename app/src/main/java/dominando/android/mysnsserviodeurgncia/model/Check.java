package dominando.android.mysnsserviodeurgncia.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Check {
    private String uuid = null;
    private String hora;
    private String data;
    private boolean estado;

    public Check() {
        this.uuid = setUuid();
        Date date_time = new Date();
        this.hora = setHora(date_time);
        this.data = setData(date_time);
    }

    public String  setUuid(){
        ///vereficar se "Uuid" já esta inicializado
        if(this.uuid == null) {
            //Caso não inicializado, inicializar a "000"
            this.uuid = "000";
        }
        //Começa o "Uuid" com o valor "001" para o primeiro Check
        int newUuid = Integer.parseInt(this.uuid) ;
        return String.valueOf(newUuid);
    }

    private String setHora(Date date_time) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return  dateFormat.format(date_time);
    }

    private String setData(Date date_time) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return  dateFormat.format(date_time);
    }

    public String getUuid() {
        return uuid;
    }
    protected String getHora() {
        return this.hora;
    }

    protected String getData() {
        return this.data;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    protected boolean getEstado() {
        return this.estado;
    }
}
