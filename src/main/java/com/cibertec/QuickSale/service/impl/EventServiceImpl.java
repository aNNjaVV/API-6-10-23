package com.cibertec.QuickSale.service.impl;

import java.util.List;
import java.util.Optional;

import com.cibertec.QuickSale.model.Category;
import com.cibertec.QuickSale.model.dto.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.QuickSale.model.Event;
import com.cibertec.QuickSale.repo.IEventRepo;
import com.cibertec.QuickSale.servic.IEventService;

@Service
public class EventServiceImpl implements IEventService {

	@Autowired
	IEventRepo repo;
	
	@Override
	public Event registrar(EventDto e) {
		Event event = Event.builder()
				.idEvent(e.getIdEvent())
				.title(e.getTitle())
				.description(e.getDescription())
				.dateEvent(e.getDateEvent())
				.image(e.getImage())
				.place(e.getPlace())
				.ticketsQuantity(e.getTicketsQuantity())
				.unitPrice(e.getUnitPrice())
				.status(e.getStatus())
				.category(e.getCategory())
				.build();

		return repo.save(event);
	}

	@Override
	public Event modificar(EventDto e) {
		Event event = Event.builder()
				.idEvent(e.getIdEvent())
				.title(e.getTitle())
				.description(e.getDescription())
				.dateEvent(e.getDateEvent())
				.image(e.getImage())
				.place(e.getPlace())
				.ticketsQuantity(e.getTicketsQuantity())
				.unitPrice(e.getUnitPrice())
				.status(e.getStatus())
				.category(e.getCategory())
				.build();

		return repo.save(event);
	}

	@Override
	public List<Event> listar() {
		return repo.findAll();
	}

	@Override
	public Event listarPorId(Integer id) {
		return repo.findById(id).get();
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public void cambiarEstadoAEliminado(Integer id) {
		Optional<Event> optionalCategory = repo.findById(id);
		if (optionalCategory.isPresent()) {
			Event category = optionalCategory.get();
			category.setStatus("Eliminado");
			repo.save(category);
		}
	}

	@Override
	public  List<Event> findEventByName(String title) {
		return repo.findEventByName(title);
	}

	@Override
	public  List<Event> filterEventByCategory(Integer idCategory) {
		return repo.filterEventByCategory(idCategory);
	}


}
