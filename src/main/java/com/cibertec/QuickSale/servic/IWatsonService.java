package com.cibertec.QuickSale.servic;

import com.cibertec.QuickSale.model.Category;
import com.cibertec.QuickSale.model.Watson;
import com.cibertec.QuickSale.model.dto.CategoryDto;
import com.cibertec.QuickSale.model.dto.WatsonDto;

import java.util.List;

public interface IWatsonService {

	Watson registrar(WatsonDto c);
	Watson modificar(WatsonDto c);
	List<Watson> listar();
	Watson listarPorId(Integer id);
	void eliminar(Integer id);
	//void cambiarEstadoAEliminado(Integer id);
}
