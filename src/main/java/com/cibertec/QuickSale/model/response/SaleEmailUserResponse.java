package com.cibertec.QuickSale.model.response;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class SaleEmailUserResponse {
    private String operationNumber;
    private String title;
    private int quantity;
    private double total;
    private Date saleDate;
    private Date dateEvent;
}
