package com.agenciavuelos.modules.revision.domain;

public class Revision {
    int id;
    String planePlate;
    String revisionDate;
    String description;

    public Revision(){}
    public Revision(String planePlate, String revisionDate, String description) {
        this.planePlate = planePlate;
        this.revisionDate = revisionDate;
        this.description = description;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPlanePlate() {
        return planePlate;
    }
    public void setPlanePlate(String planePlate) {
        this.planePlate = planePlate;
    }
    public String getRevisionDate() {
        return revisionDate;
    }
    public void setRevisionDate(String revisionDate) {
        this.revisionDate = revisionDate;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() { // un toString bien bonito :3 para darselo al usuario
        StringBuilder sb = new StringBuilder();
        sb.append("id: " + this.id );
        sb.append("- placa del avion: " + this.planePlate);
        sb.append("- fecha de revision: " + this.revisionDate);
        return sb.toString();
    }

}