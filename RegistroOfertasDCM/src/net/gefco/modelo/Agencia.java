package net.gefco.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Agencia {
	@Id
	private String agen_codigo;
	private String agen_nombre;
	public Agencia() {
		super();
		//  Auto-generated constructor stub
	}
	public Agencia(String agenCodigo, String agenNombre) {
		super();
		agen_codigo = agenCodigo;
		agen_nombre = agenNombre;
	}
	public String getAgen_codigo() {
		return agen_codigo;
	}
	public void setAgen_codigo(String agenCodigo) {
		agen_codigo = agenCodigo;
	}
	public String getAgen_nombre() {
		return agen_nombre;
	}
	public void setAgen_nombre(String agenNombre) {
		agen_nombre = agenNombre;
	}
}
