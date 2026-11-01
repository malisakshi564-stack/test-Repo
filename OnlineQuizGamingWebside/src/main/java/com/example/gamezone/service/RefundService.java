
package com.example.gamezone.service;

import com.example.gamezone.entity.Refund;
import com.example.gamezone.repository.RefundRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RefundService {

    private final RefundRepository refundRepository;

    public RefundService(RefundRepository refundRepository) {
        this.refundRepository = refundRepository;
    }

    public List<Refund> getAllRefunds() {
        return refundRepository.findAllByOrderByRequestedAtDesc();
    }

    public Refund saveRefund(Refund refund) {
        return refundRepository.save(refund);
    }
    public Refund updateRefundStatus(Long id, String status) {
        Refund refund = refundRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Refund not found"));

        if (!status.equals("APPROVED") && !status.equals("REJECTED")) {
            throw new IllegalArgumentException("Invalid refund status");
        }

        refund.setStatus(status);
        return refundRepository.save(refund);
    }
}
