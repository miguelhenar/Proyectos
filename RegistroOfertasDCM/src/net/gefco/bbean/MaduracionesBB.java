package net.gefco.bbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import net.gefco.modelo.Maduracion;
import net.gefco.negocio.GestorMaduraciones;

import org.springframework.dao.DataIntegrityViolationException;

public class MaduracionesBB {
	
	private String estado;
	private Maduracion maduracion;
	private GestorMaduraciones gestorMaduraciones;
			
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Maduracion getMaduracion() {
		return maduracion;
	}

	public void setMaduracion(Maduracion maduracion) {
		this.maduracion = maduracion;
	}

	public void setGestorMaduraciones(GestorMaduraciones gestorMaduraciones) {
		this.gestorMaduraciones = gestorMaduraciones;
	}

	public List<Maduracion> getListaMaduraciones() {		
		return gestorMaduraciones.listarTodos();
	}

	public List<SelectItem> getListaMaduracionesSI() {
		List<Maduracion> lista = gestorMaduraciones.listarTodosOrdenado("madu_codigo");
		List<SelectItem> listaSI = new ArrayList<SelectItem>();
		listaSI.add(new SelectItem(null,"Seleccione..."));
		for(Maduracion maduracion: lista){
			SelectItem si = new SelectItem(maduracion.getMadu_codigo(), maduracion.getMadu_nombre());
			listaSI.add(si);
		}
		return listaSI;
	}

	public String insertar(){
		if (maduracion.getMadu_nombre()==null ||
				maduracion.getMadu_nombre().length()==0)	{		
			estado="Introduzca la maduración";
			return null;
		}	
		try {
			gestorMaduraciones.insertar(maduracion);
			estado = "Registro guardado";
		} catch (DataIntegrityViolationException e) {	
			estado = "Valor incorrecto. No se guardó ningún dato";
		}
		return null;
	}
	
	public String modificar(){
		if (maduracion.getMadu_nombre()==null ||
				maduracion.getMadu_nombre().length()==0)	{		
			estado="Introduzca la maduración";
			return null;
		}	
		gestorMaduraciones.modificar(maduracion);
		return null;
	}
	
	public String borrar(){
		if (maduracion.getMadu_nombre()==null ||
				maduracion.getMadu_nombre().length()==0)	{			
			return null;
		}
		try {
			gestorMaduraciones.borrar(maduracion);
			maduracion.vaciar();
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
		Maduracion aux = gestorMaduraciones.buscar(maduracion.getMadu_codigo());
		maduracion.copiarValores(aux);
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
		maduracion.vaciar();
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
