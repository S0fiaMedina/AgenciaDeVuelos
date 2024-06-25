package com.agenciavuelos.modules.status;

import com.agenciavuelos.Console.Initializer;
import com.agenciavuelos.modules.status.adapter.in.StatusConsoleAdapter;

public class MainTestAgain {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/AGENCIA_VUELOS";
        String user = "root";
        String password = "R00T_12345";
        Initializer initializer = new Initializer(url, user, password);

        StatusConsoleAdapter statusConsoleAdapter = initializer.startConsoleAdapter();
        statusConsoleAdapter.run();

    }
}
