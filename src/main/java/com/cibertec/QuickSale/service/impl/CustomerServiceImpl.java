package com.cibertec.QuickSale.service.impl;

import java.util.List;
import java.util.Optional;

import com.cibertec.QuickSale.model.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.QuickSale.model.Customer;
import com.cibertec.QuickSale.repo.ICustomerRepo;
import com.cibertec.QuickSale.servic.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	ICustomerRepo repo;

	@Override
	public Customer registrar(CustomerDto cu) {
		Customer customer = Customer.builder()
				.idCustomer(cu.getIdCustomer())
				.fullName(cu.getFullName())
				.dni(cu.getDni())
				.age(cu.getAge())
				.email(cu.getEmail())
				.password(cu.getPassword())
				.roles(cu.getRoles())
				.status(cu.getStatus())
				.build();

		return repo.save(customer);
	}

	@Override
	public Customer modificar(CustomerDto cu) {
		Customer customer = Customer.builder()
				.idCustomer(cu.getIdCustomer())
				.fullName(cu.getFullName())
				.dni(cu.getDni())
				.age(cu.getAge())
				.email(cu.getEmail())
				.password(cu.getPassword())
				.roles(cu.getRoles())
				.status(cu.getStatus())
				.build();

		return repo.save(customer);
	}

	@Override
	public List<Customer> listar() {
		return repo.findAll();
	}

	@Override
	public Customer listarPorId(Integer id) {
		return repo.findById(id).get();
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);
	}


	@Override
	public void cambiarEstadoAEliminado(Integer id) {
		Optional<Customer> optionalCustomer= repo.findById(id);
		if (optionalCustomer.isPresent()) {
			Customer customer = optionalCustomer.get();
			customer.setStatus("Eliminado");
			repo.save(customer);
		}
	}

	@Override
	public Customer login(String email, String password) {
		return repo.login(email,password);
	}

	@Override
	public Customer findByEmail(String email) {
		return repo.findByEmail(email);
	}

	@Override
	public boolean changePassword(String email, String password, String nuevaContraseña) {
		Customer customer = repo.findByEmail(email);

		if (customer != null && customer.getPassword().equals(password)) {
			customer.setPassword(nuevaContraseña);
			repo.save(customer);
			return true;
		} else {
			return false;
		}
	}


}
