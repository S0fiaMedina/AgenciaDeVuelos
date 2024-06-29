package com.agenciavuelos.modules.tripBooking.application;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.tripBooking.domain.TripBooking;
import com.agenciavuelos.modules.customer.domain.Customer;
import com.agenciavuelos.modules.customer.infrastructure.CustomerRepository;
import com.agenciavuelos.modules.documentType.domain.DocumentType;
import com.agenciavuelos.modules.documentType.infrastructure.DocumentTypeRepository;
import com.agenciavuelos.modules.flightFare.domain.FlightFare;
import com.agenciavuelos.modules.flightFare.infrastructure.FlightFareRepository;
import com.agenciavuelos.modules.payment.domain.Payment;
import com.agenciavuelos.modules.payment.infrastructure.PaymentRepository;
import com.agenciavuelos.modules.paymentForm.domain.PaymentForm;
import com.agenciavuelos.modules.paymentForm.infrastructure.PaymentFormRepository;
import com.agenciavuelos.modules.plane.domain.Plane;
import com.agenciavuelos.modules.plane.infrastructure.PlaneRepository;
import com.agenciavuelos.modules.trip.domain.Trip;
import com.agenciavuelos.modules.trip.infrastructure.TripRepository;
import com.agenciavuelos.modules.tripBooking.infrastructure.TripBookingRepository;
import com.agenciavuelos.modules.tripBookingDetail.domain.TripBookingDetail;
import com.agenciavuelos.modules.tripBookingDetail.infrastructure.TripBookingDetailRepository;

public class TripBookingService {
    private final TripRepository tripRepository;
    private final PlaneRepository planeRepository;
    private final TripBookingDetailRepository tripBookingDetailRepository;
    private final TripBookingRepository tripBookingRepository;
    private final CustomerRepository customerRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final FlightFareRepository flightFareRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentFormRepository paymentFormRepository;

    public TripBookingService(TripRepository tripRepository, PlaneRepository planeRepository, TripBookingDetailRepository tripBookingDetailRepository, TripBookingRepository tripBookingRepository, CustomerRepository customerRepository, DocumentTypeRepository documentTypeRepository, FlightFareRepository flightFareRepository, PaymentRepository paymentRepository, PaymentFormRepository paymentFormRepository) {
        this.tripRepository = tripRepository;
        this.planeRepository = planeRepository;
        this.tripBookingDetailRepository = tripBookingDetailRepository;
        this.tripBookingRepository = tripBookingRepository;
        this.customerRepository = customerRepository;
        this.documentTypeRepository = documentTypeRepository;
        this.flightFareRepository = flightFareRepository;
        this.paymentRepository = paymentRepository;
        this.paymentFormRepository = paymentFormRepository;
    }

    public List<Trip> searchTrips(String nameCityD, String nameCityA, String departureDate) {
        return tripRepository.searchTrips(nameCityD, nameCityA, departureDate);
    }

    public Optional<Trip> findTripById(int id) {
        Optional<Trip> optionalTrip = this.tripRepository.findById(id);
        return optionalTrip;
    }

    public List<TripBooking> findAllTripBookings(){
        return this.tripBookingRepository.findAll();
    }

    public List<Integer> findSeatNumbers(int idTrip) {
        return this.tripBookingDetailRepository.findSeatNumbers(idTrip);
    }

    public Optional<TripBooking>  findTripBookingById(int id) {
        Optional<TripBooking> optionalTripBooking = this.tripBookingRepository.findById(id);
        return optionalTripBooking;
    }

    public Optional<TripBooking> findTripBookingOfCustomer(int idCustomer, int idTripBooking){
        return this.tripBookingRepository.findTripBookingOfCustomer(idCustomer, idTripBooking);
    }

    public void deleteTripBooking(int id){
        this.tripBookingRepository.delete(id);
    }

    public void updateTripBooking(TripBooking tripBooking){
        this.tripBookingRepository.update(tripBooking);
    }

    public int createTripBooking(TripBooking tripBooking){
        return this.tripBookingRepository.save(tripBooking);
    }

    public List<TripBooking> getTripBookingByCustomer(int id) {
        return this.tripBookingRepository.findByCustomerId(id);
    }

    public List<TripBooking> getTripBookingByTrip(int id) {
        return this.tripBookingRepository.findByTripId(id);
    }

    public List<TripBooking> findBookingById(int id) {
        return this.tripBookingRepository.findBookingById(id);
    }

    public List<Integer> findBookingsByCustomerId(int id) {
        return this.tripBookingRepository.findBookingsByCustomerId(id);
    }

    public List<Trip> findAllTrips() {
        return this.tripRepository.findAll();
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findByDocumentNumber(int docNumber) {
        return customerRepository.findByDocumentNumber(docNumber);
    }

    public void createTripBookingDetail(TripBookingDetail tripBookingDetail) {
        tripBookingDetailRepository.save(tripBookingDetail);
    }

    public List<FlightFare> findAllFlightFares() {
        return flightFareRepository.findAll();
    }

    public List<DocumentType> findAllDocumentTypes(){
        return this.documentTypeRepository.findAll();
    }

    public int verifyDocumentNumber(int number){
        return this.customerRepository.verifyDocumentNumber(number);
    }

    public int createCustomer(Customer documentType){
        return this.customerRepository.save(documentType);
    }

    public Optional<Plane> findByTrip(int idTrip) {
        return this.planeRepository.findByTrip(idTrip);
    }

    public void createPayment(Payment payment){
        this.paymentRepository.save(payment);
    }

    public List<PaymentForm> findAllPaymentForms(){
        return paymentFormRepository.findAll();
    }

    public Optional<PaymentForm>  findPaymentFormById(int id) {
        Optional<PaymentForm> optionalPaymentForm = this.paymentFormRepository.findById(id);
        return optionalPaymentForm;
    }

    public int getDocumentTypeId(int id){
        int foundId = -1;
        Optional<DocumentType> foundDocumentType = documentTypeRepository.findById(id);
        if (foundDocumentType.isPresent()) {
            DocumentType documentType = foundDocumentType.get();
            foundId = documentType.getId();
        }
        return foundId;
    }

    public int getTripId(int id) {
        int idF = -1;
        Optional<Trip> foundTrip = tripRepository.findById(id);
        if (foundTrip.isPresent()) {
            Trip trip = foundTrip.get();
            idF = trip.getId();
        }
        return idF;
    }

    public int getCustomerId(int id) {
        int idF = -1;
        Optional<Customer> foundCustomer = customerRepository.findById(id);
        if (foundCustomer.isPresent()) {
            Customer customer = foundCustomer.get();
            idF = customer.getId();
        }
        return idF;
    }

    public int getFlightFareId(int id) {
        int idF = -1;
        Optional<FlightFare> foundFlightFare = flightFareRepository.findById(id);
        if (foundFlightFare.isPresent()) {
            FlightFare flightFare = foundFlightFare.get();
            idF = flightFare.getId();
        }
        return idF;
    }
}