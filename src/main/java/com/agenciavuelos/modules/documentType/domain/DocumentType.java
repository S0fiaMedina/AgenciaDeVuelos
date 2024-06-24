package com.agenciavuelos.modules.documentType.domain;

/**Esta clase representa el tipo de documento que puede tener un cliente */
public class DocumentType {
    int id;
    String name;
    
    public DocumentType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    

    public DocumentType(String name) {
        this.name = name;
    }



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



    @Override
    public String toString() {
        return "DocumentType [ id="  + id + ", name =" + name + "]";
    }

    

    
}
