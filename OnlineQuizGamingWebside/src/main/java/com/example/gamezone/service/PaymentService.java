
package com.example.gamezone.service;

import com.example.gamezone.entity.Payment;
import com.example.gamezone.repository.PaymentRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAllByOrderByPaymentDateDesc();
    }

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(
                "Payment not found: " + id
            ));
    }

    public boolean hasPaidForGame(String email, String gameName) {
        if (email == null || email.isBlank()) {
            return false;
        }

        return paymentRepository
            .existsByCustomerEmailAndGameNameAndStatus(
                email, gameName, "PAID"
            );
    }
}