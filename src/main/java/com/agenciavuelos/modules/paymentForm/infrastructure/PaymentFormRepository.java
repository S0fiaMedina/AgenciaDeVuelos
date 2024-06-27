package com.agenciavuelos.modules.paymentForm.infrastructure;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.paymentForm.domain.PaymentForm;

public interface PaymentFormRepository {
    public Optional<PaymentForm> findById(int id);

    public List<PaymentForm> findAll();

    public void save(PaymentForm paymentForm);

    public void update(PaymentForm paymentForm);

    public void delete(int id);
}