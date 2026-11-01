package com.example.gamezone.controller;

import com.example.gamezone.entity.Payment;
import com.example.gamezone.service.PaymentService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.UUID;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Payment History
    @GetMapping
    public String paymentList(Model model) {
        model.addAttribute("payments",
                paymentService.getAllPayments());
        return "payment-list";
    }

    // View Bill
    @GetMapping("/bill/{id}")
    public String viewBill(@PathVariable Long id, Model model) {
        Payment payment = paymentService.getPaymentById(id);

        if (payment == null) {
            return "redirect:/payments";
        }

        model.addAttribute("payment", payment);
        return "bill";
    }

    // Demo Payment Page
    @GetMapping("/demo")
    public String demoPayment(
            @RequestParam String gameName,
            HttpSession session,
            Model model) {

        String username = (String) session.getAttribute("username");
        String email = (String) session.getAttribute("email");

        if (username == null || email == null) {
            return "redirect:/login";
        }

        if (!isPaidGame(gameName)) {
            return "redirect:/home";
        }

        if (paymentService.hasPaidForGame(email, gameName)) {
            return "redirect:" + getGameUrl(gameName);
        }

        model.addAttribute("gameName", gameName);
        model.addAttribute("amount", new BigDecimal("10.00"));

        return "demo-payment";
    }

    // Confirm Demo Payment
    @PostMapping("/demo/confirm")
    public String confirmDemoPayment(
            @RequestParam String gameName,
            HttpSession session) {

        String username = (String) session.getAttribute("username");
        String email = (String) session.getAttribute("email");

        if (username == null || email == null) {
            return "redirect:/login";
        }

        if (!isPaidGame(gameName)) {
            return "redirect:/home";
        }

        // Check existing payment
        if (paymentService.hasPaidForGame(email, gameName)) {
            return "redirect:" + getGameUrl(gameName);
        }

        // Save Demo Payment
        Payment payment = new Payment();

        payment.setCustomerName(username);
        payment.setCustomerEmail(email);
        payment.setGameName(gameName);
        payment.setAmount(new BigDecimal("10.00"));
        payment.setPaymentMethod("DEMO");
        payment.setTransactionId("DEMO-" + UUID.randomUUID());
        payment.setStatus("PAID");

        paymentService.savePayment(payment);

        return "redirect:" + getGameUrl(gameName);
    }

    // Only these two games are paid
    private boolean isPaidGame(String gameName) {
        return "Quiz".equals(gameName)
                || "Rock Paper Scissors".equals(gameName);
    }

    // Game URL
    private String getGameUrl(String gameName) {
        if ("Quiz".equals(gameName)) {
            return "/quiz";
        }

        if ("Rock Paper Scissors".equals(gameName)) {
            return "/rock-paper-scissors";
        }

        return "/home";
    }
}