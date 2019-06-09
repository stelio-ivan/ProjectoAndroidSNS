package dominando.android.mysnsserviodeurgncia.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmObject;

public class CheckIn extends RealmObject{

    private String uuid = null;

    private String data;
    private String nomeHospital;

    public CheckIn(String nomeHospital, String uuid) {
        //generateAndSetUuid();
        this.uuid=uuid;
        this.nomeHospital=nomeHospital;
        Date date_time = new Date(System.currentTimeMillis());
        this.data = date_time.toString();

    }

    public CheckIn(){}



    public void generateAndSetUuid() {

        uuid=UUID.randomUUID().toString();

    }


    public String getNomeHospital() {
        return nomeHospital;
    }



    public String getUuid() {
        return uuid;
    }


    protected String getData() {
        return this.data;
    }

}
