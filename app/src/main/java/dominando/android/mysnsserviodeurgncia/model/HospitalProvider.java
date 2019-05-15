package dominando.android.mysnsserviodeurgncia.model;

import java.util.ArrayList;
import java.util.List;

public class HospitalProvider {
    // Singleton pattern

    private static HospitalProvider _instance;

    private HospitalProvider() {
        initializeData();
    }


    public static HospitalProvider getInstance() {
        if (_instance == null) {
            _instance = new HospitalProvider();
        }
        return _instance;
    }


    private List<Hospital> hospitalData = new ArrayList<>();

    private void initializeData() {
        Hospital h1 = new Hospital("001", "Santa Maria", "Privado");
        Hospital h2 = new Hospital("002", "São Jose", "Publico");
        Hospital h3 = new Hospital("001", "Santa Maria", "Privado");
        Hospital h4 = new Hospital("002", "São Jose", "Publico");
        Hospital h5 = new Hospital("001", "Santa Maria", "Privado");
        Hospital h6 = new Hospital("002", "São Jose", "Publico");
        Hospital h7 = new Hospital("001", "Santa Maria", "Privado");
        Hospital h8 = new Hospital("002", "São Jose", "Publico");

        hospitalData.add(h1);
        hospitalData.add(h2);
        hospitalData.add(h3);
        hospitalData.add(h4);
        hospitalData.add(h5);
        hospitalData.add(h6);
        hospitalData.add(h7);
        hospitalData.add(h8);
    }

    public List<Hospital> getHospitais() {
        return hospitalData;
    }

    public Hospital getHospital(String hospitalUuid) {
        for (int i=0; i < hospitalData.size(); i++) {
            if (hospitalData.get(i).getUuid() != null && hospitalData.get(i).getUuid().equalsIgnoreCase(hospitalUuid)) {
                return hospitalData.get(i);
            }
        }
        return null;
    }

//    public List<Hospital> getSugestao(final String gravidade, final String tipoUrgencia){
//        hospitalData.stream().filter(hospital->hospital.getUrgencia().getGravidade().equals(gravidade)
//                && hospital.getUrgencia().getTipo().equals(tipoUrgencia))
//                .sorted((hospitalA, hospitalB)->hospitalB.getDistanciaKm()-hospitalA.getDistanciaKm());
//
//    }


}
