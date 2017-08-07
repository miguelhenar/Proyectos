package net.gefco.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoOferta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tiof_codigo;
	private String tiof_nombre;
	public TipoOferta() {
		super();
		//  Auto-generated constructor stub
	}
	
	public TipoOferta(int tiofCodigo, String tiofNombre) {
		super();
		tiof_codigo = tiofCodigo;
		tiof_nombre = tiofNombre;
	}

	public int getTiof_codigo() {
		return tiof_codigo;
	}
	public void setTiof_codigo(int tiofCodigo) {
		tiof_codigo = tiofCodigo;
	}
	public String getTiof_nombre() {
		return tiof_nombre;
	}
	public void setTiof_nombre(String tiofNombre) {
		tiof_nombre = tiofNombre;
	}
	public void copiarValores(TipoOferta aux) {
		tiof_codigo = aux.tiof_codigo;
		tiof_nombre =	aux.tiof_nombre;		
	}
	public void vaciar() {
		tiof_codigo = 0;
		tiof_nombre = null;		
	}
}
