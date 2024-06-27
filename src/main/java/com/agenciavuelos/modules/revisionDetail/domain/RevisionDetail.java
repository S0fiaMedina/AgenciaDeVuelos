package com.agenciavuelos.modules.revisionDetail.domain;

public class RevisionDetail {
    
    String employeeId;
    int revisionId;
    
    public RevisionDetail(String employeeId, int revisionId) {
        this.employeeId = employeeId;
        this.revisionId = revisionId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public int getRevisionId() {
        return revisionId;
    }

    public void setRevisionId(int revisionId) {
        this.revisionId = revisionId;
    }

    
}