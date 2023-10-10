package com.cibertec.QuickSale.model.dto;

import com.cibertec.QuickSale.model.Event;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class DetailsEventsDto implements Serializable {
    private int idDetailsEvents;

    private String description;
    private String title;
    private String comments;
    private String urlImageRef;
    private String status;
    private Event event;


}
