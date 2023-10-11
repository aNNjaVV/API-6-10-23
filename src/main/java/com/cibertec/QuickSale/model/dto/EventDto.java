package com.cibertec.QuickSale.model.dto;

import com.cibertec.QuickSale.model.Category;
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

    private String title;
    private String description;
    private Date dateEvent;
    private String image;
    private String place;
    private int ticketsQuantity;
    private double unitPrice;
    private String status;
    private Category category;

    public String getStatus() {
        return "Activo";
    }


}
