package net.gefco.bbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import net.gefco.modelo.Agencia;
import net.gefco.modelo.Usuario;
import net.gefco.negocio.GestorAgencias;


public class AgenciasBB {

	private GestorAgencias gestorAgencias;
	
	//para ver si es administrador u otro usuario
	public Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setGestorAgencias(GestorAgencias gestorAgencias) {
		this.gestorAgencias = gestorAgencias;
	}
	
	public List<SelectItem> getListaAgenciasSI() {
		List<Agencia> listaA = gestorAgencias.listarTodos(usuario);
		List<SelectItem> listaSI = new ArrayList<SelectItem>();

		//sólo colocaremos el "seleccione" si hay más de una pq el usuario el administrador
		if(listaA.size()>1)
			listaSI.add(new SelectItem(null, "Seleccione..."));
		for (Agencia a : listaA) {
			SelectItem si = new SelectItem(a.getAgen_codigo(), a.getAgen_codigo()+" "+a.getAgen_nombre());
			listaSI.add(si);
		}
		return listaSI;
	}
	
	
	
	
}
