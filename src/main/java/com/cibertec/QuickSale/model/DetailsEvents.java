package com.cibertec.QuickSale.model;

import jakarta.persistence.*;
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
	
	private String description;
	private String title;
	private String comments;
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
