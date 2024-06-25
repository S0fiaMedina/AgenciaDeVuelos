package com.agenciavuelos.Console;

import java.util.Scanner;

/**
 * NOTA PARA LA SUSTENTACIÓN: 
 * como lo dice su nombre, esta clase contiene funciones estaticas que son utiles
 * es decir, no son escenciales y el programa podria funcionar sin ellas, pero habria que hacer mas lineas
 * de codigo, por lo que son utiles para unificar logica repetida y general que este relacionada con la interaccion
 * con el usuario.
 */
@SuppressWarnings("resource")
public class Util {

    /**
     * Solicita al usuario ingresar un número entero positivo desde la consola.
     * Muestra un mensaje proporcionado y espera a que se ingrese un número entero positivo válido.
     *
     * @param message El mensaje que se mostrará al usuario antes de solicitar la entrada.
     * @return El número entero positivo ingresado por el usuario.
    */

    public static int getIntInput(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        while (true) {
            try {
                String input = scanner.nextLine().trim(); 
                int num = Integer.parseInt(input);  
                if (num >= 0){
                    return num;
                }
                System.out.println("!\tERROR: Ingresa un numero positivo");

            } catch (NumberFormatException e) {
                System.out.println("!\tERROR: Debes ingresar un número entero. Intenta de nuevo.");
                System.out.println(message);
            }
        }
    }

 
    /**
     * Solicita al usuario ingresar un texto desde la consola.
     * Muestra un mensaje proporcionado y espera a que se ingrese un texto no vacío.
     *
     * @param message El mensaje que se mostrará al usuario antes de solicitar la entrada.
     * @return El texto ingresado por el usuario (no vacío).
     */
    public static String getStringInput(String message){
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        while (true) {
            String value = scanner.nextLine().trim();
            if (!value.equals("")) {
                return value;
            }
            System.out.println("!\tERROR: No es posible registrar un texto vacio");
        }
    }


    /**
     * Imprime las opciones proporcionadas en un arreglo, cada una en una nueva línea.
     * 
     * @param opciones Arreglo de cadenas que contiene las opciones a imprimir.
     */
    public static void printOptions(String[] opciones) {
        for (String opcion : opciones) {
            System.out.println(opcion);
        }
    }

    /**
     * Método estático para validar y obtener un número dentro de un rango específico.
     * Lo que puede ser util para validar que un numero este dentro de unas opciones
     * Permite al usuario introducir un número por consola y verifica que esté dentro
     * del rango especificado.
     *
     * @param minNumber Valor mínimo del rango (inclusive).
     * @param maxNumber Valor máximo del rango (inclusive).
     * @return El número validado que se encuentra dentro del rango especificado.
     */
    public static int rangeValidator(int minNumber, int maxNumber) {
        Scanner sc = new Scanner(System.in);

        int number;
        do {
            try {
                number = sc.nextInt();

                if (number >= minNumber && number <= maxNumber) {
                    return number;
                } else {
                    System.out.println("Error: El número debe estar dentro del rango especificado.\nIngresa un numero: ");
                }
            } catch (Exception e) {
                System.out.println("Error: Debes introducir un número entero.\nIngresa un numero: ");
                sc.next();
            }
        } while (true);
    }


    /**
     * Muestra un mensaje de advertencia en la consola y espera a que el usuario presione Enter para continuar.
     * 
     * @param mensaje El mensaje de advertencia que se mostrará.
     */
    public static void showWarning(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("************* AVISO *************");
        System.out.println(mensaje);
        System.out.println("*************************************\nPresiona Enter para continuar...");
        scanner.nextLine();
    }



    

}
