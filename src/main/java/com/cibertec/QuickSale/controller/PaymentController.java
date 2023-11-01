package com.cibertec.QuickSale.controller;

import java.util.List;

import com.cibertec.QuickSale.model.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cibertec.QuickSale.model.response.MensajeResponse;
import com.cibertec.QuickSale.model.Payment;
import com.cibertec.QuickSale.servic.IPaymentService;

@RestController
@RequestMapping("api/payment")
public class PaymentController {

	@Autowired
	IPaymentService service;

	@CrossOrigin(origins = "http://localhost:4200/")
	@GetMapping
	public ResponseEntity<?> listar() {
		List<Payment> lista = service.listar();
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
		Payment p = service.listarPorId(id);

		if(p == null) {
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
							.object(PaymentDto.builder()
									.idPayment(p.getIdPayment())
									.name(p.getName())
									.status(p.getStatus())
									.build())
							.success(true)
							.build()
					, HttpStatus.OK);


		}

	}

	@CrossOrigin(origins = "http://localhost:4200/")
	@PostMapping
	public ResponseEntity<?> registrar(@RequestBody PaymentDto pa) {
		Payment paymentSave = null;
		try {
			paymentSave = service.registrar(pa);
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Guardado Correctamente")
					.object(PaymentDto.builder()
							.idPayment(paymentSave.getIdPayment())
							.name(paymentSave.getName())
							.status(paymentSave.getStatus())
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
	public ResponseEntity<?> modificar(@RequestBody PaymentDto pa) {
		Payment paymentUpdate = null;
		try {

			paymentUpdate = service.registrar(pa);
			return new ResponseEntity<>(MensajeResponse.builder()
					.mensaje("Actualizado Correctamente")
					.object(PaymentDto.builder()
							.idPayment(paymentUpdate.getIdPayment())
							.name(paymentUpdate.getName())
							.status(paymentUpdate.getStatus())
							.build())
					.success(true) // Establecer success en false
					.build()
					, HttpStatus.CREATED);
		} catch (DataAccessException exDt) {
			return new ResponseEntity<>(
					MensajeResponse.builder()
							.mensaje(exDt.getMessage())
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
			Payment paymentDelete = service.listarPorId(id);
			service.eliminar(paymentDelete.getIdPayment());
			return new ResponseEntity<>(paymentDelete, HttpStatus.NO_CONTENT);

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
}
