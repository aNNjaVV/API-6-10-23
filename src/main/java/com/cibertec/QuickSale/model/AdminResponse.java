package com.cibertec.QuickSale.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class AdminResponse implements Serializable {

    private int id;
    private String mensaje;
    private String name;
    private boolean success;
    private String roles;
}
