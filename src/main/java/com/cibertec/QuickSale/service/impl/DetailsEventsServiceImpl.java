package com.cibertec.QuickSale.service.impl;

import java.util.List;
import java.util.Optional;

import com.cibertec.QuickSale.model.Category;
import com.cibertec.QuickSale.model.dto.DetailsEventsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.QuickSale.model.DetailsEvents;
import com.cibertec.QuickSale.repo.IDetailsEventsRepo;
import com.cibertec.QuickSale.servic.IDetailsEventsService;



import java.util.Base64;
@Service
public class DetailsEventsServiceImpl implements IDetailsEventsService{
	
	@Autowired
	IDetailsEventsRepo repo;

	 @Override
	    public DetailsEvents registrar(DetailsEventsDto de) {
	        // Crear la entidad DetailsEvents y guardarla en la base de datos
	        DetailsEvents detailsEvents = DetailsEvents.builder()
	                .idDetailsEvents(de.getIdDetailsEvents())
	                .description(de.getDescription())
	                .title(de.getTitle())
	                .comments(de.getComments())
	                .urlImageRef(de.getUrlImageRef()) // Asignar la cadena base64 decodificada
	                .status(de.getStatus())
	                .event(de.getEvent())
	                .build();
	        return repo.save(detailsEvents);
	    }

	@Override
	public DetailsEvents modificar(DetailsEventsDto de) {
		DetailsEvents detailsEvents = DetailsEvents.builder()
				.idDetailsEvents(de.getIdDetailsEvents())
				.description(de.getDescription())
				.title(de.getTitle())
				.comments(de.getComments())
				.urlImageRef(de.getUrlImageRef())
				.status(de.getStatus())
				.event(de.getEvent())
				.build();

		return repo.save(detailsEvents);
	}

	@Override
	public List<DetailsEvents> listar() {
		return repo.findAll();
	}

	@Override
	public DetailsEvents listarPorId(Integer id) {
		return repo.findById(id).get();
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public DetailsEvents filterxIdEvent(int idEvent) {
		return repo.filterxIdEvent(idEvent);
	}

	@Override
	public void cambiarEstadoAEliminado(Integer id) {
		Optional<DetailsEvents> optionalCategory = repo.findById(id);
		if (optionalCategory.isPresent()) {
			DetailsEvents category = optionalCategory.get();
			category.setStatus("Eliminado");
			repo.save(category);
		}
	}
}
