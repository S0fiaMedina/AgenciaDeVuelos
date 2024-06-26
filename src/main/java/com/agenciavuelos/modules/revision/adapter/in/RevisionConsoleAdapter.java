package com.agenciavuelos.modules.revision.adapter.in;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;

import com.agenciavuelos.modules.revision.application.RevisionService;
import com.agenciavuelos.modules.revision.domain.Revision;
import com.agenciavuelos.modules.revisionDetail.domain.RevisionDetail;

public class RevisionConsoleAdapter{

    private final RevisionService revisionService;
    private final  String[] revisionOptions = { 
        "1. Registrar revision",
        "2. Consultar historial de revisiones",
        "3. Actualizar revision",
        "4. Eliminar revision",
        "5. Salir"
    };

    public RevisionConsoleAdapter(RevisionService revisionService) {
        this.revisionService = revisionService;
    }

    public int getChoiceFromUser(){
        System.out.println("-------------------------------------");
        System.out.println("MENU DE REVISIONES");
        System.out.println("-------------------------------------");
        Util.printOptions(this.revisionOptions); 
        return Util.rangeValidator(1, revisionOptions.length);
    }


    public void run(){

        int optionSelected = getChoiceFromUser();

        switch (optionSelected) {

            /**
             * Caso de Uso 4: Registrar Revisión de Mantenimiento
            */
            case 1:

                // FECHA DE FABRICACIÓN
                String newRevisionDate;
                boolean isCorrect;
                do {
                    newRevisionDate = Util.getStringInput(">> Ingrese la fecha de revision: (yyyy- mm - dd)");
                    isCorrect = Util.checkDateFormat(newRevisionDate, "yyyy-MM-dd");
                } while (isCorrect == false);
                

                // EMPLEADO
                String newIdFound;
                String newEmployeeId;
                do {
                    newEmployeeId = Util.getStringInput(">> Ingrese el id del empleado responsable del avión: ");
                    newIdFound = this.revisionService.getIdEmployee(newEmployeeId);
                } while (newIdFound == "");

                // AVION
                String newPlanePlate;
                do {
                    newPlanePlate = Util.getStringInput(">> Ingrese la matricula del avion al que se la hecho mantenimiento ");
                    newIdFound = this.revisionService.getPlatePlane(newPlanePlate);
                } while (newIdFound == "");

                // descripcion
                String newDescription = Util.getStringInput(">> Agregue detalles de la revisión");

                Revision newRevision = new Revision(newPlanePlate, newRevisionDate, newDescription);

                int newRevisionId = this.revisionService.createRevision(newRevision);
                this.revisionService.addEmployeeToRevision(new RevisionDetail(newEmployeeId, newRevisionId));

                Util.showSuccess("revision registrada correctamente");

                break;
        
            /**
             * Caso de Uso 12: Consultar Historial de Revisiones de Avión
            */
            case 2:
            // AVION
                String searchPlanePlate;
                searchPlanePlate = Util.getStringInput(">> Ingrese la matricula deL avion al que se la hecho mantenimiento ");
                List<Revision> foundRevisions = this.revisionService.findAllevisionsByPlane(searchPlanePlate);

                if ( foundRevisions.isEmpty() ){
                    Util.showWarning("El avion no ha pasado por revisiones");
                } else {
                    foundRevisions.forEach( revision -> {System.out.println(revision);});
                }
            
            break;

            /**
             * Caso de Uso 24: Actualizar Información de Revisión
            */
            case 3:
                int updateRevisionId;
                updateRevisionId = Util.getIntInput(">> Ingrese el id de la revision ");
                Optional<Revision>foundRevision = this.revisionService.findRevisionById(updateRevisionId);

                foundRevision.ifPresentOrElse(

                spottedRevision -> { 
                    System.out.println("Esta es la información de la revision encontrado:\n" + spottedRevision);
                    // FECHA DE FABRICACIÓN
                    String updateRevisionDate;
                    boolean isCorrect2;
                    do {
                        updateRevisionDate = Util.getStringInput(">> Ingrese la fecha de revision: (yyyy- mm - dd)");
                        isCorrect2 = Util.checkDateFormat(updateRevisionDate, "yyyy-MM-dd");
                    } while (isCorrect2 == false);
                    

                    // EMPLEADO
                    String updateIdFound;
                    String updateEmployeeId;
                    do {
                        updateEmployeeId = Util.getStringInput(">> Ingrese el id del empleado responsable del avión: ");
                        updateIdFound = this.revisionService.getIdEmployee(updateEmployeeId);
                    } while (updateIdFound == "");

                    // AVION
                    String updatePlanePlate;
                    do {
                        updatePlanePlate = Util.getStringInput(">> Ingrese la matricula del avion al que se la hecho mantenimiento ");
                        updateIdFound = this.revisionService.getPlatePlane(updatePlanePlate);
                    } while (updateIdFound == "");

                    // descripcion
                    String updateDescription = Util.getStringInput(">> Agregue detalles de la revisión");

                    spottedRevision.setDescription(updateDescription);
                    spottedRevision.setPlanePlate(updatePlanePlate);
                    spottedRevision.setRevisionDate(updateRevisionDate);

                    this.revisionService.updateRevision(spottedRevision);
                    this.revisionService.updateEmployeeToRevision(new RevisionDetail(updateEmployeeId, spottedRevision.getId()));
                    Util.showSuccess("Revision con el id: " + spottedRevision.getId() + " actualizada exitosamente");

                    },
                    ()-> {
                        Util.showWarning("Id no encontrado o revision inexistente");
                    }
                );
            break;

            /**
             * Caso de Uso 25: Eliminar Revisión de Mantenimiento
             */    
            case 4:
                int idDelete; 
                idDelete = Util.getIntInput(">> Ingresa el id de la revision : ");
                Optional<Revision> deletedRevision = this.revisionService.findRevisionById(idDelete);
                    
                deletedRevision.ifPresentOrElse(
                spottedRevision -> { 
                    this.revisionService.deleteEmployeeToRevision(idDelete);
                    this.revisionService.deleteRevision(idDelete);
                    Util.showSuccess("Revision con el id: " + idDelete + " Eliminada exitosamente");
                },
                ()-> {
                    Util.showWarning("Id no encontrado o avion inexistente");
                }
            );
                
            break;
        }
    }


}