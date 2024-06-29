package com.agenciavuelos.modules.payment.infrastructure;

import java.util.List;
import java.util.Optional;

import com.agenciavuelos.modules.payment.domain.Payment;

public interface PaymentRepository {
    public Optional<Payment> findById(int id);

    public List<Payment> findAll();

    public void save(Payment payment);

    public void update(Payment payment);

    public void delete(int id);
}