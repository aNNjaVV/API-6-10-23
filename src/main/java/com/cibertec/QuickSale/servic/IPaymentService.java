package com.cibertec.QuickSale.servic;

import java.util.List;

import com.cibertec.QuickSale.model.dto.PaymentDto;
import org.springframework.stereotype.Service;

import com.cibertec.QuickSale.model.Payment;

@Service
public interface IPaymentService {

	Payment registrar(PaymentDto p);
	Payment modificar(PaymentDto p);
	List<Payment> listar();
	Payment listarPorId(Integer id);
	void eliminar(Integer id);

	void cambiarEstadoAEliminado(Integer id);
}

