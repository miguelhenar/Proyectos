package net.gefco.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PreparacionOferta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prof_codigo;
	private String prof_equipo;
	@ManyToOne
	@JoinColumn(name = "prof_uo", referencedColumnName = "uO_codigo")
	private UO uo = new UO();
	
	public PreparacionOferta() {
		super();
		//  Auto-generated constructor stub
	}
	
	public PreparacionOferta(int profCodigo, String profEquipo, UO uo) {
		super();
		prof_codigo = profCodigo;
		prof_equipo = profEquipo;
		this.uo = uo;
	}

	public int getProf_codigo() {
		return prof_codigo;
	}
	public void setProf_codigo(int profCodigo) {
		prof_codigo = profCodigo;
	}
	public String getProf_equipo() {
		return prof_equipo;
	}
	public void setProf_equipo(String profEquipo) {
		prof_equipo = profEquipo;
	}

	public UO getUo() {
		return uo;
	}

	public void setUo(UO uo) {
		this.uo = uo;
	}
	
	public void copiarValores(PreparacionOferta aux) {
		prof_codigo = aux.prof_codigo;
		prof_equipo =	aux.prof_equipo;
		uo = aux.uo;
	}
	public void vaciar() {
		prof_codigo = 0;
		prof_equipo = null;		
		uo =  new UO();
	}
	
	
}
