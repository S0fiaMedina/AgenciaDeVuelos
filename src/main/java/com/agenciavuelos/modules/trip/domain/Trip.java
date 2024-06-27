package com.agenciavuelos.modules.trip.domain;

public class Trip {
    private int id;
    private String date;
    private Double price;
    private String idAirportD;
    private String idAirportA;
    private String nameCityD;
    private String nameCityA;

    public Trip() {}

    public Trip(String date, Double price, String idAirportD, String idAirportA) {
        this.date = date;
        this.price = price;
        this.idAirportD = idAirportD;
        this.idAirportA = idAirportA;
    }

    public Trip(int id, String date, Double price, String idAirportD, String idAirportA) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.idAirportD = idAirportD;
        this.idAirportA = idAirportA;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getIdAirportD() {
        return idAirportD;
    }

    public void setIdAirportD(String idAirportD) {
        this.idAirportD = idAirportD;
    }

    public String getIdAirportA() {
        return idAirportA;
    }

    public void setIdAirportA(String idAirportA) {
        this.idAirportA = idAirportA;
    }

    public String getNameCityD() {
        return nameCityD;
    }

    public void setNameCityD(String nameCityD) {
        this.nameCityD = nameCityD;
    }

    public String getNameCityA() {
        return nameCityA;
    }

    public void setNameCityA(String nameCityA) {
        this.nameCityA = nameCityA;
    }

}