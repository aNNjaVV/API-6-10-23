package com.cibertec.QuickSale.model;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEvent;
	
	private String title;
	private String description;
	private Date dateEvent;
	private String image;
	private String place;
	private int ticketsQuantity;
	private double unitPrice;
	private String status;
	
	//@ManyToOne(cascade = CascadeType.ALL)
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_category", referencedColumnName = "idCategory")
	private Category category;



}
