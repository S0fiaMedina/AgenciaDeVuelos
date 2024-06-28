package com.agenciavuelos.modules.documentType.adapter.in;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.documentType.application.DocumentTypeService;
import com.agenciavuelos.modules.documentType.domain.DocumentType;




public class DocumentTypeConsoleAdapter {
    private final DocumentTypeService documentTypeService;
    private final  String[] documentTypeOptions = { 
        "1. Crear tipo de documento",
        "2. Actualizar tipo de documento",
        "3. Buscar tipo de documento por ID",
        "4. Eliminar tipo de documento",
        "5. Salir"
    };

    public DocumentTypeConsoleAdapter(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    /**
     * Muestra un menú de opciones de tipos de documento y solicita al usuario que elija una opción válida.
     * 
     * @return El número de opción seleccionado por el usuario, validado dentro del rango de opciones disponibles.
    */
    public int getChoiceFromUser(){
        System.out.println("-------------------------------------");
        System.out.println("MENU DE MANUFACTURADORES");
        System.out.println("-------------------------------------");
        Util.printOptions(this.documentTypeOptions); 
        return Util.rangeValidator(1, documentTypeOptions.length);
    }


    public void run(){
        int optionSelected = getChoiceFromUser();
        switch (optionSelected) {

            case 1: // CREAR

                // solicita los datos
                String name = Util.getStringInput(">> Ingrese el nombre del tipo de documento:");
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
                            Util.showWarning("Tipo de documento inexistente o no encontrado. ");
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
            },
            () -> {
                Util.showWarning("Tipo de documento no encontrado");
            }
            );
                break;
        }
    }


}