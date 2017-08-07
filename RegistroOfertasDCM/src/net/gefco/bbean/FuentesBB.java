package net.gefco.bbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import net.gefco.modelo.Fuente;
import net.gefco.negocio.GestorFuentes;

import org.springframework.dao.DataIntegrityViolationException;

public class FuentesBB {

	private String estado;
	private Fuente fuente;	
	private GestorFuentes gestorFuentes;
		
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Fuente getFuente() {
		return fuente;
	}

	public void setFuente(Fuente fuente) {
		this.fuente = fuente;
	}

	public void setGestorFuentes(GestorFuentes gestorFuentes) {
		this.gestorFuentes = gestorFuentes;
	}

	public List<Fuente> getListaFuentes() {		
		return gestorFuentes.listarTodos();
	}

	public List<SelectItem> getListaFuentesSI() {
		List<Fuente> lista = gestorFuentes.listarTodosOrdenado("fuen_codigo");
		List<SelectItem> listaSI = new ArrayList<SelectItem>();
		listaSI.add(new SelectItem(null,"Seleccione..."));
		for(Fuente fuente: lista){
			SelectItem si = new SelectItem(fuente.getFuen_codigo(), fuente.getFuen_codigo()+" "+fuente.getFuen_nombre());
			listaSI.add(si);
		}
		return listaSI;
	}

	public String insertar(){
		if (fuente.getFuen_nombre()==null ||
				fuente.getFuen_nombre().length()==0)	{		
			estado="Introduzca la fuente";
			return null;
		}				
		try {
			gestorFuentes.insertar(fuente);
			estado = "Registro guardado";
		} catch (DataIntegrityViolationException e) {	
			estado = "Valor incorrecto. No se guardó ningún dato";
		}
		return null;
	}
	
	public String modificar(){
		if (fuente.getFuen_nombre()==null ||
				fuente.getFuen_nombre().length()==0)	{		
			estado="Introduzca la fuente";
			return null;
		}	
		gestorFuentes.modificar(fuente);
		return null;
	}
	
	public String borrar(){
		if (fuente.getFuen_nombre()==null ||
				fuente.getFuen_nombre().length()==0)	{			
			return null;
		}
		try {
			gestorFuentes.borrar(fuente);
			fuente.vaciar();
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
		Fuente aux = gestorFuentes.buscar(fuente.getFuen_codigo());
		fuente.copiarValores(aux);
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
		fuente.vaciar();
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
