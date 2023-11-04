package com.cibertec.QuickSale.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cibertec.QuickSale.model.Event;
import com.cibertec.QuickSale.model.response.MensajeResponse;
import com.cibertec.QuickSale.model.dto.SaleDto;
import com.cibertec.QuickSale.model.response.SaleEmailUserResponse;
import com.cibertec.QuickSale.servic.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cibertec.QuickSale.model.Sale;
import com.cibertec.QuickSale.servic.ISaleService;

@RestController
@RequestMapping("api/sale")
public class SaleController {

    @Autowired
    ISaleService service;

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping
    public ResponseEntity<?> listar() {

        List<Sale> lista = service.listar();

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
        Sale sale = service.listarPorId(id);

        if (sale == null) {
            return new ResponseEntity<>(
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
                            .object(SaleDto.builder()
                                    .idSale(sale.getIdSale())
                                    .saleDate(sale.getSaleDate())
                                    .operationNumber(sale.getOperationNumber())
                                    .total(sale.getTotal())
                                    .quantity(sale.getQuantity())
                                    .status(sale.getStatus())
                                    .event(sale.getEvent())
                                    .customer(sale.getCustomer())
                                    .payment(sale.getPayment())
                                    .build())
                            .success(true)
                            .build()
                    , HttpStatus.OK);


        }
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody SaleDto se) {
        Sale saleSave = null;
        try {
            saleSave = service.registrar(se);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado Correctamente")
                    .object(SaleDto.builder()
                            .idSale(saleSave.getIdSale())
                            .saleDate(saleSave.getSaleDate())
                            .operationNumber(saleSave.getOperationNumber())
                            .total(saleSave.getTotal())
                            .quantity(saleSave.getQuantity())
                            .status(saleSave.getStatus())
                            .event(saleSave.getEvent())
                            .customer(saleSave.getCustomer())
                            .payment(saleSave.getPayment())
                            .build())
                    .success(true)
                    .build()
                    , HttpStatus.CREATED);
        } catch (DataAccessException data) {
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
    public ResponseEntity<?> modificar(@RequestBody SaleDto se) {
        Sale saleUpdate = null;
        try {
            saleUpdate = service.registrar(se);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Actualizado Correctamente")
                    .object(SaleDto.builder()
                            .idSale(saleUpdate.getIdSale())
                            .saleDate(saleUpdate.getSaleDate())
                            .operationNumber(saleUpdate.getOperationNumber())
                            .total(saleUpdate.getTotal())
                            .quantity(saleUpdate.getQuantity())
                            .status(saleUpdate.getStatus())
                            .event(saleUpdate.getEvent())
                            .customer(saleUpdate.getCustomer())
                            .payment(saleUpdate.getPayment())
                            .build())
                    .success(true) // Establecer success en false
                    .build()
                    , HttpStatus.CREATED);
        } catch (DataAccessException data) {
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
    public ResponseEntity<?> eliminar(@PathVariable("id") Integer id) {
        try {
            Sale saleDelete = service.listarPorId(id);
            service.eliminar(saleDelete.getIdSale());
            return new ResponseEntity<>(saleDelete, HttpStatus.NO_CONTENT);

        } catch (DataAccessException da) {
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
        }
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/find/{dateStart}/{dateFin}")
    public ResponseEntity<?> findByDateRange(@PathVariable("dateStart") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dateStart, @PathVariable("dateFin") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dateFin) {
        List<Sale> sale = service.findByDateRange(dateStart, dateFin);
        if (sale == null) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Fechas no encontradas")
                            .object(null)
                            .success(false) // Establecer success en false
                            .build()
                    , HttpStatus.NOT_FOUND);

        } else {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("")
                            .object(sale)
                            .success(true) // Establecer success en true
                            .build()
                    , HttpStatus.OK);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/find/{email}")
    public ResponseEntity<?> findSaleByEmailUser(@PathVariable("email") String email) {
        List<Sale> sale = service.findSaleByEmailUser(email);
        if (sale == null) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No hay registros para este usuario")
                    .object(null)
                    .success(false)
                    .build()
                    , HttpStatus.NOT_FOUND);

        } else {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("")
                    .object(sale)
                    .success(true) // Establecer success en false
                    .build()
                    , HttpStatus.OK);


        }

    }


}
