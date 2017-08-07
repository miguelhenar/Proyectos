package net.gefco.modelo.dto;

import java.util.Date;

import net.gefco.modelo.Maduracion;
import net.gefco.modelo.Pole;
import net.gefco.modelo.TipoOferta;
import net.gefco.modelo.Usuario;


public class CBOferta {
	private int codigo;
	private String empresa = null;	
	private Date fechaTopeI = null;
	private Date fechaTopeF = null;
	private Date fechaUltimoEstadoI = null;
	private Date fechaUltimoEstadoF = null;
	private Maduracion maduracion = new Maduracion();
	private Pole pole = new Pole();
	private TipoOferta tipoOferta = new TipoOferta();
	private Usuario usuario = new Usuario();	

	public CBOferta() {
		super();
		//  Auto-generated constructor stub
	}
	
	public CBOferta(int codigo, String empresa, Date fechaTopeI, Date fechaTopeF,
			Date fechaUltimoEstadoI, Date fechaUltimoEstadoF, Maduracion maduracion,
			Pole pole, TipoOferta tipoOferta, Usuario usuario) {
		super();
		this.codigo = codigo;
		this.empresa = empresa;
		this.fechaTopeI = fechaTopeI;
		this.fechaTopeF = fechaTopeF;
		this.fechaUltimoEstadoI = fechaUltimoEstadoI;
		this.fechaUltimoEstadoF = fechaUltimoEstadoF;
		this.maduracion = maduracion;
		this.pole = pole;
		this.tipoOferta = tipoOferta;
		this.usuario = usuario;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public Date getFechaTopeI() {
		return fechaTopeI;
	}

	public void setFechaTopeI(Date fechaTopeI) {
		this.fechaTopeI = fechaTopeI;
	}

	public Date getFechaTopeF() {
		return fechaTopeF;
	}

	public void setFechaTopeF(Date fechaTopeF) {
		this.fechaTopeF = fechaTopeF;
	}

	public Date getFechaUltimoEstadoI() {
		return fechaUltimoEstadoI;
	}

	public void setFechaUltimoEstadoI(Date fechaUltimoEstadoI) {
		this.fechaUltimoEstadoI = fechaUltimoEstadoI;
	}

	public Date getFechaUltimoEstadoF() {
		return fechaUltimoEstadoF;
	}

	public void setFechaUltimoEstadoF(Date fechaUltimoEstadoF) {
		this.fechaUltimoEstadoF = fechaUltimoEstadoF;
	}

	public Maduracion getMaduracion() {
		return maduracion;
	}

	public void setMaduracion(Maduracion maduracion) {
		this.maduracion = maduracion;
	}

	public Pole getPole() {
		return pole;
	}

	public void setPole(Pole pole) {
		this.pole = pole;
	}

	public void setTipoOferta(TipoOferta tipoOferta) {
		this.tipoOferta = tipoOferta;
	}
	
	public TipoOferta getTipoOferta() {
		return tipoOferta;
	}	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void vaciar() {		
		codigo=0;
		empresa = null;	
		fechaTopeI = null;
		fechaTopeF = null;
		fechaUltimoEstadoI = null;
		fechaUltimoEstadoF = null;
		maduracion = new Maduracion();
		pole = new Pole();
		tipoOferta = new TipoOferta();
		usuario = new Usuario();
	}

}
