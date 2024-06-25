package com.agenciavuelos.modules.gate.domain;

public class Gate {
    private int id;
    private String gateNumber;
    private String idAirport;

    public Gate(String gateNumber, String idAirport) {
        this.gateNumber = gateNumber;
        this.idAirport = idAirport;
    }

    public Gate(int id, String gateNumber, String idAirport) {
        this.id = id;
        this.gateNumber = gateNumber;
        this.idAirport = idAirport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    public String getIdAirport() {
        return idAirport;
    }

    public void setIdAirport(String idAirport) {
        this.idAirport = idAirport;
    }
}