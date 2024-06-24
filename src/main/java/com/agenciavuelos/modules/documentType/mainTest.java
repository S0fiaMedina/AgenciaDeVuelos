package com.agenciavuelos.modules.documentType;

import com.agenciavuelos.Console.Initializer;
import com.agenciavuelos.modules.documentType.adapter.in.DocumentTypeConsoleAdapter;

public class mainTest {
    public static void main(String[] args) {
        String user = "campus2023";
        String url = "jdbc:mysql://localhost:3306/AGENCIA_VUELOS";
        String password = "campus2023";
        Initializer initializer = new Initializer(url, user, password);
        DocumentTypeConsoleAdapter  documentTypeConsoleAdapter= initializer.startDocumentTypeModule();
        documentTypeConsoleAdapter.run();

    }
}
