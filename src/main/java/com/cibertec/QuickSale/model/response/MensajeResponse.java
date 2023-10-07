package com.cibertec.QuickSale.model.response;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MensajeResponse implements Serializable {
	/**
	 * 
	 */
	private String mensaje;
	private Object object;

	private boolean success;// Agregar el campo success


	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}


	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}




	@Override
	public String toString() {
		return "MensajeResponse [mensaje=" + mensaje + ", object=" + object + "]"  ;
	}





}
