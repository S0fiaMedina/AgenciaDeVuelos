package com.agenciavuelos.modules.tripBookingDetail.domain;

public class TripBookingDetail {
    private int id;
    private int idTripBooking;
    private int idCustomer;
    private int idFare;

    public TripBookingDetail(int idTripBooking, int idCustomer, int idFare) {
        this.idTripBooking = idTripBooking;
        this.idCustomer = idCustomer;
        this.idFare = idFare;
    }

    public TripBookingDetail(int id, int idTripBooking, int idCustomer, int idFare) {
        this.id = id;
        this.idTripBooking = idTripBooking;
        this.idCustomer = idCustomer;
        this.idFare = idFare;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTripBooking() {
        return idTripBooking;
    }

    public void setIdTripBooking(int idTripBooking) {
        this.idTripBooking = idTripBooking;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdFare() {
        return idFare;
    }

    public void setIdFare(int idFare) {
        this.idFare = idFare;
    }
}