package com.agenciavuelos.modules.customer.adapter.in;

import java.util.List;
import java.util.Optional;

import javax.swing.text.Document;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.customer.application.CustomerService;
import com.agenciavuelos.modules.documentType.domain.DocumentType;


public class CustomerConsoleAdapter {
    private final CustomerService customerService;

    private final  String[] customerOptions = { 
        "1. Crear tipo de documento",
        "2. Actualizar tipo de documento",
        "3. Buscar tipo de documento por ID",
        "4. Eliminar tipo de documento",
        "5. Salir"
    };

    public CustomerConsoleAdapter(CustomerService customerService) {
        this.customerService = customerService;
    }

    public int getChoiceFromUser(){
        System.out.println("-------------------------------------");
        System.out.println("MENU DE CLIENTES");
        System.out.println("-------------------------------------");
        Util.printOptions(this.customerOptions); 
        return Util.rangeValidator(1, customerOptions.length);
    }

    public void run(){
        int optionSelected = getChoiceFromUser();
        switch (optionSelected) {

            // inicializando variables
            int idFound;

            /**
             * Caso de Uso 7: Registrar Cliente
             */
            case 1: 

                // solicita al agente ingresar los detalles del cliente:
                String name = Util.getStringInput(">> Ingrese el nombre del cliente: ");
                int edad = Util.getIntInput(">> Ingrese la edad del cliente: ");

                // solicitando datos de tipo de documento
                List<DocumentType>  documentTypes = this.customerService.findAllDocumentTypes();
                documentTypes.forEach(document -> { System.out.println(document); }); 

                do{ // validacion id de tipo de documento
                    int newDocumentTypeId = Util.getIntInput(">> Ingrese el id que corresponda al tipo de documento: ");
                    idFound = this.customerService.getDocumentTypeId(newDocumentTypeId);
                } while (idFound == -1);

                // solicitando numero de documento

                // validacion de que el numero de dodumento sea unico





                DocumentType  manufacturer = new DocumentType(0,name); 

                //guarda
                this.documentTypeService.createDocumentType(manufacturer);

                break;

            case 2: // ACTUALIZAR

                // solicita los datos
                List<DocumentType> documentTypes = this.documentTypeService.findAllDocumentTypes();
               

                if (documentTypes == null || documentTypes.isEmpty()  ) // valida que hayan manufacturadores antes de cualquier cosa
                    Util.showWarning("No hay tipos de documentos registrados");

                else{
                    int id = Util.getIntInput(">> Introduzca el id a buscar: ");
                    Optional<DocumentType> optionalDocumentType = this.documentTypeService.findDocumentTypeById(id);

                
                    optionalDocumentType.ifPresentOrElse( // Aqui esta la funcion lambda

                        // Acción si el tipo de documento está presente
                        updatedDocumentType -> {
                            System.out.println("Esta es la información actual del tipo de documento:\n " + updatedDocumentType);
                            String newName = Util.getStringInput(">> Ingrese el nuevo nombre del tipo de documento: ");

                            // Cambiar el nombre del tipo de documento
                            updatedDocumentType.setName(newName);

                            // Actualizar el tipo de documento en la base de datos
                            this.documentTypeService.updateDocumentType(updatedDocumentType);
                        },

                        // Acción si el tipo de documento no está presente (ID no encontrado)
                        () -> {
                            System.out.println("ID no encontrado");
                        });
                    }
                break;

            case 3: // BUSCAR
                int id = Util.getIntInput(">> Introduzca el id a buscar: ");
                Optional<DocumentType> foundDocumentType = this.documentTypeService.findDocumentTypeById(id);
                
                // estoy empezando a creer que esta logica de validacion es mejor colocarla en una funcion aparte -_-
                foundDocumentType.ifPresentOrElse(
                    spottedDocumentType -> { // Si el tipo de documento fue encontrado...
                    System.out.println("Esta es la información del tipo de documento encontrado:\n" + spottedDocumentType);
                    },
                    ()-> {
                        Util.showWarning("Id no encontrado o tipo de documento inexistente");
                    }
                
                );
                break;
            
            case 4: // ELIMINAR
            int deleteId = Util.getIntInput(">> Introduzca el id a buscar: ");

            Optional<DocumentType> manufacturerToDelete = this.documentTypeService.findDocumentTypeById(deleteId);

            // Utilizar ifPresent para manejar el caso cuando el tipo de documento está presente
            
            manufacturerToDelete.ifPresentOrElse(spottedDocumentType -> {
                this.documentTypeService.deteleDocumentType(deleteId);
                Util.showWarning("Tipo de documento eliminado con exito");
            },
            () -> {
                Util.showWarning("Tipo de documento no encontrado");
            }
            );
                break;
        }
    }

    
}