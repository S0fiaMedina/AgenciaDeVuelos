package com.agenciavuelos.modules.flightFare.domain;

public class FlightFare {
    private int id;
    private String description;
    private String details;
    private Double value;

    public FlightFare(String description, String details, Double value) {

        this.description = description;
        this.details = details;
        this.value = value;
    }
    
    public FlightFare(int id, String description, String details, Double value) {
        this.id = id;
        this.description = description;
        this.details = details;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: " +  this.id);
        sb.append("\nDescripción: ").append(this.description);
        sb.append("\nDetalles: ").append(this.details);
        sb.append("\nValor: ").append(this.value);
        return sb.toString();
    }
}