package com.cibertec.QuickSale.service.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.cibertec.QuickSale.model.CodeGenerator;
import com.cibertec.QuickSale.model.Event;
import com.cibertec.QuickSale.model.dto.SaleDto;
import com.cibertec.QuickSale.repo.IEventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.QuickSale.model.Sale;
import com.cibertec.QuickSale.repo.ISaleRepo;
import com.cibertec.QuickSale.servic.ISaleService;

@Service
public class SaleServiceImpl implements ISaleService {

	@Autowired
	ISaleRepo repo;

	@Autowired
	private IEventRepo eventRepo;

	@Override
	public Sale registrar(SaleDto s) {
		Sale sale = Sale.builder()
				.idSale(s.getIdSale())
				.saleDate(s.getSaleDate())
				.operationNumber(CodeGenerator.generateCode())
				.total(s.getTotal())
				.quantity(s.getQuantity())
				.status(s.getStatus())
				.event(s.getEvent())
				.customer(s.getCustomer())
				.payment(s.getPayment())
				.build();

		return repo.save(sale);
	}

	@Override
	public Sale modificar(SaleDto s) {
		Sale sale = Sale.builder()
				.idSale(s.getIdSale())
				.saleDate(s.getSaleDate())
				.operationNumber(CodeGenerator.generateCode())
				.total(s.getTotal())
				.quantity(s.getQuantity())
				.status(s.getStatus())
				.event(s.getEvent())
				.customer(s.getCustomer())
				.payment(s.getPayment())
				.build();

		return repo.save(sale);
	}

	@Override
	public List<Sale> listar() {
		return repo.findAll();
	}

	@Override
	public Sale listarPorId(Integer id) {
		return repo.findById(id).get();
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);
	}


	@Override
	public void cambiarEstadoAEliminado(Integer id) {
		Optional<Sale> optionalCategory = repo.findById(id);
		if (optionalCategory.isPresent()) {
			Sale category = optionalCategory.get();
			category.setStatus("Eliminado");
			repo.save(category);
		}
	}

	@Override
	public List<Sale> findByDateRange(LocalDate dateStart, LocalDate dateFin) {
		return repo.findByDateRange(dateStart,dateFin);
	}

	@Override
	public List<Sale> findSaleByEmailUser(String email) {
		return repo.findSaleByEmailUser(email);
	}




}
