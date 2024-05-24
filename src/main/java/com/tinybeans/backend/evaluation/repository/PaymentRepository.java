package com.tinybeans.backend.evaluation.data.repository;

import com.tinybeans.backend.evaluation.data.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Orders, Long> {
    // You can define custom query methods if needed
}
