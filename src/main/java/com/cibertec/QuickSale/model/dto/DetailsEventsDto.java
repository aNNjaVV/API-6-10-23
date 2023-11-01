package com.cibertec.QuickSale.model.dto;

import com.cibertec.QuickSale.model.Event;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class DetailsEventsDto implements Serializable {
    private int idDetailsEvents;

    @NotEmpty
    private String description;

    @NotEmpty
    private String title;

    @NotEmpty
    private String comments;

    @NotEmpty
    private String urlImageRef;
    private String status;
    private Event event;

    public String getStatus() {
        return "Activo";
    }


}
