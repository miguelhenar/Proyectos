package net.gefco.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Rol {
	@Id
	@Column(name = "rol_codigo")
	private String rol_codigo;
	private String rol_descripcion;
			
	public Rol() {
		super();
		//  Auto-generated constructor stub
	}
	public Rol(String rolCodigo, String rolDescripcion) {
		super();
		rol_codigo = rolCodigo;
		rol_descripcion = rolDescripcion;
	}
	public String getRol_codigo() {
		return rol_codigo;
	}
	public void setRol_codigo(String rolCodigo) {
		rol_codigo = rolCodigo;
	}
	public String getRol_descripcion() {
		return rol_descripcion;
	}
	public void setRol_descripcion(String rolDescripcion) {
		rol_descripcion = rolDescripcion;
	}
}