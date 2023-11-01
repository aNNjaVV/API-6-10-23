package com.cibertec.QuickSale.model.dto;

import com.cibertec.QuickSale.model.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;

@Data
@ToString
@Builder
public class EventDto implements Serializable {
    private int idEvent;

    @NotEmpty
    private String title;

    @NotEmpty
    private String description;
    private Date dateEvent;

    @NotEmpty
    private String image;

    @NotEmpty
    private String place;

    @NotNull
    private int ticketsQuantity;

    @NotNull
    private double unitPrice;
    private String status;
    private Category category;

    public String getStatus() {
        return "Activo";
    }


}
