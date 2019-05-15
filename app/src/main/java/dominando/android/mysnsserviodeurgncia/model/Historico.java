package dominando.android.mysnsserviodeurgncia.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Historico {
    private String uuid;
    private LocalTime hora;
    private LocalDate data;
    private CheckIn checkIn;
    private CheckOut checkOut;

    public Historico(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void CheckIn() {
        this.checkIn = new CheckIn(true);
    }

    public void CheckOut() {
        this.checkOut = new CheckOut(true);
    }

    public String getHoraCheckIn(){
        return checkIn.getHora();
    }

    public String getDataCheckIn(){
        return checkIn.getData();
    }

    public String getHoraCheckOut(){
        return checkOut.getHora();
    }

    public String getDataCheckOut(){
        return checkOut.getData();
    }
}
