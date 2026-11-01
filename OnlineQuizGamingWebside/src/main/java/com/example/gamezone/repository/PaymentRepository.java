
package com.example.gamezone.repository;

import com.example.gamezone.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByCustomerEmailOrderByPaymentDateDesc(
        String customerEmail
    );

    List<Payment> findAllByOrderByPaymentDateDesc();
    boolean existsByCustomerEmailAndGameNameAndStatus(
    	    String customerEmail,
    	    String gameName,
    	    String status
    	);
}
