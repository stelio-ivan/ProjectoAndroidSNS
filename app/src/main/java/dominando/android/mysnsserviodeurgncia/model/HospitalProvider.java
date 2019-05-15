package dominando.android.mysnsserviodeurgncia.model;

import java.util.ArrayList;
import java.util.Collections;
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
        h1.setDistanciaKm(21);
        h1.setUrgencia(new Urgencia("Geral", "Grave", "00:10"));
        h1.setUrgencia(new Urgencia("Geral", "Não grave", "00:02"));
        //--
        h1.setUrgencia(new Urgencia("Pediatria,", "Grave", "00:15"));
        h1.setUrgencia(new Urgencia("Pediatria,", "Não grave", "00:04"));
        //--
        h1.setUrgencia(new Urgencia("Obstetrícia),", "Grave", "00:08"));
        h1.setUrgencia(new Urgencia("Obstetrícia),", "Não grave", "00:06"));
        Hospital h2 = new Hospital("002", "São Jose", "Publico");
        h2.setDistanciaKm(32);
        h2.setUrgencia(new Urgencia("Geral", "Grave", "00:20"));
        h2.setUrgencia(new Urgencia("Geral", "Não grave", "00:01"));
        //--
        h2.setUrgencia(new Urgencia("Pediatria,", "Grave", "00:10"));
        h2.setUrgencia(new Urgencia("Pediatria,", "Não grave", "00:05"));
        //--
        h2.setUrgencia(new Urgencia("Obstetrícia),", "Grave", "00:07"));
        h2.setUrgencia(new Urgencia("Obstetrícia),", "Não grave", "00:04"));
        Hospital h3 = new Hospital("001", "Santa Maria", "Privado");
        h3.setDistanciaKm(35);
        h3.setUrgencia(new Urgencia("Geral", "Grave", "00:20"));
        h3.setUrgencia(new Urgencia("Geral", "Não grave", "00:01"));
        //--
        h3.setUrgencia(new Urgencia("Pediatria,", "Grave", "00:10"));
        h3.setUrgencia(new Urgencia("Pediatria,", "Não grave", "00:05"));
        //--
        h3.setUrgencia(new Urgencia("Obstetrícia),", "Grave", "00:07"));
        h3.setUrgencia(new Urgencia("Obstetrícia),", "Não grave", "00:04"));
        Hospital h4 = new Hospital("002", "São Jose", "Publico");
        h4.setDistanciaKm(51);
        Hospital h5 = new Hospital("001", "Santa Maria", "Privado");
        h5.setDistanciaKm(39);
        Hospital h6 = new Hospital("002", "São Jose", "Publico");
        h6.setDistanciaKm(47);
        Hospital h7 = new Hospital("001", "Santa Maria", "Privado");
        h7.setDistanciaKm(46);
        Hospital h8 = new Hospital("002", "São Jose", "Publico");
        h8.setDistanciaKm(50);

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

    public List<Hospital> getSugestao(final String gravidade, final String tipoUrgencia){
        Collections.sort(hospitalData);
        List<Hospital> hospitaisSugestao=new ArrayList<>();
        for(int idx=0; idx<3; idx++ ){
            hospitaisSugestao.add(hospitalData.get(idx));
        }
        return  hospitaisSugestao;
    }


}
