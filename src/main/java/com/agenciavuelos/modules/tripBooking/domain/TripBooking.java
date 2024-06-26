package com.agenciavuelos.modules.tripBooking.domain;

public class TripBooking {
    private int id;
    private String bookingDate;
    private int idTrip;

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
}