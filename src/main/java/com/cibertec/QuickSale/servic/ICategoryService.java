package com.cibertec.QuickSale.servic;

import java.util.List;


import com.cibertec.QuickSale.model.Category;
import com.cibertec.QuickSale.model.dto.CategoryDto;

public interface ICategoryService {

	Category registrar(CategoryDto c);
	Category modificar(CategoryDto c);
	List<Category> listar();
	Category listarPorId(Integer id);
	void eliminar(Integer id);

	void cambiarEstadoAEliminado(Integer id);
}
