package com.agenciavuelos.modules.flightConnection.domain;

public class FlightConnection {
    private int id;
    private String connectionNumber;
    private int idTrip;
    private String idAirport;
    private String planePlates;

    private String airportName;


    public FlightConnection() {
    }


    public FlightConnection(int id, String connectionNumber, int idTrip, String idAirport, String planePlates) {
        this.id = id;
        this.connectionNumber = connectionNumber;
        this.idTrip = idTrip;
        this.idAirport = idAirport;
        this.planePlates = planePlates;
    }

    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }



    public String getConnectionNumber() {
        return connectionNumber;
    }



    public void setConnectionNumber(String connectionNumber) {
        this.connectionNumber = connectionNumber;
    }



    public int getIdTrip() {
        return idTrip;
    }



    public void setIdTrip(int idTrip) {
        this.idTrip = idTrip;
    }



    public String getIdAirport() {
        return idAirport;
    }



    public void setIdAirport(String idAirport) {
        this.idAirport = idAirport;
    }


    public String getPlanePlates() {
        return planePlates;
    }


    public void setPlanePlates(String planePlates) {
        this.planePlates = planePlates;
    }


    public String getAirportName() {
        return airportName;
    }


    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    

    
    // se pregunta al usuario si desea un vuelo directo o de escalas
    // si es directo... SE HACE SOLO UNA CONEXION 
    // 
}