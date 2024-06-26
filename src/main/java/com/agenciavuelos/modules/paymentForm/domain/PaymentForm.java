package com.agenciavuelos.modules.paymentForm.domain;

public class PaymentForm {
    private int id;
    private String description;

    public PaymentForm(String description) {
        this.description = description;
    }

    public PaymentForm(int id, String description) {
        this.id = id;
        this.description = description;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: " +  this.id);
        sb.append("\nDescripción: ").append(this.description);
        return sb.toString();
    }
}