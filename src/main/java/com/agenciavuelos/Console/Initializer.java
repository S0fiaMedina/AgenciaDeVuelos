package com.agenciavuelos.Console;

import com.agenciavuelos.modules.customer.adapter.in.CustomerConsoleAdapter;
import com.agenciavuelos.modules.customer.adapter.out.CustomerMySQLRepository;
import com.agenciavuelos.modules.customer.application.CustomerService;
import com.agenciavuelos.modules.customer.infrastructure.CustomerRepository;
import com.agenciavuelos.modules.documentType.adapter.in.DocumentTypeConsoleAdapter;
import com.agenciavuelos.modules.documentType.adapter.out.DocumentTypeMySQLRepository;
import com.agenciavuelos.modules.documentType.application.DocumentTypeService;
import com.agenciavuelos.modules.documentType.infrastructure.DocumentTypeRepository;
import com.agenciavuelos.modules.manufacturer.adapter.in.ManufacturerConsoleAdapter;
import com.agenciavuelos.modules.manufacturer.adapter.out.ManufacturerMySQLRepository;
import com.agenciavuelos.modules.manufacturer.application.ManufacturerService;
import com.agenciavuelos.modules.manufacturer.infrastructure.ManufacturerRepository;


/**
 * Como lo dice su nombre, esta clase sirve para inicializar todos los repositorios necesarios,
 * de esta forma, se separan responsabilidades y se unifica la url, el usuario y la contraseña de mysql
 * en tres variables y no tener que hacerlo que colocarlas explicitamente cada vez que se inicialice algo
*/
public class Initializer {
    private String user;
    private String password;
    private String url;

    

    public Initializer(String url, String user, String password) {
        this.user = user;
        this.password = password;
        this.url = url;
    }



    /**
     * FUNCIONAMIENTO GENERAL:
     * Basicamente, cada funcion inicializa todo lo necesario para que su modulo funcione correctanente,
     * esto quiere decir que inicializa el repositorio, para lueg pasarlo al servicio e inicializarlo
     * y finalmente pasarselo al adaptador de consola y que finalmente el main o los menus solo tengan 
     * que perdirle los adaptadores a esta clase y de esta forma ahorrarle lineas de codigo y asi esa clase de main
     * solo tenga que conocer los adaptadores, nada más
     *
    */


    // FABRICANTES - MANUFACTURADORES
    public ManufacturerConsoleAdapter startManufacturerModule(){
        ManufacturerRepository manufacturerRepository = new ManufacturerMySQLRepository(url, user, password);
        ManufacturerService manufacturerService = new ManufacturerService(manufacturerRepository);
        return new ManufacturerConsoleAdapter(manufacturerService);
    }


    /**
     * RELACIONADOS A CLIENTES 
    */

    // TIPO DE DOCUMENTO 
    public DocumentTypeConsoleAdapter startDocumentTypeModule(){
        DocumentTypeRepository documentTypeRepository = new DocumentTypeMySQLRepository(url, user, password);
        DocumentTypeService documentTypeService = new DocumentTypeService(documentTypeRepository);
        return new DocumentTypeConsoleAdapter(documentTypeService);
    }

    // CUSTOMER 
    public CustomerConsoleAdapter startCustomerModule(){
        DocumentTypeRepository documentTypeRepository = new DocumentTypeMySQLRepository(url, user, password); // re instancia repo de tipos de documentos (XXX: revisar en fututo)
        CustomerRepository customerRepository = new CustomerMySQLRepository(url, user, password);
        CustomerService customerService = new CustomerService(customerRepository, documentTypeRepository);
        return new CustomerConsoleAdapter(customerService);
    }

}
