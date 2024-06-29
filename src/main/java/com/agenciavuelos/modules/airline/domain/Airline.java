package com.agenciavuelos.modules.airline.domain;

public class Airline {
    private int id;
    private String name;

    public Airline(String name) {
        this.name = name;
    }

    public Airline(int id, String name) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: " +  this.id);
        sb.append("\nNombre: ").append(this.name);
        return sb.toString();
    }

}