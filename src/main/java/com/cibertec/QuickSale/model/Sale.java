package com.cibertec.QuickSale.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sale")
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSale;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date saleDate;
	private String operationNumber;
	private double total;
	private int quantity;
	private String status;
	
	//@ManyToOne(cascade = CascadeType.ALL)
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_event", referencedColumnName = "idEvent")
	private Event event;

	//@OneToOne(cascade = CascadeType.ALL)
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_customer", referencedColumnName = "idCustomer")
	private Customer customer;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_payment", referencedColumnName = "idPayment")
		private Payment payment;


	public void setIdSale(int idSale) {
		this.idSale = idSale;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public void setOperationNumber(String operationNumber) {
		this.operationNumber = operationNumber;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
}
