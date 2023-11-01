package com.cibertec.QuickSale.model;

import java.sql.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
	
	//@ManyToOne(cascade = CascadeType.ALL)
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_category", referencedColumnName = "idCategory")
	private Category category;



}
