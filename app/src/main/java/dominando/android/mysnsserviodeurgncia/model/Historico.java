package dominando.android.mysnsserviodeurgncia.model;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.Realm;


public class Historico extends RealmObject {
    private String uuid;
    private String nomeHospital;


    private Date checkInDate;
    private Date checkOutDate;

    public Historico( String nomeHospital) {
        this.uuid = UUID.randomUUID().toString();
        this.nomeHospital = nomeHospital;
    }

    public Historico(){}

    public String getUuid() {
        return uuid;
    }

    public String getNomeHospital() {
        return this.nomeHospital;
    }





    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setNomeHospital(String nomeHospital) {
        this.nomeHospital = nomeHospital;
    }

    public void setCheckInDate() {
        this.checkInDate = new Date(System.currentTimeMillis());
    }

    public void setCheckOutDate() {
        this.checkOutDate = new Date(System.currentTimeMillis());
    }
}
