package com.cibertec.QuickSale.service.impl;

import java.util.List;

import com.cibertec.QuickSale.model.Payment;
import com.cibertec.QuickSale.model.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.QuickSale.model.Category;
import com.cibertec.QuickSale.repo.ICategoryRepo;
import com.cibertec.QuickSale.servic.ICategoryService;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	ICategoryRepo repo;
	
	@Override
	public Category registrar(CategoryDto c) {
		Category category = Category.builder()
				.idCategory(c.getIdCategory())
				.description(c.getDescription())
				.status(c.getStatus())
				.build();

		return repo.save(category);
	}

	@Override
	public Category modificar(CategoryDto c) {
		Category category = Category.builder()
				.idCategory(c.getIdCategory())
				.description(c.getDescription())
				.status(c.getStatus())
				.build();

		return repo.save(category);
	}

	@Override
	public List<Category> listar() {
		return repo.findAll();
	}

	@Override
	public Category listarPorId(Integer id) {
		return repo.findById(id).get();
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);
	}


	@Override
	public void cambiarEstadoAEliminado(Integer id) {
		Optional<Category> optionalCategory = repo.findById(id);
		if (optionalCategory.isPresent()) {
			Category category = optionalCategory.get();
			category.setStatus("Eliminado");
			repo.save(category);
		}
	}

}
