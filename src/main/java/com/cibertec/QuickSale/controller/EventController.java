package com.cibertec.QuickSale.controller;

import java.util.ArrayList;
import java.util.List;

import com.cibertec.QuickSale.model.Sale;
import com.cibertec.QuickSale.model.response.MensajeResponse;
import com.cibertec.QuickSale.model.dto.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cibertec.QuickSale.model.Event;
import com.cibertec.QuickSale.servic.IEventService;

@RestController
@RequestMapping("api/event")
public class EventController {

    @Autowired
    IEventService service;

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping
    public ResponseEntity<?> listar() {
        List<Event> lista = service.listar();
        List<Event> listActive = new ArrayList<>();

        for(Event event:lista){
            if("Activo".equals(event.getStatus())){
                listActive.add(event);
            }
        }

        if (lista.isEmpty()) {
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
                            .object(listActive)
                            .success(true) // Establecer success en true
                            .build()
                    , HttpStatus.OK);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable("id") Integer id) {
        Event event = service.listarPorId(id);

        if (event == null) {
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
                            .object(EventDto.builder()
                                    .idEvent(event.getIdEvent())
                                    .title(event.getTitle())
                                    .description(event.getDescription())
                                    .dateEvent(event.getDateEvent())
                                    .image(event.getImage())
                                    .place(event.getPlace())
                                    .ticketsQuantity(event.getTicketsQuantity())
                                    .unitPrice(event.getUnitPrice())
                                    .status(event.getStatus())
                                    .category(event.getCategory())
                                    .build())
                            .success(true)
                            .build()
                    , HttpStatus.OK);


        }
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody EventDto ev) {
        Event eventSave = null;
        try {
            eventSave = service.registrar(ev);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado Correctamente")
                    .object(EventDto.builder()
                            .idEvent(eventSave.getIdEvent())
                            .title(eventSave.getTitle())
                            .description(eventSave.getDescription())
                            .dateEvent(eventSave.getDateEvent())
                            .image(eventSave.getImage())
                            .place(eventSave.getPlace())
                            .ticketsQuantity(eventSave.getTicketsQuantity())
                            .unitPrice(eventSave.getUnitPrice())
                            .status(eventSave.getStatus())
                            .category(eventSave.getCategory())
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
    public ResponseEntity<?> modificar(@RequestBody EventDto ev) {
        Event eventUpdate = null;
        try {
            eventUpdate = service.registrar(ev);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Actualizado Correctamente")
                    .object(EventDto.builder()
                            .idEvent(eventUpdate.getIdEvent())
                            .title(eventUpdate.getTitle())
                            .description(eventUpdate.getDescription())
                            .dateEvent(eventUpdate.getDateEvent())
                            .image(eventUpdate.getImage())
                            .place(eventUpdate.getPlace())
                            .ticketsQuantity(eventUpdate.getTicketsQuantity())
                            .unitPrice(eventUpdate.getUnitPrice())
                            .status(eventUpdate.getStatus())
                            .category(eventUpdate.getCategory())
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
            Event eventDelete = service.listarPorId(id);
            service.eliminar(eventDelete.getIdEvent());
            return new ResponseEntity<>(eventDelete, HttpStatus.NO_CONTENT);

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
    @GetMapping("/find/{title}")
    public ResponseEntity<?> findEventByName(@PathVariable("title") String title) {
        List<Event> events = service.findEventByName(title);

        if (events.isEmpty()) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Evento no encontrado o no disponible")
                            .object(null)
                            .success(false)
                            .build(),
                    HttpStatus.NOT_FOUND
            );
        }else {

            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("")
                            .object(events)
                            .success(true)
                            .build(),
                    HttpStatus.OK
            );
        }
    }


    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/filter/{idCategory}")
    public ResponseEntity<?> filterEventByCategory(@PathVariable("idCategory") Integer idCategory) {
        List<Event> event = service.filterEventByCategory(idCategory);

        if (event.isEmpty()) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Error")
                            .object(null)
                            .success(false)
                            .build(),
                    HttpStatus.NOT_FOUND
            );
        } else {

            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("")
                            .object(event)
                            .success(true)
                            .build(),
                    HttpStatus.OK
            );
        }
    }
}
