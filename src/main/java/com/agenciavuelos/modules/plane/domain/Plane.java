package com.agenciavuelos.modules.plane.domain;

public class Plane {
    int id;
    String plates; // matricula
    int capacity;
    String fabricationDate;
    int idModel;
    int idStatus;
    int idAirline;
    String modelStr;
    String statusStr;
    String airlineStr;
    
    public Plane() {
    }

    public Plane(int id, String plates, int capacity, String fabricationDate, int idModel, int idStatus, int idAirline) {
        this.id = id;
        this.plates = plates;
        this.capacity = capacity;
        this.fabricationDate = fabricationDate;
        this.idModel = idModel;
        this.idStatus = idStatus;
        this.idAirline = idAirline;
    }

    public Plane(String plates, int capacity, String fabricationDate, int idModel, int idStatus, int idAirline) {
        this.plates = plates;
        this.capacity = capacity;
        this.fabricationDate = fabricationDate;
        this.idModel = idModel;
        this.idStatus = idStatus;
        this.idAirline = idAirline;
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
    

    public String getModelStr() {
        return modelStr;
    }

    public void setModelStr(String modelStr) {
        this.modelStr = modelStr;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getAirlineStr() {
        return airlineStr;
    }

    public void setAirlineStr(String airlineStr) {
        this.airlineStr = airlineStr;
    }

    @Override
    public String toString() { // un toString bien bonito :3 para darselo al usuario
        StringBuilder sb = new StringBuilder();
        sb.append("Detalles del cliente:");
        sb.append("\n=================");
        sb.append("\nID:\t" +  this.id);
        sb.append("\nMatricula:\t"+ this.plates);
        sb.append("\nCapacidad:\t" + this.capacity);
        sb.append("\nEstado,:\t"+ this.statusStr);
        sb.append("\nAerolínea,:\t" + this.airlineStr);
        sb.append("\nModelo,:\t" + this.modelStr);

        sb.append("\n=================");
        return sb.toString();
    }

}