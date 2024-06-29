package com.agenciavuelos.modules.payment.domain;

public class Payment {
    private int id;
    private int idCustomer;
    private int idPaymentForm;
    private int idTripBooking;

    public Payment(int idCustomer, int idPaymentForm, int idTripBooking) {
        this.idCustomer = idCustomer;
        this.idPaymentForm = idPaymentForm;
        this.idTripBooking = idTripBooking;
    }

    public Payment(int id, int idCustomer, int idPaymentForm, int idTripBooking) {
        this.id = id;
        this.idCustomer = idCustomer;
        this.idPaymentForm = idPaymentForm;
        this.idTripBooking = idTripBooking;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdPaymentForm() {
        return idPaymentForm;
    }

    public void setIdPaymentForm(int idPaymentForm) {
        this.idPaymentForm = idPaymentForm;
    }

    public int getIdTripBooking() {
        return idTripBooking;
    }

    public void setIdTripBooking(int idTripBooking) {
        this.idTripBooking = idTripBooking;
    }
}