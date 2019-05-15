package dominando.android.mysnsserviodeurgncia.model;

import android.net.Uri;

public class Hospital {
    private String uuid;
    private String nome;
    private String tipo;
    private String telefone;
    private String morada;
    private String email;
    private String url;
    private  Urgencia urgencia;
    private int distanciaKm;


    public Hospital () {

    }
    public Hospital(String uuid, String nome, String tipo){
        this.uuid = uuid;
        this.nome = nome;
        this.tipo = tipo;
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

    public Urgencia getUrgencia() {
        return urgencia;
    }

    public int getDistanciaKm() {
        return distanciaKm;
    }
}
