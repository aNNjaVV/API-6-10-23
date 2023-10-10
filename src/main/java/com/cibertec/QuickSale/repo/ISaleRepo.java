package com.cibertec.QuickSale.repo;

import com.cibertec.QuickSale.model.Customer;
import com.cibertec.QuickSale.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.QuickSale.model.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ISaleRepo extends JpaRepository<Sale, Integer> {

    @Query("SELECT e FROM Sale e WHERE e.saleDate BETWEEN :dateStart AND :dateFin ")
     //List<Sale> findByDateRange(@Param("dateStart") Date dateStart, @Param("dateFin") Date dateFin);
    List<Sale> findByDateRange(@Param("dateStart") LocalDate dateStart, @Param("dateFin") LocalDate dateFin);


    //@Query("select e from Sale e join fetch e.customer join fetch e.payment join fetch e.event")
     //List<Sale> findFullObj();


    @Query("SELECT v FROM Sale v WHERE v.customer.email = :email")
    List<Sale> findSaleByEmailUser(@Param("email") String email);


}
