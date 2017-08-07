package net.gefco.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uO_codigo;
	private String uO_nombre;
	public UO() {
		super();
		//  Auto-generated constructor stub
	}
	
	public int getuO_codigo() {
		return uO_codigo;
	}

	public void setuO_codigo(int uOCodigo) {
		uO_codigo = uOCodigo;
	}

	public String getuO_nombre() {
		return uO_nombre;
	}

	public void setuO_nombre(String uONombre) {
		uO_nombre = uONombre;
	}

	public UO(int uOCodigo, String uONombre) {
		super();
		uO_codigo = uOCodigo;
		uO_nombre = uONombre;
	}

	public void copiarValores(UO aux) {
		uO_codigo = aux.uO_codigo;
		uO_nombre =	aux.uO_nombre;
		
	}
	public void vaciar() {
		uO_codigo = 0;
		uO_nombre = null;		
	}
}
