package com.agenciavuelos.modules.tripBookingDetail.application;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.tripBookingDetail.domain.TripBookingDetail;
import com.agenciavuelos.modules.tripBookingDetail.infrastructure.TripBookingDetailRepository;

public class TripBookingDetailService {
    private TripBookingDetailRepository tripBookingDetailRepository;

    public TripBookingDetailService(TripBookingDetailRepository tripBookingDetailRepository) {
        this.tripBookingDetailRepository = tripBookingDetailRepository;
    }

    public List<TripBookingDetail> findAllTripBookingDetails(){
        return tripBookingDetailRepository.findAll();
    }

    public Optional<TripBookingDetail>  findTripBookingDetailById(int id) {
        Optional<TripBookingDetail> optionalTripBookingDetail = this.tripBookingDetailRepository.findById(id);
        return optionalTripBookingDetail;
    }

    public void deleteTripBookingDetail(int id){
        this.tripBookingDetailRepository.delete(id);
    }

    public void updateTripBookingDetail(TripBookingDetail tripBooking){
        this.tripBookingDetailRepository.update(tripBooking);
    }

    public void createTripBookingDetail(TripBookingDetail tripBooking){
        this.tripBookingDetailRepository.save(tripBooking);
    }

    public Optional<TripBookingDetail>  findTripBookingById(int id) {
        Optional<TripBookingDetail> optionalTripBookingDetail = this.tripBookingDetailRepository.findByTripBookingId(id);
        return optionalTripBookingDetail;
    }
}