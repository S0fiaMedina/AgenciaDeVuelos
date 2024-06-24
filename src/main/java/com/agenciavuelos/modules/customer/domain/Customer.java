package com.agenciavuelos.modules.customer.domain;
/*
 * 
 * Clase customer
 * 
*/
public class Customer {
    int id;
    String name;
    int age;
    int documentTypeId;
    int documentNumber;

    // customer con id
    public Customer(int id, String name, int age, int documentTypeId, int documentNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.documentTypeId = documentTypeId;
        this.documentNumber = documentNumber;
    }

    // constructor sin id (para los casos de creacion)
    public Customer(String name, int age, int documentTypeId, int documentNumber) {
        this.name = name;
        this.age = age;
        this.documentTypeId = documentTypeId;
        this.documentNumber = documentNumber;
    }

    // getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(int documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public int getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(int documentNumber) {
        this.documentNumber = documentNumber;
    }

    

    

    


}