package com.cibertec.QuickSale.service.impl;

import java.util.List;
import java.util.Optional;

import com.cibertec.QuickSale.model.Category;
import com.cibertec.QuickSale.model.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.QuickSale.model.Payment;
import com.cibertec.QuickSale.repo.IPaymentRepo;
import com.cibertec.QuickSale.servic.IPaymentService;

@Service
public class PaymentServiceImpl implements IPaymentService {

	@Autowired
	IPaymentRepo repo;
	
	@Override
	public Payment registrar(PaymentDto p) {
		Payment payment = Payment.builder()
				.idPayment(p.getIdPayment())
				.name(p.getName())
				.status(p.getStatus())
				.build();

		return repo.save(payment);
	}

	@Override
	public Payment modificar(PaymentDto p) {
		Payment payment = Payment.builder()
				.idPayment(p.getIdPayment())
				.name(p.getName())
				.status(p.getStatus())
				.build();

		return repo.save(payment);
	}

	@Override
	public List<Payment> listar() {
		return repo.findAll();
	}

	@Override
	public Payment listarPorId(Integer id) {
		return repo.findById(id).get();
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);
	}


	@Override
	public void cambiarEstadoAEliminado(Integer id) {
		Optional<Payment> optionalCategory = repo.findById(id);
		if (optionalCategory.isPresent()) {
			Payment category = optionalCategory.get();
			category.setStatus("Eliminado");
			repo.save(category);
		}
	}
}
