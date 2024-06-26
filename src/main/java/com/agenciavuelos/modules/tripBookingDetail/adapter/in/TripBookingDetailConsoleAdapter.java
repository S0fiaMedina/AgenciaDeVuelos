package com.agenciavuelos.modules.tripBookingDetail.adapter.in;

import com.agenciavuelos.modules.tripBookingDetail.application.TripBookingDetailService;
import com.agenciavuelos.modules.tripBookingDetail.domain.TripBookingDetail;

public class TripBookingDetailConsoleAdapter {
    private final TripBookingDetailService tripBookingDetailService;

    public TripBookingDetailConsoleAdapter(TripBookingDetailService tripBookingDetailService) {
        this.tripBookingDetailService = tripBookingDetailService;
    }
}