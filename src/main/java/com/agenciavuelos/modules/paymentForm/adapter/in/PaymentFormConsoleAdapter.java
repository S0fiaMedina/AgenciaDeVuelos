package com.agenciavuelos.modules.paymentForm.adapter.in;

import java.util.Optional;

import com.agenciavuelos.Console.Util;
import com.agenciavuelos.modules.paymentForm.application.PaymentFormService;
import com.agenciavuelos.modules.paymentForm.domain.PaymentForm;

public class PaymentFormConsoleAdapter {
    private final PaymentFormService paymentFormService;

    // lista que contiene las opciones del menu
    private final  String[] paymentFormOptions = { 
        "1. Registrar Forma de Pago",
        "2. Buscar Forma de Pago",
        "3. Salir"
    };

    public PaymentFormConsoleAdapter(PaymentFormService paymentFormService) {
        this.paymentFormService = paymentFormService;
    }

    /**
     * @return El número de opción seleccionado por el usuario, validado dentro del rango de opciones disponibles.
    */
    public int getChoiceFromUser(){
        System.out.println("-------------------------------------");
        System.out.println("MENU DE FORMAS DE PAGO");
        System.out.println("-------------------------------------");
        Util.printOptions(this.paymentFormOptions); 
        return Util.rangeValidator(1, paymentFormOptions.length);
    }

    public void run(){
        int optionSelected = getChoiceFromUser();
        switch (optionSelected) {

            case 1: // CREAR
                // TODO: validacion de no repeticion de codigo de pais
                String description = Util.getStringInput(">> Ingrese el nombre del pais:");
                PaymentForm  paymentForm = new PaymentForm(description);
                this.paymentFormService.createPaymentForm(paymentForm);
                break;
            /*
            case 2: // ACTUALIZAR

                List<PaymentForm> paymentForms = this.paymentFormService.findAllPaymentForms();

                if (paymentForms == null || paymentForms.isEmpty())
                    Util.showWarning("No hay países registrados");

                else{
                    int idPaymentForm = Util.getIntInput(">> Introduzca el código a buscar: ");
                    Optional<PaymentForm> optionalPaymentForm = this.paymentFormService.findPaymentFormById(idPaymentForm);

                
                    optionalPaymentForm.ifPresentOrElse(
                        updatedPaymentForm -> {
                            System.out.println("Esta es la información actual del pais:\n " + updatedPaymentForm);

                            String newDescription = Util.getStringInput(">> Ingrese el nuevo nombre del pais: ");
                            
                            updatedPaymentForm.setDescription(newDescription);

                            this.paymentFormService.updatePaymentForm(updatedPaymentForm);
                        },
                        
                        () -> {
                            System.out.println("ID no encontrado");
                        });
                    }
                break;
            */
            case 2: // BUSCAR POR ID

                int SearchId = Util.getIntInput(">> Introduzca el ID a buscar: ");
                Optional<PaymentForm> foundPaymentForm = this.paymentFormService.findPaymentFormById(SearchId);
                
                foundPaymentForm.ifPresentOrElse(
                    spottedPaymentForm -> { 
                    System.out.println("Esta es la información del país encontrado:\n" + spottedPaymentForm);
                    },
                    ()-> {
                        Util.showWarning("ID no encontrado o país inexistente");
                    }
                
                );
                break;
            /*
            case 3: // ELIMINAR
                int deleteId = Util.getIntInput(">> Introduzca el ID a buscar: ");
                Optional<PaymentForm> paymentFormToDelete = this.paymentFormService.findPaymentFormById(deleteId);

                // TODO: hacer funcion de validacion de obj nulos
                paymentFormToDelete.ifPresentOrElse(
                    spottedPaymentForm -> {
                        this.paymentFormService.deletePaymentForm(deleteId);
                        System.out.println("País eliminado con éxito");
                    },
                    () -> {
                        Util.showWarning("ID no encontrado o país inexistente");
                    }); */
        }
    }
}