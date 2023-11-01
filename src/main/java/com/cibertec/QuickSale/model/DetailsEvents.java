package com.cibertec.QuickSale.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "detailsEvents")
public class DetailsEvents {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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



	//Esto suele no funcionar
	//si no probar el
	//@OneToOne(cascade = CascadeType.ALL)
	//o
	//@ManyToOne(fetch = FetchType.EAGER)
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_Event", referencedColumnName = "idEvent")
	private Event event;



}
