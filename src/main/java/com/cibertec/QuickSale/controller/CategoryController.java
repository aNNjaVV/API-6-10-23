package com.cibertec.QuickSale.controller;

import java.util.ArrayList;
import java.util.List;

import com.cibertec.QuickSale.model.Event;
import com.cibertec.QuickSale.model.response.MensajeResponse;
import com.cibertec.QuickSale.model.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cibertec.QuickSale.model.Category;
import com.cibertec.QuickSale.servic.ICategoryService;

@RestController
@RequestMapping("api/category")
public class CategoryController {

	@Autowired
	ICategoryService service;

	@CrossOrigin(origins = "https://angularquicksale.web.app/#/")
	@GetMapping
	public ResponseEntity<?> listar() {
		List<Category> lista = service.listar();
		List<Category> listActive = new ArrayList<>();

		for(Category category:lista){
			if("Activo".equals(category.getStatus())){
				listActive.add(category);
			}
		}

		if (lista.isEmpty()) {
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
							.object(listActive)
							.success(true) // Establecer success en true
							.build(),
					HttpStatus.OK
			);
		}
	}

	@CrossOrigin(origins = "https://angularquicksale.web.app/#/")
	@GetMapping("/{id}")
	public ResponseEntity<?> listarPorId(@PathVariable("id") Integer id) {
		Category category = service.listarPorId(id);

		if(category == null) {
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
							.object(CategoryDto.builder()
									.idCategory(category.getIdCategory())
									.description(category.getDescription())
									.status(category.getStatus())
									.build())
							.success(true)
							.build()
					, HttpStatus.OK);


		}
	}

	@CrossOrigin(origins = "https://angularquicksale.web.app/#/")
	@PostMapping
	public ResponseEntity<?> registrar(@RequestBody CategoryDto c) {
		Category categorySave = null;
		try {
			categorySave = service.registrar(c);
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Guardado Correctamente")
					.object(CategoryDto.builder()
							.idCategory(categorySave.getIdCategory())
							.description(categorySave.getDescription())
							.status(categorySave.getStatus())
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
	public ResponseEntity<?> modificar(@RequestBody CategoryDto c) {
		Category categoryUpdate = null;
		try {
			categoryUpdate = service.registrar(c);
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Actualizado Correctamente")
					.object(CategoryDto.builder()
							.idCategory(categoryUpdate.getIdCategory())
							.description(categoryUpdate.getDescription())
							.status(categoryUpdate.getStatus())
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
			Category categoryDelete = service.listarPorId(id);
			service.eliminar(categoryDelete.getIdCategory());
			return new ResponseEntity<>(categoryDelete, HttpStatus.NO_CONTENT);

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

	@CrossOrigin(origins = "https://angularquicksale.web.app/#/")
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
}
