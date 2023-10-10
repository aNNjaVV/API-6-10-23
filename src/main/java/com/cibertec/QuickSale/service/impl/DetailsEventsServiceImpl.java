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
	        // Decodificar la cadena base64 y asignarla al atributo urlImageRef
	        String base64Data = de.getUrlImageRef(); // Obtener la cadena base64 desde el DTO
	        
	        if (base64Data != null && !base64Data.isEmpty()) {
	            byte[] decodedImage = Base64.getDecoder().decode(base64Data);
	            String imageInBase64 = new String(decodedImage);
	            de.setUrlImageRef(imageInBase64);
	        }

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
	public void cambiarEstadoAEliminado(Integer id) {
		Optional<DetailsEvents> optionalCategory = repo.findById(id);
		if (optionalCategory.isPresent()) {
			DetailsEvents category = optionalCategory.get();
			category.setStatus("Eliminado");
			repo.save(category);
		}
	}
}
