package com.cibertec.QuickSale.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class CategoryDto implements Serializable {

    private int idCategory;

    @NotEmpty
    private String description;
    private String status;

    public String getStatus() {
        return "Activo";
    }
}
