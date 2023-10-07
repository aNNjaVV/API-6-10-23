package com.cibertec.QuickSale.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.QuickSale.model.Payment;

public interface IPaymentRepo extends JpaRepository<Payment, Integer> {

}
