package com.tinybeans.backend.evaluation.service;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;
import com.tinybeans.backend.evaluation.data.entity.Item;
import com.tinybeans.backend.evaluation.data.entity.Orders;
import com.tinybeans.backend.evaluation.data.repository.PaymentRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        Stripe.apiKey = "sk_test_51PJSo6P4V1jTXpjXPQJCOlbfi90djFsvmLFFHBmz8mt3hkefUZHRZTAmP2FeBd6vLBJalslJ57bfGwVyxDW1uaoe00yZ61sr5B";
        this.paymentRepository = paymentRepository;
    }

    public String createPaymentIntent(double amount) throws StripeException {
        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        Map<String, Object> params = new HashMap<>();
        params.put("amount", (int)(amount * 100));
        params.put("currency", "usd");
        params.put("payment_method_types", paymentMethodTypes);

        PaymentIntent paymentIntent = PaymentIntent.create(params);

        return paymentIntent.getClientSecret();
    }

    public Orders saveOrder(List<Item> items, String paymentId) {
        Orders order = new Orders();
        order.setItems(items);
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Item item : items) {
            totalPrice = totalPrice.add(BigDecimal.valueOf(item.getPrice()));
        }
        order.setSubTotal(totalPrice);
        order.setPaymentId(paymentId);
        Orders savedOrder = paymentRepository.save(order);  // Save the order and get the saved entity
        return savedOrder;
    }
}