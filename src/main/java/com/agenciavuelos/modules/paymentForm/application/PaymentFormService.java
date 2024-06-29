package com.agenciavuelos.modules.paymentForm.application;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.paymentForm.domain.PaymentForm;
import com.agenciavuelos.modules.paymentForm.infrastructure.PaymentFormRepository;

public class PaymentFormService {
    private final PaymentFormRepository paymentFormRepository;

    public PaymentFormService(PaymentFormRepository paymentFormRepository) {
        this.paymentFormRepository = paymentFormRepository;
    }

    public List<PaymentForm> findAllPaymentForms(){
        return paymentFormRepository.findAll();
    }

    public Optional<PaymentForm>  findPaymentFormById(int id) {
        Optional<PaymentForm> optionalPaymentForm = this.paymentFormRepository.findById(id);
        return optionalPaymentForm;
    }

    public void deletePaymentForm(int id){
        this.paymentFormRepository.delete(id);
    }

    public void updatePaymentForm(PaymentForm paymentForm){
        this.paymentFormRepository.update(paymentForm);
    }

    public void createPaymentForm(PaymentForm paymentForm){
        this.paymentFormRepository.save(paymentForm);
    }
}