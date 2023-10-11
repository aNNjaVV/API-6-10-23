package com.cibertec.QuickSale.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class WatsonDto implements Serializable {

    private int idWatson;
    private int quantity  ;
    private String nameEvent  ;
    private String emailCustomer   ;
    private String payment  ;

}
