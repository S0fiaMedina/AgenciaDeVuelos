package com.agenciavuelos.modules.payment.application;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.payment.domain.Payment;
import com.agenciavuelos.modules.payment.infrastructure.PaymentRepository;

public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> findAllPayment(){
        return paymentRepository.findAll();
    }

    public Optional<Payment>  findPaymentById(int id) {
        Optional<Payment> optionalPayment = this.paymentRepository.findById(id);
        return optionalPayment;
    }

    public void deletePayment(int id){
        this.paymentRepository.delete(id);
    }

    public void updatePayment(Payment payment){
        this.paymentRepository.update(payment);
    }

    public void createPayment(Payment payment){
        this.paymentRepository.save(payment);
    }
}