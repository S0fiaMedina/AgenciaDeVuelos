package com.agenciavuelos.Console.view.domain;

import com.agenciavuelos.Console.Initializer;
import com.agenciavuelos.modules.revision.adapter.in.RevisionConsoleAdapter;

public class Technician {
    private final RevisionConsoleAdapter revisionConsoleAdapter;
    

    public Technician(Initializer initializer){
        this.revisionConsoleAdapter = initializer.startRevisionModule(); 
    }


    public RevisionConsoleAdapter getRevisionConsoleAdapter() {
        return revisionConsoleAdapter;
    }
}
