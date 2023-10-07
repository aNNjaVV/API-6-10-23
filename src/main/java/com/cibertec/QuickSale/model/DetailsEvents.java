package com.cibertec.QuickSale.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
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
	private String link;
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


	public void setIdDetailsEvents(int idDetailsEvents) {
		this.idDetailsEvents = idDetailsEvents;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setUrlImageRef(String urlImageRef) {
		this.urlImageRef = urlImageRef;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public int getIdDetailsEvents() {
		return idDetailsEvents;
	}

	public String getDescription() {
		return description;
	}

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

	public String getComments() {
		return comments;
	}

	public String getUrlImageRef() {
		return urlImageRef;
	}

	public String getStatus() {
		return status;
	}

	public Event getEvent() {
		return event;
	}
}
