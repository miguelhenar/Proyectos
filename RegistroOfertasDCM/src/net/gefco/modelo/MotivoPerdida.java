package net.gefco.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class MotivoPerdida {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int mope_codigo;
	private String mope_nombre;
        
	public MotivoPerdida() {
		super();
		//  Auto-generated constructor stub
	}
	public MotivoPerdida(int mopeCodigo, String mopeNombre) {
		super();
		mope_codigo = mopeCodigo;
		mope_nombre = mopeNombre;
	}
	public int getMope_codigo() {
		return mope_codigo;
	}
	public void setMope_codigo(int mopeCodigo) {
		mope_codigo = mopeCodigo;
	}
	public String getMope_nombre() {
		return mope_nombre;
	}
	public void setMope_nombre(String mopeNombre) {
		mope_nombre = mopeNombre;
	}
	public void copiarValores(MotivoPerdida aux) {
		mope_codigo = aux.mope_codigo;
		mope_nombre =	aux.mope_nombre;		
	}
	public void vaciar() {
		mope_codigo = 0;
		mope_nombre = null;		
	}
}