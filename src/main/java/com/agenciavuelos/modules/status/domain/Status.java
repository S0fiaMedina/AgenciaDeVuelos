package com.agenciavuelos.modules.status.domain;

public class Status {
    int id;
    String name;

    public Status(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Status(String name) {
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
        sb.append("id: " + this.id + ", nombre: " + this.name);
        return sb.toString();
    }

    
}