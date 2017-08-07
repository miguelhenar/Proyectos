package net.gefco.bbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import net.gefco.modelo.Pole;
import net.gefco.modelo.Usuario;
import net.gefco.negocio.GestorPoles;

import org.springframework.dao.DataIntegrityViolationException;


public class PolesBB {

	private String estado;
	private Pole pole;
	private GestorPoles gestorPoles;
		
	public Usuario usuario;
		
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Pole getPole() {
		return pole;
	}

	public void setPole(Pole pole) {
		this.pole = pole;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void setGestorPoles(GestorPoles gestorPoles) {
		this.gestorPoles = gestorPoles;
	}
		
	public List<Pole> getListaPoles() {		
		return gestorPoles.listarTodos();
	}
	
	public List<SelectItem> getListasPolesSI() {
		List<Pole> lista = gestorPoles.listarTodos(); 
		List<SelectItem> listaSI = new ArrayList<SelectItem>();
		
		for (Pole p: lista) {
			SelectItem si = new SelectItem(p.getPole_codigo(),p.getPole_nombre());
			listaSI.add(si);
		}
		return listaSI;
	}
	
	public List<SelectItem> getListaPolesSI() {
		List<Pole> lista = usuario.getPoles(); 
		List<SelectItem> listaSI = new ArrayList<SelectItem>();
		
		//sólo colocaremos el "seleccione" si hay más de una
		if(lista.size()>1)
			listaSI.add(new SelectItem(null, "Seleccione..."));
		for (Pole p: lista) {
			SelectItem si = new SelectItem(p.getPole_codigo(),p.getPole_nombre());
			listaSI.add(si);
		}
		return listaSI;
	}
	public String insertar(){
		if (pole.getPole_nombre()==null ||
				pole.getPole_nombre().length()==0)	{		
			estado="Introduzca el nombre";
			return null;
		}
		try {
			gestorPoles.insertar(pole);
			estado = "Registro guardado";
		} catch (DataIntegrityViolationException e) {	
			estado = "Valor incorrecto. No se guardó ningún dato";
		}
		return null;
	}
	
	public String modificar(){
		if (pole.getPole_nombre()==null ||
				pole.getPole_nombre().length()==0)	{		
			estado="Introduzca el nombre";
			return null;
		}
		gestorPoles.modificar(pole);
		return null;
	}
	
	public String borrar(){
		if (pole.getPole_nombre()==null ||
				pole.getPole_nombre().length()==0)	{		
			return null;
		}
		try {
			gestorPoles.borrar(pole);
			pole.vaciar();
			estado="Registro eliminado";
			//Pedimos el facesContext
			FacesContext fCtx = FacesContext.getCurrentInstance();
			//Obtenemos el Id del árbol que se está visualizando actualmente
			String vId = fCtx.getViewRoot().getViewId(); 
			//Obtenemos el Viewhandler
			ViewHandler vh = fCtx.getApplication().getViewHandler();
			//Le pedimos al ViewHandler que cree un nuevo árbol (vacío) con el id del antiguo
			UIViewRoot nuevoArbol = vh.createView(fCtx, vId);
			//Sustituimos el viejo por el nuevo
			fCtx.setViewRoot(nuevoArbol);
		} catch (DataIntegrityViolationException e) {
			estado = "No se pudo eliminar el registro";
		}
		return null;
	}
	
	public String seleccionar(){
		Pole aux = gestorPoles.buscar(pole.getPole_codigo());
		pole.copiarValores(aux);
		//Pedimos el facesContext
		FacesContext fCtx = FacesContext.getCurrentInstance();
		//Obtenemos el Id del árbol que se está visualizando actualmente
		String vId = fCtx.getViewRoot().getViewId(); 
		//Obtenemos el Viewhandler
		ViewHandler vh = fCtx.getApplication().getViewHandler();
		//Le pedimos al ViewHandler que cree un nuevo árbol (vacío) con el id del antiguo
		UIViewRoot nuevoArbol = vh.createView(fCtx, vId);
		//Sustituimos el viejo por el nuevo
		fCtx.setViewRoot(nuevoArbol);
		return null;
	}
	
	public String vaciar(){
		pole.vaciar();
		//Pedimos el facesContext
		FacesContext fCtx = FacesContext.getCurrentInstance();
		//Obtenemos el Id del árbol que se está visualizando actualmente
		String vId = fCtx.getViewRoot().getViewId(); 
		//Obtenemos el Viewhandler
		ViewHandler vh = fCtx.getApplication().getViewHandler();
		//Le pedimos al ViewHandler que cree un nuevo árbol (vacío) con el id del antiguo
		UIViewRoot nuevoArbol = vh.createView(fCtx, vId);
		//Sustituimos el viejo por el nuevo
		fCtx.setViewRoot(nuevoArbol);
		return null;
	}
}
