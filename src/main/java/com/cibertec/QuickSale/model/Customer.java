package com.cibertec.QuickSale.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCustomer;

	@NotEmpty
	private String fullName;

	@NotEmpty
	private String dni;

	@NotNull
	private int age;

	@NotEmpty
	private String email;

	@NotEmpty
	private String password;

	@NotEmpty
	private String roles;
	private String status;

}
