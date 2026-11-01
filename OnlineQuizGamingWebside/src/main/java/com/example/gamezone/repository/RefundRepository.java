
package com.example.gamezone.repository;

import com.example.gamezone.entity.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RefundRepository extends JpaRepository<Refund, Long> {

    List<Refund> findByCustomerEmailOrderByRequestedAtDesc(
        String customerEmail
    );

    List<Refund> findAllByOrderByRequestedAtDesc();
}
