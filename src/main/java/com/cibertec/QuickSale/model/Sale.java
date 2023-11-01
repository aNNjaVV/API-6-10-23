package com.cibertec.QuickSale.model;

import java.sql.Date;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sale")
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSale;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate saleDate;

	@NotEmpty
	private String operationNumber;

	@NotNull
	private double total;

	@NotNull
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

	public double getTotal() {
		return event.getUnitPrice()*quantity;
	}



}
