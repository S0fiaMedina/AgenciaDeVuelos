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
        "1. Registrar cliente",
        "2. Consultar cliente",
        "3. Actualizar cliente",
        "4. Salir"
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
            
            /**
             * CASO 5 : Consultar Información de Cliente
             */
            case 2:
                idFound = Util.getIntInput(">> Ingresa el id a buscar: ");
                Optional<Customer> foundCustomer = this.customerService.findCustomerById(idFound);
                    
                foundCustomer.ifPresentOrElse(
                    spottedCustomer -> { // Si el tipo de documento fue encontrado...
                    System.out.println("Esta es la información del cliente encontrado:\n" + spottedCustomer);
                    },
                    ()-> {
                        Util.showWarning("Id no encontrado o cliente inexistente");
                    }
                );
                break;
            
            /**
             * CASO DE USO 13/22 pq Estan repetidos :p : Actualizar informacion del cliente
            */
            case 3:
                List<Customer> customers = this.customerService.findAllCustomers();
                if (customers == null || customers.isEmpty()  ){ // valida que hayan manufacturadores antes de cualquier cosa
                    Util.showWarning("No hay clientes registrados");
                } else{

                    // busca id
                    int id = Util.getIntInput(">> Introduzca el id a buscar: ");
                    Optional<Customer> optionalCustomer = this.customerService.findCustomerById(id);

                    // mostrar informacion actual
                    System.out.println(">> Cliente encontrado. esta es su información actual");
                    System.out.println(optionalCustomer);

                    // validacion id
                    optionalCustomer.ifPresentOrElse(

                        updatedCustomer -> {
                            int idFound2; // pongo esto porque a java no le gusta usar variables del exterior en lambdas

                             // solicita al agente ingresar los detalles del cliente:
                            String updateName = Util.getStringInput(">> Ingrese el nuevo nombre del cliente: ");
                            int updateAge = Util.getIntInput(">> Ingrese la nueva edad del cliente: ");

                            // solicitando datos de tipo de documento
                            List<DocumentType>  updateDocumentTypes = this.customerService.findAllDocumentTypes();
                            updateDocumentTypes.forEach(document -> { System.out.println(document); }); 

                            int updateDocumentId;
                            
                            do{ // validacion id de tipo de documento
                                updateDocumentId = Util.getIntInput(">> Ingrese el nuevo id que corresponda al tipo de documento: ");
                                idFound2 = this.customerService.getDocumentTypeId(updateDocumentId);
                            } while (idFound2 == -1);
                            

                            // solicitando numero de documento y  validacion de que el numero de dodumento sea unico
                            int updatedDocNumber = 0;
                            do {
                                updatedDocNumber = Util.getIntInput(">> Introduzca el numero de indentificacion del cliente\n NOTA: debe ser un numero unico"); 
                            }while (this.customerService.verifyDocumentNumber(updatedDocNumber) != 0); 

                            // setteando los datos
                            updatedCustomer.setName(updateName);
                            updatedCustomer.setAge(updateAge);
                            updatedCustomer.setDocumentTypeId(updateDocumentId);
                            updatedCustomer.setDocumentNumber(updatedDocNumber);

                            this.customerService.updateCustomer(updatedCustomer);
                            

                        }, 
                        () -> {
                            Util.showWarning("id no encontrado o cliente inexistente");
                        });

                }
                break;

            }

 
            
        }
    
    
}