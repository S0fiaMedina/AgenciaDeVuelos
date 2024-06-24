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
    String typeOfDoc; // esto es para mostrarlo en el toString -_-

    public Customer(){};
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

    public String gettypeOfDoc() {
        return typeOfDoc;
    }

    public void settypeOfDoc(String typeOfDoc) {
        this.typeOfDoc = typeOfDoc;
    }

    @Override
    public String toString() { // un toString bien bonito :3 para darselo al usuario
        StringBuilder sb = new StringBuilder();
        sb.append("Detalles del cliente:");
        sb.append("\n=================");
        sb.append("\nID:\t" +  this.id);
        sb.append("\nNombre:\t"+ this.name);
        sb.append("\nEdad:\t" + this.age);
        sb.append("\nTipo de documento:\t"+ this.typeOfDoc);
        sb.append("\nNumero de documento:\t" + this.documentNumber);
        sb.append("\n=================");
        return sb.toString();
    }



    

    

    


}