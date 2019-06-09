package dominando.android.mysnsserviodeurgncia.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;

public class Check  {
    private String uuid = null;
    private String hora;
    private String data;
    private int  estado;

    public Check() {
        generateAndSetUuid();
        Date date_time = new Date();
        this.hora = setHora(date_time);
        this.data = setData(date_time);
    }

    public void setUuid(String uuid){
        this.uuid=uuid;
    }

    public void generateAndSetUuid() {
        setUuid(UUID.randomUUID().toString());
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






}
