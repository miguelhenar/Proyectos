package net.gefco.bbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import net.gefco.modelo.TipoOferta;
import net.gefco.negocio.GestorTiposOferta;

import org.springframework.dao.DataIntegrityViolationException;

public class TiposOfertaBB {

	private String estado;
	private TipoOferta tipoOferta;	
	private GestorTiposOferta gestorTiposOferta;
		
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public TipoOferta getTipoOferta() {
		return tipoOferta;
	}

	public void setTipoOferta(TipoOferta tipoOferta) {
		this.tipoOferta = tipoOferta;
	}

	public void setGestorTiposOferta(GestorTiposOferta gestorTiposOferta) {
		this.gestorTiposOferta = gestorTiposOferta;
	}

	public List<TipoOferta> getListaTiposOferta() {		
		return gestorTiposOferta.listarTodos();
	}

	public List<SelectItem> getListaTiposOfertaSI() {
		List<TipoOferta> lista = gestorTiposOferta.listarTodosOrdenado("tiof_codigo");
		List<SelectItem> listaSI = new ArrayList<SelectItem>();
		listaSI.add(new SelectItem(null,"Seleccione..."));
		for(TipoOferta TipoOf: lista){
			SelectItem si = new SelectItem(TipoOf.getTiof_codigo(), TipoOf.getTiof_codigo()+" "+TipoOf.getTiof_nombre());
			listaSI.add(si);
		}
		return listaSI;
	}

	public String insertar(){
		if (tipoOferta.getTiof_nombre()==null ||
				tipoOferta.getTiof_nombre().length()==0)	{		
			estado="Introduzca el tipo de oferta";
			return null;
		}				
		try {
			gestorTiposOferta.insertar(tipoOferta);
			estado = "Registro guardado";
		} catch (DataIntegrityViolationException e) {	
			estado = "Valor incorrecto. No se guardó ningún dato";
		}
		return null;
	}
	
	public String modificar(){
		if (tipoOferta.getTiof_nombre()==null ||
				tipoOferta.getTiof_nombre().length()==0)	{		
			estado="Introduzca el tipo de oferta";
			return null;
		}				
		gestorTiposOferta.modificar(tipoOferta);
		return null;
	}
	
	public String borrar(){
		if (tipoOferta.getTiof_nombre()==null ||
				tipoOferta.getTiof_nombre().length()==0)	{		
			return null;
		}				
		try {
			gestorTiposOferta.borrar(tipoOferta);
			tipoOferta.vaciar();
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
		TipoOferta aux = gestorTiposOferta.buscar(tipoOferta.getTiof_codigo());
		tipoOferta.copiarValores(aux);
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
		tipoOferta.vaciar();
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
