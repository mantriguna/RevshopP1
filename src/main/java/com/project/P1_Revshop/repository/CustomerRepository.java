package com.project.P1_Revshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.P1_Revshop.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    // You can add custom queries if needed
}
