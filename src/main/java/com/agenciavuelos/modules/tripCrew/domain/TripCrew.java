package com.agenciavuelos.modules.tripCrew.domain;

public class TripCrew {
    private String idEmployee;
    private int idTrip;
    private String rolEmployee;
    private String nameEmployee;

    public TripCrew(String idEmployee, int idTrip) {
        this.idEmployee = idEmployee;
        this.idTrip = idTrip;
    }
    public TripCrew(String idEmployee, int idTrip, String rolEmployee, String nameEmployee) {
        this.idEmployee = idEmployee;
        this.idTrip = idTrip;
        this.rolEmployee = rolEmployee;
        this.nameEmployee = nameEmployee;
    }
    public String getIdEmployee() {
        return idEmployee;
    }
    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }
    public int getIdTrip() {
        return idTrip;
    }
    public void setIdTrip(int idTrip) {
        this.idTrip = idTrip;
    }
    public String getRolEmployee() {
        return rolEmployee;
    }
    public void setRolEmployee(String rolEmployee) {
        this.rolEmployee = rolEmployee;
    }
    public String getNameEmployee() {
        return nameEmployee;
    }
    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    @Override
    public String toString() { // un toString bien bonito :3 para darselo al usuario
        StringBuilder sb = new StringBuilder();
        sb.append("id del empleado : ").append(this.idEmployee);
        sb.append(", nombre del empleado: ").append(this.nameEmployee);
        sb.append("\nrol del empleado: ").append(this.rolEmployee);
        return sb.toString();
    }
    

    

}