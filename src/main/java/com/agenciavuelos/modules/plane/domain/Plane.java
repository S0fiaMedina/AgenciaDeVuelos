package com.agenciavuelos.modules.plane.domain;

public class Plane {
    int id;
    String plates; // matricula
    int capacity;
    String fabricationDate;
    int idModel;
    int idStatus;
    int idAirline;
    
    public Plane() {
    }

    public Plane(int id, String plates, int capacity, String fabricationDate, int idModel, int idStatus) {
        this.id = id;
        this.plates = plates;
        this.capacity = capacity;
        this.fabricationDate = fabricationDate;
        this.idModel = idModel;
        this.idStatus = idStatus;
    }

    public Plane(String plates, int capacity, String fabricationDate, int idModel, int idStatus) {
        this.plates = plates;
        this.capacity = capacity;
        this.fabricationDate = fabricationDate;
        this.idModel = idModel;
        this.idStatus = idStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlates() {
        return plates;
    }

    public void setPlates(String plates) {
        this.plates = plates;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getFabricationDate() {
        return fabricationDate;
    }

    public void setFabricationDate(String fabricationDate) {
        this.fabricationDate = fabricationDate;
    }

    public int getIdModel() {
        return idModel;
    }

    public void setIdModel(int idModel) {
        this.idModel = idModel;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public int getIdAirline() {
        return idAirline;
    }

    public void setIdAirline(int idAirline) {
        this.idAirline = idAirline;
    }

    

}