package com.agenciavuelos.modules.model.adapter.in;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.manufacturer.domain.Manufacturer;
import com.agenciavuelos.modules.model.application.ModelService;
import com.agenciavuelos.modules.model.domain.Model;

public class ModelConsoleAdapter {
    private final ModelService modelService;
    private String[] modelOptions = {
        "1. Registrar modelo",
        "2. Consultar modelo",
        "3. Actualizar modelo",
        "4. Elimnar modelo",
        "5. Salir"
    };
    public ModelConsoleAdapter(ModelService modelService) {
        this.modelService = modelService;
    }

    public int getChoiceFromUser(){
        System.out.println("-------------------------------------");
        System.out.println("MENU DE MODELOS");
        System.out.println("-------------------------------------");
        Util.printOptions(this.modelOptions); 
        return Util.rangeValidator(1, modelOptions.length);
    }
    
    public void run(){
        int optionSelected = getChoiceFromUser();
        int idFound;
        switch (optionSelected){
        
            case 1: // CREAR

                // solicita al agente ingresar los detalles del mdoelo:
                String newName = Util.getStringInput(">> Ingrese el nombre del mdoelo: ");
                

                // solicitando datos de fabricante
                System.out.println(" ------------> FABRICANTES DISPONIBLES <-------------");
                List<Manufacturer>  manufacturers = this.modelService.findAllManufacturers();
                manufacturers.forEach(document -> { System.out.println(document); }); 

                int newManufacturerId;
                do{ // validacion id de tipo de documento
                    newManufacturerId = Util.getIntInput(">> Ingrese el id que corresponda al fabricante: ");
                    idFound = this.modelService.getIdManufacturerId(newManufacturerId);
                } while (idFound == -1);
                
                Model  model = new Model(newName, newManufacturerId); 

                //guarda
                this.modelService.createModel(model);
                Util.showSuccess("Modelo registrado con exito");

                break;
            
            // consultar modelo
            case 2:
                idFound = Util.getIntInput(">> Ingresa el id a buscar: ");
                Optional<Model> foundModel = this.modelService.findModelById(idFound);
                    
                foundModel.ifPresentOrElse(
                    spottedModel -> { // Si el tipo de documento fue encontrado...
                    System.out.println("Esta es la información del modelo encontrado:\n" + spottedModel);
                    },
                    ()-> {
                        Util.showWarning("Id no encontrado o modelo inexistente");
                    }
                );
                break;
            
            // ACTUALIZAR
            case 3:
                // busca id
                int id = Util.getIntInput(">> Introduzca el id a buscar: ");
                Optional<Model> optionalModel = this.modelService.findModelById(id);

                // mostrar informacion actual
                System.out.println(">> Modelo encontrado. esta es su información actual");
                System.out.println(optionalModel);

                // validacion id
                optionalModel.ifPresentOrElse(

                    updatedModel -> {
                        int idFound2; // pongo esto porque a java no le gusta usar variables del exterior en lambdas

                            // singreso de datos
                        String updateName = Util.getStringInput(">> Ingrese el nuevo nombre del modelo: ");

                        // solicitando datos de tipo de documento
                        List<Manufacturer>  updateManufacturers = this.modelService.findAllManufacturers();
                        updateManufacturers.forEach(document -> { System.out.println(document); }); 

                        int updateManufacturerId;
                        
                        do{ // validacion id de tipo de documento
                            updateManufacturerId = Util.getIntInput(">> Ingrese el nuevo id que corresponda al fabricante: ");
                            idFound2 = this.modelService.getIdManufacturerId(updateManufacturerId);
                        } while (idFound2 == -1);
                        


                        // setteando los datos
                        updatedModel.setName(updateName);
                        updatedModel.setIdManufacturer(updateManufacturerId);

                        this.modelService.updateModel(updatedModel);
                        

                    }, 
                    () -> {
                        Util.showWarning("id no encontrado o cliente inexistente");
                    });
                break;
            
            case 4: // ELIMINAR
                int deleteId = Util.getIntInput(">> Introduzca el id a buscar: ");

                Optional<Model> manufacturerToDelete = this.modelService.findModelById(deleteId);

                // Utilizar ifPresent para manejar el caso cuando el tipo de documento está presente
                
                manufacturerToDelete.ifPresentOrElse(spottedModel -> {
                    this.modelService.deleteModel(deleteId);
        
                },
                () -> {
                    Util.showWarning("modelo no encontrado");
                }
                );
                break;
        }
                
    }
}



