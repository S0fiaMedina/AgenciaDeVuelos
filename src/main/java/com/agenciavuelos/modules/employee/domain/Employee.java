package com.agenciavuelos.modules.employee.domain;

public class Employee {
    private String id;
    private String name;
    private int idRol;
    private String ingressDate;
    private int idAirline;
    private String idAirport;
    
    public Employee(String id, String name, int idRol, String ingressDate, int idAirline, String idAirport) {
        this.id = id;
        this.name = name;
        this.idRol = idRol;
        this.ingressDate = ingressDate;
        this.idAirline = idAirline;
        this.idAirport = idAirport;
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

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getIngressDate() {
        return ingressDate;
    }

    public void setIngressDate(String ingressDate) {
        this.ingressDate = ingressDate;
    }

    public int getIdAirline() {
        return idAirline;
    }

    public void setIdAirline(int idAirline) {
        this.idAirline = idAirline;
    }

    public String getIdAirport() {
        return idAirport;
    }

    public void setIdAirport(String idAirport) {
        this.idAirport = idAirport;
    }
}