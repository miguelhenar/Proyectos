package net.gefco.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fuente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fuen_codigo;	
	private String fuen_nombre;
	public Fuente() {
		super();
		//  Auto-generated constructor stub
	}
	public Fuente(int fuenCodigo, String fuenNombre) {
		super();
		fuen_codigo = fuenCodigo;
		fuen_nombre = fuenNombre;
	}
	public int getFuen_codigo() {
		return fuen_codigo;
	}
	public void setFuen_codigo(int fuenCodigo) {
		fuen_codigo = fuenCodigo;
	}
	public String getFuen_nombre() {
		return fuen_nombre;
	}
	public void setFuen_nombre(String fuenNombre) {
		fuen_nombre = fuenNombre;
	}			
	
	public void copiarValores(Fuente aux) {
		fuen_codigo = aux.fuen_codigo;
		fuen_nombre =	aux.fuen_nombre;		
	}
	public void vaciar() {
		fuen_codigo = 0;
		fuen_nombre = null;		
	}
}
