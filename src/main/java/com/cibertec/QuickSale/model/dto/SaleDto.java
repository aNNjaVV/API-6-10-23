package com.cibertec.QuickSale.model.dto;

import com.cibertec.QuickSale.model.Customer;
import com.cibertec.QuickSale.model.Event;
import com.cibertec.QuickSale.model.Payment;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.PrintStream;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@Data
@ToString
@Builder
public class SaleDto implements Serializable {

    private int idSale;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate saleDate;

    @NotEmpty
    private String operationNumber;

    @NotNull
    private double total;

    @NotNull
    private int quantity;
    private String status;
    private Event event;
    private Customer customer;
    private Payment payment;

    public LocalDate getSaleDate() {
        return LocalDate.now();
    }

    public double getTotal() {
        return event.getUnitPrice()*quantity;
    }

    public String getStatus() {
        return "Activo";
    }
}
