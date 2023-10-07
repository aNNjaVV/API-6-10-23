package com.cibertec.QuickSale.model;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
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


	public int getIdEvent() {
		return idEvent;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Date getDateEvent() {
		return dateEvent;
	}

	public String getImage() {
		return image;
	}

	public String getPlace() {
		return place;
	}

	public int getTicketsQuantity() {
		return ticketsQuantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public String getStatus() {
		return status;
	}

	public Category getCategory() {
		return category;
	}


	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDateEvent(Date dateEvent) {
		this.dateEvent = dateEvent;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public void setTicketsQuantity(int ticketsQuantity) {
		this.ticketsQuantity = ticketsQuantity;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
