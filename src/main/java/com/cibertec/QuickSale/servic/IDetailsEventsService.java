package com.cibertec.QuickSale.servic;

import java.util.List;

import com.cibertec.QuickSale.model.dto.DetailsEventsDto;
import org.springframework.stereotype.Service;

import com.cibertec.QuickSale.model.DetailsEvents;

@Service
public interface IDetailsEventsService {

	DetailsEvents registrar(DetailsEventsDto de);
	DetailsEvents modificar(DetailsEventsDto de);
	List<DetailsEvents> listar();
	DetailsEvents listarPorId(Integer id);
	void eliminar(Integer id);

	void cambiarEstadoAEliminado(Integer id);
}
