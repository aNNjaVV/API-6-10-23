package com.cibertec.QuickSale.service.impl;

import com.cibertec.QuickSale.model.Category;
import com.cibertec.QuickSale.model.Watson;
import com.cibertec.QuickSale.model.dto.CategoryDto;
import com.cibertec.QuickSale.model.dto.WatsonDto;
import com.cibertec.QuickSale.repo.ICategoryRepo;
import com.cibertec.QuickSale.repo.IWatsonRepo;
import com.cibertec.QuickSale.servic.ICategoryService;
import com.cibertec.QuickSale.servic.IWatsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WatsonServiceImpl implements IWatsonService {

	@Autowired
	IWatsonRepo repo;
	
	@Override
	public Watson registrar(WatsonDto c) {
		Watson watson = Watson.builder()
				.idWatson(c.getIdWatson())
				.quantity(c.getQuantity())
				.nameEvent(c.getNameEvent())
				.emailCustomer(c.getEmailCustomer())
				.payment(c.getPayment())
				.build();

		return repo.save(watson);
	}

	@Override
	public Watson modificar(WatsonDto c) {
		Watson watson = Watson.builder()
				.idWatson(c.getIdWatson())
				.quantity(c.getQuantity())
				.nameEvent(c.getNameEvent())
				.emailCustomer(c.getEmailCustomer())
				.payment(c.getPayment())
				.build();

		return repo.save(watson);
	}

	@Override
	public List<Watson> listar() {
		return repo.findAll();
	}

	@Override
	public Watson listarPorId(Integer id) {
		return repo.findById(id).get();
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);
	}

}
