package com.agenciavuelos.modules.customer.adapter.in;

import java.util.List;
import java.util.Optional;



import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.customer.application.CustomerService;
import com.agenciavuelos.modules.customer.domain.Customer;
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
        int idFound;
        switch (optionSelected){
            

            /**
             * Caso de Uso 7: Registrar Cliente
             */
            case 1: 

                // solicita al agente ingresar los detalles del cliente:
                String name = Util.getStringInput(">> Ingrese el nombre del cliente: ");
                int age = Util.getIntInput(">> Ingrese la edad del cliente: ");

                // solicitando datos de tipo de documento
                List<DocumentType>  documentTypes = this.customerService.findAllDocumentTypes();
                documentTypes.forEach(document -> { System.out.println(document); }); 

                int newDocumentTypeId;
                do{ // validacion id de tipo de documento
                     newDocumentTypeId = Util.getIntInput(">> Ingrese el id que corresponda al tipo de documento: ");
                    idFound = this.customerService.getDocumentTypeId(newDocumentTypeId);
                } while (idFound == -1);
                

                // solicitando numero de documento
                int docNumber = 0;
                do {
                     docNumber = Util.getIntInput(">> Introduzca el numero de indentificacion del cliente\n NOTA: debe ser un numero unico"); 
                }while (this.customerService.verifyDocumentNumber(docNumber) != 0);

                // validacion de que el numero de dodumento sea unico

                Customer  customer = new Customer(name, age, newDocumentTypeId, docNumber); 

                //guarda
                this.customerService.createCustomer(customer);

                break;

            case 2:
                break;
            }

 
            
        }
    
    
}