package com.cibertec.QuickSale.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Watson")
public class Watson {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idWatson;
	private int quantity  ;
	private String nameEvent  ;
	private String emailCustomer   ;
	private String payment  ;



}
