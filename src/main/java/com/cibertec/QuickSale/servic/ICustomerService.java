package com.cibertec.QuickSale.servic;

import java.util.Date;
import java.util.List;

import com.cibertec.QuickSale.model.Customer;
import com.cibertec.QuickSale.model.Sale;
import com.cibertec.QuickSale.model.dto.CustomerDto;

public interface ICustomerService {

    Customer registrar(CustomerDto cu);

    Customer modificar(CustomerDto cu);

    List<Customer> listar();

    Customer listarPorId(Integer id);

    void eliminar(Integer id);

    void cambiarEstadoAEliminado(Integer id);

    Customer login(String email, String password);




}
