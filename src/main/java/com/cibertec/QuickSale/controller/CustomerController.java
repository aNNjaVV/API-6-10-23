package com.cibertec.QuickSale.controller;

import java.util.List;

import com.cibertec.QuickSale.model.*;
import com.cibertec.QuickSale.model.dto.CustomerDto;
import com.cibertec.QuickSale.model.response.CustomerResponse;
import com.cibertec.QuickSale.model.response.MensajeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cibertec.QuickSale.servic.ICustomerService;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    ICustomerService service;

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CustomerDto customerDto) {
        String email = customerDto.getEmail();
        String password = customerDto.getPassword();

        Customer authenticated = service.login(email, password);

        if (email.equals("admin@hotmail.com") && password.equals("admin")) {
            return new ResponseEntity<>(
                    AdminResponse.builder()
                            .id(0)
                            .mensaje("Bievenido Admin")
                            .name("admin")
                            .roles("admin")
                            .success(true)
                            .build(),
                    HttpStatus.OK);
        }

        if (authenticated != null && authenticated.getPassword().equals(customerDto.getPassword())) {
            return new ResponseEntity<>(
                    CustomerResponse.builder()
                            .mensaje("Inicio de sesion Exitoso")
                            .id(authenticated.getIdCustomer())
                            .name(authenticated.getFullName())
                            .roles(authenticated.getRoles())
                            .success(true)
                            .build(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    CustomerResponse.builder()
                            .mensaje("Credenciales Incorrectas")
                            .name("")
                            .roles("")
                            .success(true)
                            .build(),
                    HttpStatus.OK);
        }

    }



    @CrossOrigin(origins = "http://localhost:4200/")
    @PutMapping("/changePassword/{email}/{password}/{nuevaContraseña}")
    public ResponseEntity<?> changePassword(@PathVariable String email, @PathVariable String password, @PathVariable String nuevaContraseña) {
        Boolean result = service.changePassword(email,password,nuevaContraseña);

        if(result != null && result){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Contraseña cambiada con éxito")
                            .object(null)
                            .success(true)
                            .build(),
                    HttpStatus.OK
            );
        }else {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Error verifica los datos proporcionados")
                            .object(null)
                            .success(false)
                            .build(),
                    HttpStatus.BAD_REQUEST
            );

        }


    }


    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping
    public ResponseEntity<?> listar() {
        List<Customer> lista = service.listar();

        if (lista.isEmpty()) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No hay registros")
                            .object(null)
                            .success(false) // Establecer success en true
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
        Customer customer = service.listarPorId(id);

        if (customer == null) {
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
                            .object(CustomerDto.builder()
                                    .idCustomer(customer.getIdCustomer())
                                    .fullName(customer.getFullName())
                                    .dni(customer.getDni())
                                    .age(customer.getAge())
                                    .email(customer.getEmail())
                                    .password(customer.getPassword())
                                    .roles(customer.getRoles())
                                    .status(customer.getStatus())
                                    .build())
                            .success(true)
                            .build()
                    , HttpStatus.OK);


        }
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody CustomerDto cu) {
        Customer customerSave = null;
        try {
            customerSave = service.registrar(cu);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado Correctamente")
                    .object(CustomerDto.builder()
                            .idCustomer(customerSave.getIdCustomer())
                            .fullName(customerSave.getFullName())
                            .dni(customerSave.getDni())
                            .age(customerSave.getAge())
                            .email(customerSave.getEmail())
                            .password(customerSave.getPassword())
                            .roles(customerSave.getRoles())
                            .status(customerSave.getStatus())
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
    public ResponseEntity<?> modificar(@RequestBody CustomerDto cu) {
        Customer customerUpdate = null;
        try {
            customerUpdate = service.registrar(cu);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Actualizado Correctamente")
                    .object(CustomerDto.builder()
                            .idCustomer(customerUpdate.getIdCustomer())
                            .fullName(customerUpdate.getFullName())
                            .dni(customerUpdate.getDni())
                            .age(customerUpdate.getAge())
                            .email(customerUpdate.getEmail())
                            .password(customerUpdate.getPassword())
                            .roles(customerUpdate.getRoles())
                            .status(customerUpdate.getStatus())
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
            Customer customerDelete = service.listarPorId(id);
            service.eliminar(customerDelete.getIdCustomer());
            return new ResponseEntity<>(customerDelete, HttpStatus.NO_CONTENT);

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



}
