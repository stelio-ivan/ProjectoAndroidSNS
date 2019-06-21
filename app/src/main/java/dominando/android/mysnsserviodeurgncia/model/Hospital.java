package dominando.android.mysnsserviodeurgncia.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Hospital implements Comparable<Hospital>, Parcelable {
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Longitude")
    @Expose
    private Double longitude;
    @SerializedName("Latitude")
    @Expose
    private Double latitude;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Phone")
    @Expose
    private Integer phone;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("District")
    @Expose
    private String district;
    @SerializedName("StandbyTimesUrl")
    @Expose
    private String standbyTimesUrl;
    @SerializedName("ShareStandbyTimes")
    @Expose
    private Boolean shareStandbyTimes;
    @SerializedName("HasCTH")
    @Expose
    private Boolean hasCTH;
    @SerializedName("HasSIGLIC")
    @Expose
    private Boolean hasSIGLIC;
    @SerializedName("HasEmergency")
    @Expose
    private Boolean hasEmergency;
    @SerializedName("InstitutionURL")
    @Expose
    private String institutionURL;
    @SerializedName("Pilot")
    @Expose
    private Boolean pilot;
    private List<Urgencia> urgencias;

    private double distanciaKm;
    double distanciaLat, distanciaLong;


    public Hospital () {

    }

    public Hospital(Integer id, String name, String description, Double longitude, Double latitude, String address, Integer phone, String email, String district, String standbyTimesUrl, Boolean shareStandbyTimes, Boolean hasCTH, Boolean hasSIGLIC, Boolean hasEmergency, String institutionURL, Boolean pilot, List<Urgencia> urgencias, int distanciaKm) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.district = district;
        this.standbyTimesUrl = standbyTimesUrl;
        this.shareStandbyTimes = shareStandbyTimes;
        this.hasCTH = hasCTH;
        this.hasSIGLIC = hasSIGLIC;
        this.hasEmergency = hasEmergency;
        this.institutionURL = institutionURL;
        this.pilot = pilot;
        this.urgencias = urgencias;

    }

    //Implementação de Parcelable (Serve para passar o objeto hospital para um intent.putExtra())
    protected Hospital(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        description = in.readString();
        if (in.readByte() == 0) {
            longitude = null;
        } else {
            longitude = in.readDouble();
        }
        if (in.readByte() == 0) {
            latitude = null;
        } else {
            latitude = in.readDouble();
        }
        address = in.readString();
        if (in.readByte() == 0) {
            phone = null;
        } else {
            phone = in.readInt();
        }
        email = in.readString();
        district = in.readString();
        standbyTimesUrl = in.readString();
        byte tmpShareStandbyTimes = in.readByte();
        shareStandbyTimes = tmpShareStandbyTimes == 0 ? null : tmpShareStandbyTimes == 1;
        byte tmpHasCTH = in.readByte();
        hasCTH = tmpHasCTH == 0 ? null : tmpHasCTH == 1;
        byte tmpHasSIGLIC = in.readByte();
        hasSIGLIC = tmpHasSIGLIC == 0 ? null : tmpHasSIGLIC == 1;
        byte tmpHasEmergency = in.readByte();
        hasEmergency = tmpHasEmergency == 0 ? null : tmpHasEmergency == 1;
        institutionURL = in.readString();
        byte tmpPilot = in.readByte();
        pilot = tmpPilot == 0 ? null : tmpPilot == 1;
        //distanciaKm = in.readInt();
    }

    public static final Creator<Hospital> CREATOR = new Creator<Hospital>() {
        @Override
        public Hospital createFromParcel(Parcel in) {
            return new Hospital(in);
        }

        @Override
        public Hospital[] newArray(int size) {
            return new Hospital[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return String.valueOf(phone);
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStandbyTimesUrl() {
        return standbyTimesUrl;
    }

    public void setStandbyTimesUrl(String standbyTimesUrl) {
        this.standbyTimesUrl = standbyTimesUrl;
    }

    public Boolean getShareStandbyTimes() {
        return shareStandbyTimes;
    }

    public void setShareStandbyTimes(Boolean shareStandbyTimes) {
        this.shareStandbyTimes = shareStandbyTimes;
    }

    public Boolean getHasCTH() {
        return hasCTH;
    }

    public void setHasCTH(Boolean hasCTH) {
        this.hasCTH = hasCTH;
    }

    public Boolean getHasSIGLIC() {
        return hasSIGLIC;
    }

    public void setHasSIGLIC(Boolean hasSIGLIC) {
        this.hasSIGLIC = hasSIGLIC;
    }

    public Boolean getHasEmergency() {
        return hasEmergency;
    }

    public void setHasEmergency(Boolean hasEmergency) {
        this.hasEmergency = hasEmergency;
    }

    public String getInstitutionURL() {
        return institutionURL;
    }

    public void setInstitutionURL(String institutionURL) {
        this.institutionURL = institutionURL;
    }

    public Boolean getPilot() {
        return pilot;
    }

    public void setPilot(Boolean pilot) {
        this.pilot = pilot;
    }

    public void setUrgencias(List<Urgencia> urgencias) {
        this.urgencias = urgencias;
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


    public void setDistanciaKm(double latitude, double longitude) {
        distanciaLat=this.latitude-latitude;
        distanciaLong=this.longitude-longitude;
        this.distanciaKm =Math.sqrt(Math.pow(distanciaLat, 2)+Math.pow(distanciaLong,2));
    }

    public double getDistanciaKm() {

        return  distanciaKm;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(description);
        if (longitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(longitude);
        }
        if (latitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(latitude);
        }
        dest.writeString(address);
        if (phone == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(phone);
        }
        dest.writeString(email);
        dest.writeString(district);
        dest.writeString(standbyTimesUrl);
        dest.writeByte((byte) (shareStandbyTimes == null ? 0 : shareStandbyTimes ? 1 : 2));
        dest.writeByte((byte) (hasCTH == null ? 0 : hasCTH ? 1 : 2));
        dest.writeByte((byte) (hasSIGLIC == null ? 0 : hasSIGLIC ? 1 : 2));
        dest.writeByte((byte) (hasEmergency == null ? 0 : hasEmergency ? 1 : 2));
        dest.writeString(institutionURL);
        dest.writeByte((byte) (pilot == null ? 0 : pilot ? 1 : 2));
        dest.writeDouble(distanciaKm);
    }


}
