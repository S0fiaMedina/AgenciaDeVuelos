package com.agenciavuelos.modules.tripBookingDetail.infrastructure;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.tripBookingDetail.domain.TripBookingDetail;

public interface TripBookingDetailRepository {
    public Optional<TripBookingDetail> findById(int id);

    public List<TripBookingDetail> findAll();

    public void save(TripBookingDetail tripBookingDetail);

    public void update(TripBookingDetail tripBookingDetail);

    public void delete(int id);
}