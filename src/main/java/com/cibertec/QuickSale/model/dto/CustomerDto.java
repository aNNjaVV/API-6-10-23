package com.cibertec.QuickSale.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class CustomerDto implements Serializable {
    private int idCustomer;
    private String fullName;
    private String dni;
    private int age;
    private String email;
    private String password;
    private String status;
    private String roles;




}
