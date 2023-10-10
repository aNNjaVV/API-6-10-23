package com.cibertec.QuickSale.servic;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.cibertec.QuickSale.model.Event;
import com.cibertec.QuickSale.model.dto.SaleDto;
import org.springframework.stereotype.Service;

import com.cibertec.QuickSale.model.Sale;

@Service
public interface ISaleService {

	Sale registrar(SaleDto s);
	Sale modificar(SaleDto s);
	List<Sale> listar();
	Sale listarPorId(Integer id);
	void eliminar(Integer id);

	void cambiarEstadoAEliminado(Integer id);

	List<Sale> findByDateRange(LocalDate dateStart, LocalDate dateFin);

	List<Sale> findSaleByEmailUser(String email);



}
