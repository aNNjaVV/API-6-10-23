package com.cibertec.QuickSale.controller;

import java.util.List;

import com.cibertec.QuickSale.model.response.MensajeResponse;
import com.cibertec.QuickSale.model.dto.DetailsEventsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cibertec.QuickSale.model.DetailsEvents;
import com.cibertec.QuickSale.servic.IDetailsEventsService;

@RestController
@RequestMapping("api/detailsevents")
public class DetailsEventsController {

	@Autowired
	IDetailsEventsService service;

	@CrossOrigin(origins = "http://localhost:4200/")
	@GetMapping
	public ResponseEntity<?> listar() {
		List<DetailsEvents> lista = service.listar();
		if (lista == null) {
			return new ResponseEntity<>(
					MensajeResponse.builder()
							.mensaje("No hay registros")
							.object(null)
							.success(false) // Establecer success en false
							.build()
					, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(
					MensajeResponse.builder()
							.mensaje("")
							.object(lista)
							.success(true) // Establecer success en true
							.build()
					, HttpStatus.OK);
		}
	}

	@CrossOrigin(origins = "http://localhost:4200/")
	@GetMapping("/{id}")
	public ResponseEntity<?> listarPorId(@PathVariable("id") Integer id) {
		DetailsEvents detailsEvents = service.listarPorId(id);

		if(detailsEvents == null) {
			return  new ResponseEntity<>(
					MensajeResponse.builder()
							.mensaje("Codigo no encontrado")
							.object(null)
							.success(false) // Establecer success en false
							.build()
					, HttpStatus.NOT_FOUND);

		} else {
			return new ResponseEntity<>(
					MensajeResponse.builder()
							.mensaje("")
							.object(DetailsEventsDto.builder()
									.idDetailsEvents(detailsEvents.getIdDetailsEvents())
									.description(detailsEvents.getDescription())
									.title(detailsEvents.getTitle())
									.comments(detailsEvents.getComments())
									.urlImageRef(detailsEvents.getUrlImageRef())
									.status(detailsEvents.getStatus())
									.event(detailsEvents.getEvent())
									.build())
							.success(true)
							.build()
					, HttpStatus.OK);


		}
	}

	@CrossOrigin(origins = "http://localhost:4200/")
	@PostMapping
	public ResponseEntity<?> registrar(@RequestBody DetailsEventsDto de) {
		DetailsEvents detailsEventsSave = null;
		try {
			detailsEventsSave = service.registrar(de);
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Guardado Correctamente")
					.object(DetailsEventsDto.builder()
							.idDetailsEvents(detailsEventsSave.getIdDetailsEvents())
							.description(detailsEventsSave.getDescription())
							.title(detailsEventsSave.getTitle())
							.comments(detailsEventsSave.getComments())
							.urlImageRef(detailsEventsSave.getUrlImageRef())
							.status(detailsEventsSave.getStatus())
							.event(detailsEventsSave.getEvent())
							.build())
					.success(true)
					.build()
					, HttpStatus.CREATED);
		} catch (DataAccessException data){
			return new ResponseEntity<>(
					MensajeResponse.builder()
							.mensaje(data.getMessage())
							.object(null)
							.success(false) // Establecer success en true
							.build()
					, HttpStatus.METHOD_NOT_ALLOWED);
		}
	}

	@CrossOrigin(origins = "http://localhost:4200/")
	@PutMapping
	public ResponseEntity<?> modificar(@RequestBody DetailsEventsDto de) {
		DetailsEvents detailsEventsUpdate = null;
		try {
			detailsEventsUpdate = service.registrar(de);
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Actualizado Correctamente")
					.object(DetailsEventsDto.builder()
							.idDetailsEvents(detailsEventsUpdate.getIdDetailsEvents())
							.description(detailsEventsUpdate.getDescription())
							.title(detailsEventsUpdate.getTitle())
							.comments(detailsEventsUpdate.getComments())
							.urlImageRef(detailsEventsUpdate.getUrlImageRef())
							.status(detailsEventsUpdate.getStatus())
							.event(detailsEventsUpdate.getEvent())
							.build())
					.success(true) // Establecer success en false
					.build()
					, HttpStatus.CREATED);
		} catch (DataAccessException data){
			return new ResponseEntity<>(
					MensajeResponse.builder()
							.mensaje(data.getMessage())
							.object(null)
							.success(false) // Establecer success en false
							.build()
					, HttpStatus.METHOD_NOT_ALLOWED);
		}
	}

	@CrossOrigin(origins = "http://localhost:4200/")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable("id")Integer id) {
		try {
			DetailsEvents detailsEventsDelete = service.listarPorId(id);
			service.eliminar(detailsEventsDelete.getIdDetailsEvents());
			return new ResponseEntity<>(detailsEventsDelete, HttpStatus.NO_CONTENT);

		}catch (DataAccessException da) {
			return new ResponseEntity<>(
					MensajeResponse.builder()
							.mensaje(da.getMessage())
							.object(null)
							.success(true)// Establecer success en false
							.build()
					, HttpStatus.METHOD_NOT_ALLOWED);

		}
	}

	@CrossOrigin(origins = "http://localhost:4200/")
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> cambiarEstadoAEliminado(@PathVariable("id") Integer id) {
		try {
			service.cambiarEstadoAEliminado(id);
			return new ResponseEntity<>(
					MensajeResponse.builder()
							.mensaje("Estado cambiado a Eliminado correctamente")
							.success(true)
							.build(),
					HttpStatus.OK
			);
		} catch (DataAccessException data) {
			return new ResponseEntity<>(
					MensajeResponse.builder()
							.mensaje(data.getMessage())
							.object(null)
							.success(false)
							.build(),
					HttpStatus.METHOD_NOT_ALLOWED
			);
		}	}
	

	@CrossOrigin(origins = "http://localhost:4200/")
	@GetMapping("/findxidEvent/{idEvent}")
	public ResponseEntity<?> filterxIdEvent(@PathVariable("idEvent") Integer idEvent) {
		DetailsEvents detailsEvents = service.filterxIdEvent(idEvent);

		if(detailsEvents == null) {
			return  new ResponseEntity<>(
					MensajeResponse.builder()
							.mensaje("Codigo no encontrado")
							.object(null)
							.success(false) // Establecer success en false
							.build()
					, HttpStatus.NOT_FOUND);

		} else {
			return new ResponseEntity<>(
					MensajeResponse.builder()
							.mensaje("")
							.object(DetailsEventsDto.builder()
									.idDetailsEvents(detailsEvents.getIdDetailsEvents())
									.description(detailsEvents.getDescription())
									.title(detailsEvents.getTitle())
									.comments(detailsEvents.getComments())
									.urlImageRef(detailsEvents.getUrlImageRef())
									.status(detailsEvents.getStatus())
									.event(detailsEvents.getEvent())
									.build())
							.success(true)
							.build()
					, HttpStatus.OK);


		}
	}
}
