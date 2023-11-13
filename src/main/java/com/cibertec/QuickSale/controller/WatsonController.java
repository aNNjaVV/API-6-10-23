package com.cibertec.QuickSale.controller;

import com.cibertec.QuickSale.model.Category;
import com.cibertec.QuickSale.model.Watson;
import com.cibertec.QuickSale.model.dto.CategoryDto;
import com.cibertec.QuickSale.model.dto.WatsonDto;
import com.cibertec.QuickSale.model.response.MensajeResponse;
import com.cibertec.QuickSale.servic.ICategoryService;
import com.cibertec.QuickSale.servic.IWatsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/watson")
public class WatsonController {

	@Autowired
	IWatsonService service;

	@CrossOrigin(origins = "https://angularquicksale.web.app/#/")
	@GetMapping
	public ResponseEntity<?> listar() {
		List<Watson> lista = service.listar();
		if (lista == null) {
			return new ResponseEntity<>(
					MensajeResponse.builder()
							.mensaje("No hay registros")
							.object(null)
							.success(false) // Establecer success en false
							.build(),
					HttpStatus.OK
			);
		} else {
			return new ResponseEntity<>(
					MensajeResponse.builder()
							.mensaje("")
							.object(lista)
							.success(true) // Establecer success en true
							.build(),
					HttpStatus.OK
			);
		}
	}

	@CrossOrigin(origins = "https://angularquicksale.web.app/#/")
	@GetMapping("/{id}")
	public ResponseEntity<?> listarPorId(@PathVariable("id") Integer id) {
		Watson watson = service.listarPorId(id);

		if(watson == null) {
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
							.object(WatsonDto.builder()
									.idWatson(watson.getIdWatson())
									.quantity(watson.getQuantity())
									.nameEvent(watson.getNameEvent())
									.emailCustomer(watson.getEmailCustomer())
									.payment(watson.getPayment())
									.build())
							.success(true)
							.build()
					, HttpStatus.OK);


		}
	}

	@CrossOrigin(origins = "https://angularquicksale.web.app/#/")
	@PostMapping
	public ResponseEntity<?> registrar(@RequestBody WatsonDto c) {
		Watson watson = null;
		try {
			watson = service.registrar(c);
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Guardado Correctamente")
					.object(WatsonDto.builder()
							.idWatson(watson.getIdWatson())
							.quantity(watson.getQuantity())
							.nameEvent(watson.getNameEvent())
							.emailCustomer(watson.getEmailCustomer())
							.payment(watson.getPayment())
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

	@CrossOrigin(origins = "https://angularquicksale.web.app/#/")
	@PutMapping
	public ResponseEntity<?> modificar(@RequestBody WatsonDto c) {
		Watson watson = null;
		try {
			watson = service.registrar(c);
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Actualizado Correctamente")
					.object(WatsonDto.builder()
							.idWatson(watson.getIdWatson())
							.quantity(watson.getQuantity())
							.nameEvent(watson.getNameEvent())
							.emailCustomer(watson.getEmailCustomer())
							.payment(watson.getPayment())
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

	@CrossOrigin(origins = "https://angularquicksale.web.app/#/")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable("id")Integer id) {
		try {
			Watson watson = service.listarPorId(id);
			service.eliminar(watson.getIdWatson());
			return new ResponseEntity<>(watson, HttpStatus.NO_CONTENT);

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


}
