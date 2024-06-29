package com.agenciavuelos.modules.airport.domain;

public class Airport {
    private String id;
    private String name;
    private int idCity;
    
    public Airport(String id, String name, int idCity) {
        this.id = id;
        this.name = name;
        this.idCity = idCity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: " +  this.id);
        sb.append("\nNombre: ").append(this.name);
        return sb.toString();
    }
}