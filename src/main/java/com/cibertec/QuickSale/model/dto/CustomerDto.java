package com.cibertec.QuickSale.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class CustomerDto implements Serializable {
    private int idCustomer;

    @NotEmpty
    private String fullName;

    @NotEmpty
    private String dni;

    @NotNull
    private int age;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String status;
    private String roles;

    public String getStatus() {
        return "Activo";
    }




}
