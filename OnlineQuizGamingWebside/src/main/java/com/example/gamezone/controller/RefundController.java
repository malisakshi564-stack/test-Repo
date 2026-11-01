
package com.example.gamezone.controller;

import com.example.gamezone.entity.Payment;
import com.example.gamezone.entity.Refund;
import com.example.gamezone.service.PaymentService;
import com.example.gamezone.service.RefundService;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/refunds")
public class RefundController {

    private final PaymentService paymentService;
    private final RefundService refundService;

    public RefundController(
            PaymentService paymentService,
            RefundService refundService) {
        this.paymentService = paymentService;
        this.refundService = refundService;
    }

    @GetMapping
    public String refundList(Model model) {
        model.addAttribute(
            "refunds",
            refundService.getAllRefunds()
        );
        return "refund-list";
    }

    @GetMapping("/new")
    public String refundForm(
            @RequestParam Long paymentId,
            Model model) {
        Payment payment = paymentService.getPaymentById(paymentId);
        model.addAttribute("payment", payment);
        return "refund";
    }

    @PostMapping("/submit")
    public String submitRefund(
            @RequestParam Long paymentId,
            @RequestParam BigDecimal refundAmount,
            @RequestParam String reason,
            Model model) {

        try {
            Payment payment =
                paymentService.getPaymentById(paymentId);

            if (!"PAID".equalsIgnoreCase(payment.getStatus())) {
                model.addAttribute(
                    "error",
                    "Refund is allowed only for PAID payments."
                );
                model.addAttribute("payment", payment);
                return "refund";
            }

            if (refundAmount == null
                    || refundAmount.compareTo(BigDecimal.ZERO) <= 0
                    || refundAmount.compareTo(payment.getAmount()) > 0) {
                model.addAttribute(
                    "error",
                    "Enter a valid refund amount."
                );
                model.addAttribute("payment", payment);
                return "refund";
            }

            if (reason == null || reason.isBlank()) {
                model.addAttribute(
                    "error",
                    "Please enter a refund reason."
                );
                model.addAttribute("payment", payment);
                return "refund";
            }

            Refund refund = new Refund();
            refund.setPaymentId(payment.getId());
            refund.setCustomerEmail(payment.getCustomerEmail());
            refund.setRefundAmount(refundAmount);
            refund.setReason(reason.trim());
            refund.setStatus("REQUESTED");

            refundService.saveRefund(refund);

            return "redirect:/refunds?success";

        } catch (IllegalArgumentException ex) {
            model.addAttribute("error", "Payment not found.");
            return "redirect:/payments";
        }
    }
    @PostMapping("/update-status")
    public String updateRefundStatus(
            @RequestParam Long id,
            @RequestParam String status) {

        refundService.updateRefundStatus(id, status);

        return "redirect:/refunds";
    }
}
