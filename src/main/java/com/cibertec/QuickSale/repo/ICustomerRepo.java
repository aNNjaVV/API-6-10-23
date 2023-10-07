package com.cibertec.QuickSale.repo;

import com.cibertec.QuickSale.model.dto.CustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.QuickSale.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICustomerRepo extends JpaRepository<Customer, Integer> {

    @Query("SELECT u FROM Customer u WHERE u.email = :email AND u.password = :password")
    Customer login(@Param("email") String email, @Param("password") String password);



}
