package dominando.android.mysnsserviodeurgncia.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Historico {
    private String uuid;
    private String nomeHospital;
    private CheckIn checkIn;
    private CheckOut checkOut;


    public Historico(String uuid, String nomeHospital) {
        this.uuid = uuid;
        this.nomeHospital = nomeHospital;
    }

    public String getUuid() {
        return uuid;
    }

    public String getNomeHospital() {
        return this.nomeHospital;
    }




    public String getDataCheckIn(){
        return checkIn.getData();
    }



    public String getDataCheckOut(){
        return checkOut.getData();
    }

    public void setCheckIn(CheckIn checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(CheckOut checkOut) {
        this.checkOut = checkOut;
    }
}
