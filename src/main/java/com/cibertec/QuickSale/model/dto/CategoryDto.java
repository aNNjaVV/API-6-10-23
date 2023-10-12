package com.cibertec.QuickSale.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class CategoryDto implements Serializable {

    private int idCategory;
    private String description;
    private String status;

    public String getStatus() {
        return "Activo";
    }
}
