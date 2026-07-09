package com.ecommerce.backend.service;

import com.ecommerce.backend.entity.Payment;
import com.ecommerce.backend.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final EmailService emailService;

    public PaymentService(PaymentRepository paymentRepository, EmailService emailService) {
        this.paymentRepository = paymentRepository;
        this.emailService = emailService;
    }

    public Payment makePayment(Payment payment) {

        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentStatus("SUCCESS");

        Payment savedPayment = paymentRepository.save(payment);

        emailService.sendPaymentEmail(
                savedPayment.getOrder().getUser().getEmail(),
                savedPayment.getAmount()
        );

        return savedPayment;
    }

    public List<Payment> getAllPayments(){
        return paymentRepository.findAll();
    }

    public Payment getPayment(Long id){
        return paymentRepository.findById(id).orElseThrow(()->new RuntimeException("Payment Not Found"));
    }
}
