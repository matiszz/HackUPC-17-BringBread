package com.banannaps.cool.bringbread;

/**
 * Created by aleix on 5/3/2017.
 */

public class Tasca {

    public Tasca(double latitude, double longitude, String message, String location){
        this.latitude = latitude;
        this.longitude = longitude;
        this.message = message;
        this.location = location;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void marcarEnsenyada(){
        jaEnsenyada = true;
    }

    public boolean isEnsenyada(){
        return jaEnsenyada;
    }

    private double latitude;
    private double longitude;
    private String message;
    private String location;
    private boolean jaEnsenyada = false;
}
