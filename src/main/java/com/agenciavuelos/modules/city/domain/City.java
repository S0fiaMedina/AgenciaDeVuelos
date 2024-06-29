package com.agenciavuelos.modules.city.domain;

public class City {
    private int id;
    private String name;
    private int idCountry;

    public City(String name, int idCountry) {
        this.name = name;
        this.idCountry = idCountry;
    }

    public City(int id, String name, int idCountry) {
        this.id = id;
        this.name = name;
        this.idCountry = idCountry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

}