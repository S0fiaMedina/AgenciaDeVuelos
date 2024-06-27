package com.agenciavuelos.views.application;

import com.agenciavuelos.views.infrastructure.ViewRepository;

public class ViewService {
    private final ViewRepository viewRepository;

    public ViewService(ViewRepository viewRepository) {
        this.viewRepository = viewRepository;
    }

    public void runPage(){
        this.viewRepository.runPage();
    }


    // EN LOS ADAPTADORES SE PASAN A LOS MENUS SEGUN CORRESPONDA
}
