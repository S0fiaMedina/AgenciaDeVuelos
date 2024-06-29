package com.agenciavuelos.modules.tripBooking.adapter.in;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.customer.domain.Customer;
import com.agenciavuelos.modules.documentType.domain.DocumentType;
import com.agenciavuelos.modules.plane.domain.Plane;
import com.agenciavuelos.modules.tripBooking.application.TripBookingService;
import com.agenciavuelos.modules.tripBooking.domain.TripBooking;

/**
 * Realmente este archivo es una extension del adaptador de tripBooking 
 * Pero se tuvo que crear uno nuevo, ya que se estaba volviendo muy grande :(.
 * Este archivo contiene la logica de actualizacion de reservas
*/
public class UpdateTripBookingConsoleAdapter {
    private String[] updateOptions = {
        "1. Pasajeros"
    };

    private final TripBookingService tripBookingService;

    public UpdateTripBookingConsoleAdapter(TripBookingService tripBookingService){
        this.tripBookingService = tripBookingService;

    }
    
    public void updateTripBooking(){

        // se obtiene id del cliente 
        int updateCustomerId = Util.getIntInput(">> Ingrese el numero de documento: ");
        int updateBookingId  = Util.getIntInput(">> Ingrese el id de la reserva: ");


        Optional<TripBooking> foundBooking = this.tripBookingService.findTripBookingOfCustomer(updateCustomerId, updateBookingId);
        foundBooking.ifPresentOrElse(
            spottedBooking -> {
                System.out.println(">> Esta fue la reserva encontrada: " + spottedBooking);

                // le damos a escojer al cliente
                Util.printOptions(updateOptions);
                System.out.println(">> ¿Que desea editar? ");
                int op = Util.rangeValidator(1, updateOptions.length);

                switch (op){
                    case 1: // pasajeros 
                        this.updatePassangers(updateBookingId);
                    break;

                   
                }
                

            }, 
            () -> {
                Util.showWarning("Reserva no encontrada o inexistente");
            });
    }

    public void updatePassangers(int idBooking){
        // solicita pasajeros y los imprime 
        List <Customer> customers = this.tripBookingService.getPassangers(idBooking);
        System.out.println("-----------> PASAJEROS DE LA RESERVA <-----------");

        // recorre los pasajeros 
        customers.forEach(
            updatedCustomer -> {
                // pregunta al usuario si desea editar el pasajero en cuestion
                System.out.println(updatedCustomer);
                int op = Util.getIntInput(">> Digite 1 si desea editar este pasajero\n >> De lo contrario, digite cualquier otro numero.");

                if (op == 1){
                    // solicita al agente ingresar los detalles del cliente:
                    String updateName = Util.getStringInput(">> Ingrese el nuevo nombre del cliente: ");
                    int updateAge = Util.getIntInput(">> Ingrese la nueva edad del cliente: ");

                    // solicitando datos de tipo de documento
                    List<DocumentType>  updateDocumentTypes = this.tripBookingService.findAllDocumentTypes();
                    System.out.println("------------> TIPOS DE DOCUMENTO DISPONIBLES <-------------");
                    updateDocumentTypes.forEach(document -> { System.out.println(document); }); 

                    int updateDocumentId;
                    int idFound;
                    do{ // validacion id de tipo de documento
                        updateDocumentId = Util.getIntInput(">> Ingrese el nuevo id que corresponda al tipo de documento: ");
                        idFound = this.tripBookingService.getDocumentTypeId(updateDocumentId);
                    } while (idFound == -1);

                    // setteando los datos
                    updatedCustomer.setName(updateName);
                    updatedCustomer.setAge(updateAge);
                    updatedCustomer.setDocumentTypeId(updateDocumentId);

                    this.tripBookingService.updateCustomer(updatedCustomer);
                } 
            }
        );
    }

    
}
