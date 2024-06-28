package com.agenciavuelos.modules.tripBooking.domain;

public class TripBooking {
    private int id;
    private String bookingDate;
    private int idTrip;
    private int idCustomer;
    private String tripDate;
    private String nameCustomer;
    private String documentCustomer;
    private int seatNumber;
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

    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getDocumentCustomer() {
        return documentCustomer;
    }

    public void setDocumentCustomer(String documentCustomer) {
        this.documentCustomer = documentCustomer;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getValueFare() {
        return valueFare;
    }

    public void setValueFare(int valueFare) {
        this.valueFare = valueFare;
    }
}