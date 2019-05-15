package dominando.android.mysnsserviodeurgncia.model;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class Hospital implements Comparable<Hospital>{
    private String uuid;
    private String nome;
    private String tipo;
    private String telefone;
    private String morada;
    private String email;
    private String url;
    private List<Urgencia> urgencias;
    private int distanciaKm;

    public Hospital () {

    }
    public Hospital(String uuid, String nome, String tipo){
        this.uuid = uuid;
        this.nome = nome;
        this.tipo = tipo;
        urgencias = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getUuid() {
        return uuid;
    }

    public String getTipo() {
        return tipo;
    }

    public List<Urgencia> getUrgencias() {
        return this.urgencias;
    }

    public Urgencia getUrgencia(final String gravidadeUrgencia, final String tipoUrgencia){
        for(int idx = 0; idx < urgencias.size(); idx++) {
            if(urgencias.get(idx).getTipo().equals(tipoUrgencia) && urgencias.get(idx).getGravidade().equals(gravidadeUrgencia) ){
                return urgencias.get(idx);
            }
        }
        return null;
    }

    public void setUrgencia(Urgencia urgencia) {
        urgencias.add(urgencia);
    }

    public void deleteUrgencia(String tipo){
        for(int idx = 0; idx < urgencias.size(); idx++) {
            if(urgencias.get(idx).getTipo().equals("tipo")){
                urgencias.remove(idx);
            }
        }
    }

    public void setDistanciaKm(int distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public int getDistanciaKm() {
        return distanciaKm;
    }


    @Override
    public int compareTo(Hospital o) {
        if(distanciaKm>o.distanciaKm){
            return 1;
        }else if(distanciaKm<o.distanciaKm){
            return  -1;
        }else {
            return 0;
        }
    }
}
