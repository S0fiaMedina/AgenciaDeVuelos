package com.agenciavuelos.modules.gate.application;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.airport.domain.Airport;
import com.agenciavuelos.modules.airport.infrastructure.AirportRepository;
import com.agenciavuelos.modules.gate.domain.Gate;
import com.agenciavuelos.modules.gate.infrastructure.GateRepository;

public class GateService {
    private final GateRepository gateRepository;
    private final AirportRepository airportRepository;

    public GateService(GateRepository gateRepository, AirportRepository airportRepository) {
        this.gateRepository = gateRepository;
        this.airportRepository = airportRepository;
    }

    public List<Gate> findAllGates(){
        return gateRepository.findAll();
    }

    public Optional<Gate>  findGateById(int id) {
        Optional<Gate> optionalGate = this.gateRepository.findById(id);
        return optionalGate;

    }

    public List<Airport> findAllAirports() {
        return airportRepository.findAll();
    }

    public void deleteGate(int id){
        this.gateRepository.delete(id);
    }

    public void updateGate(Gate gate){
        this.gateRepository.update(gate);
    }

    public void createGate(Gate gate){
        this.gateRepository.save(gate);
    }

    public String getAirportId(String id) {
        String idF = "";
        Optional<Airport> foundAirport = airportRepository.findById(id);
        if (foundAirport.isPresent()) {
            Airport airport = foundAirport.get();
            idF = airport.getId();
        }
        return idF;
    }
}