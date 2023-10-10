package com.cibertec.QuickSale.servic;

import java.util.List;

import com.cibertec.QuickSale.model.dto.EventDto;
import org.springframework.stereotype.Service;

import com.cibertec.QuickSale.model.Event;

@Service
public interface IEventService {

	Event registrar(EventDto e);
	Event modificar(EventDto e);
	List<Event> listar();
	Event listarPorId(Integer id);
	void eliminar(Integer id);

	void cambiarEstadoAEliminado(Integer id);

	List<Event> findEventByName(String title);

	List<Event> filterEventByCategory(Integer idCategory);
}
