package com.agenciavuelos.modules.tripulationRole.domain;

public class TripulationRole {
    private int id; 
    private String name;

    public TripulationRole(String name) {
        this.name = name;
    }

    public TripulationRole(int id, String name) {
        this.id = id;
        this.name = name;
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
}
