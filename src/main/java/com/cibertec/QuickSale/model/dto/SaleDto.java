package com.cibertec.QuickSale.model.dto;

import com.cibertec.QuickSale.model.Customer;
import com.cibertec.QuickSale.model.Event;
import com.cibertec.QuickSale.model.Payment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.PrintStream;
import java.io.Serializable;
import java.sql.Date;

@Data
@ToString
@Builder
public class SaleDto implements Serializable {

    private int idSale;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date saleDate;

    private String operationNumber;
    private double total;
    private int quantity;
    private String status;
    private Event event;
    private Customer customer;
    private Payment payment;


}