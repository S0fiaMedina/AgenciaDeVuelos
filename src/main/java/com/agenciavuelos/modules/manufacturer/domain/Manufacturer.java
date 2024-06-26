package com.agenciavuelos.modules.manufacturer.domain;

/*
 * Esta es la clase que representa a los manufacturadores de un avion
 * o las empresas encargadas de crear un modelo de avion
*/
public class Manufacturer {
    private int id; 
    private String name;

    
    public Manufacturer() {
    }

    public Manufacturer(String name) {
        this.name = name;
    }


    public Manufacturer(int id, String name) {
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