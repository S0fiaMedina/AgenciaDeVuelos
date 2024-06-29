package com.agenciavuelos.modules.model.domain;

public class Model {
    int id;
    String name;
    int idManufacturer;

    
    public Model(String name, int idManufacturer) {
        this.name = name;
        this.idManufacturer = idManufacturer;
    }


    public Model(int id, String name, int idManufacturer) {
        this.id = id;
        this.name = name;
        this.idManufacturer = idManufacturer;
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


    public int getIdManufacturer() {
        return idManufacturer;
    }


    public void setIdManufacturer(int idManufacturer) {
        this.idManufacturer = idManufacturer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: " +  this.id);
        sb.append("\nNombre: ").append(this.name);
        return sb.toString();
    }

    
}