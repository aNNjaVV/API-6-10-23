package com.cibertec.QuickSale.model.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CustomerResponse implements Serializable {
    private String mensaje;
    private String name;
    private boolean success;
    private String roles;
}
