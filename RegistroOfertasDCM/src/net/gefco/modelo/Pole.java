package net.gefco.modelo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pole {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pole_codigo;
	private String pole_nombre;
	
	public Pole() {
		super();
		//  Auto-generated constructor stub
	}
	
	
	
	
	public Pole(int poleCodigo, String poleNombre) {
		super();
		pole_codigo = poleCodigo;
		pole_nombre = poleNombre;
	}
	public int getPole_codigo() {
		return pole_codigo;
	}
	public void setPole_codigo(int poleCodigo) {
		pole_codigo = poleCodigo;
	}
	public String getPole_nombre() {
		return pole_nombre;
	}
	public void setPole_nombre(String poleNombre) {
		pole_nombre = poleNombre;
	}
	
	public void copiarValores(Pole aux) {
		pole_codigo = aux.pole_codigo;
		pole_nombre =	aux.pole_nombre;		
	}
	public void vaciar() {
		pole_codigo = 0;
		pole_nombre = null;		
	}
	
	//Como nos ha salido el error identifier of an instance of net.gefco.modelo.Oferta was altered from 19 to 0
	//sobreescribimos el mé equals (solución google)
	//I had to implement the "equals" method into the associated class. That is, if you have an A class that is 
	//associated with a B class that has a composite id, you must implement the "equals" method in the A class
	//If you overwrite equals(), you must overwrite hashcode()
//	public boolean equals(Object otroPole){
//		boolean igual = false;
//		if (otroPole instanceof Pole) {
//			Pole p = (Pole) otroPole;
//			igual = (pole_codigo == p.pole_codigo);
//		}
//		return igual;
//	}
	
}
