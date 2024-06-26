package com.agenciavuelos.modules.tripBooking.domain;

public class TripBooking {
    private int id;
    private String bookingDate;
    private int idTrip;
    private int idCustomer;
    private String nameCustomer;
    private int valueFare;

    public TripBooking() {}

    public TripBooking(String bookingDate, int idTrip) {
        this.bookingDate = bookingDate;
        this.idTrip = idTrip;
    }

    public TripBooking(int id, String bookingDate, int idTrip) {
        this.id = id;
        this.bookingDate = bookingDate;
        this.idTrip = idTrip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(int idTrip) {
        this.idTrip = idTrip;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public int getValueFare() {
        return valueFare;
    }

    public void setValueFare(int valueFare) {
        this.valueFare = valueFare;
    }
}