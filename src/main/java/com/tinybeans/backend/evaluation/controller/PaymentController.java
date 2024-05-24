package com.tinybeans.backend.evaluation.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.tinybeans.backend.evaluation.data.entity.Item;
import com.tinybeans.backend.evaluation.data.entity.Orders;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import com.tinybeans.backend.evaluation.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private PaymentService paymentService;
    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("")
    public String getPayment() {
        return "payment";
    }

    @PostMapping("/create-payment-intent")
    public String createPaymentIntent(@RequestBody Map<String, Object> data) throws StripeException {
        double amount = (double) data.get("amount");
        String clientSecret = paymentService.createPaymentIntent(amount);
        return clientSecret;
    }

    @PostMapping("/save-order")
    public Orders saveOrder(@RequestBody Map<String, Object> data) {
        List<Map<String, Object>> products = (List<Map<String, Object>>) data.get("products");
        List<Item> items = new ArrayList<>();

        for (Map<String, Object> product : products) {
            Item item = new Item();
            item.setId((Integer) product.get("id"));
            item.setName((String) product.get("name"));
            item.setDescription((String) product.get("description"));
            item.setPhotoUrl((String) product.get("photoUrl"));
            item.setPrice((Double) product.get("price"));
            items.add(item);
        }

        String paymentId = (String) data.get("paymentRef");
        Orders order = paymentService.saveOrder(items, paymentId);
        return order;
    }
}
