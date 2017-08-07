package net.gefco.bbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import net.gefco.modelo.UO;
import net.gefco.negocio.GestorUOs;

import org.springframework.dao.DataIntegrityViolationException;

public class UOsBB {

	private String estado;
	private UO uO;	
	private GestorUOs gestorUOs;
		
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}	

	public UO getuO() {
		return uO;
	}

	public void setuO(UO uO) {
		this.uO = uO;
	}

	public GestorUOs getGestorUOs() {
		return gestorUOs;
	}

	public void setGestorUOs(GestorUOs gestorUOs) {
		this.gestorUOs = gestorUOs;
	}
	
	public List<UO> getListaUOs() {		
		return gestorUOs.listarTodos();
	}

	public List<SelectItem> getListaUOsSI() {
		List<UO> lista = gestorUOs.listarTodos();
		List<SelectItem> listaSI = new ArrayList<SelectItem>();
		listaSI.add(new SelectItem(null,"Seleccione..."));
		for(UO uO: lista){
			SelectItem si = new SelectItem(uO.getuO_codigo(), uO.getuO_nombre());
			listaSI.add(si);			
		}
		return listaSI;
	}

	public String insertar(){
		if (uO.getuO_nombre()==null ||
				uO.getuO_nombre().length()==0)	{		
			estado="Introduzca la unidad operacional";
			return null;
		}
		try {
			gestorUOs.insertar(uO);
			estado = "Registro guardado";
		} catch (DataIntegrityViolationException e) {
			estado = "Valor incorrecto. No se guardó ningún dato";
		}
		return null;
	}
	
	public String modificar(){
		if (uO.getuO_nombre()==null ||
				uO.getuO_nombre().length()==0)	{		
			estado="Introduzca la unidad operacional";
			return null;
		}
		gestorUOs.modificar(uO);
		return null;
	}
	
	public String borrar(){
		if (uO.getuO_nombre()==null ||
				uO.getuO_nombre().length()==0)	{		
			return null;
		}
		try {
			gestorUOs.borrar(uO);
			uO.vaciar();
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
		UO aux = gestorUOs.buscar(uO.getuO_codigo());
		uO.copiarValores(aux);
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
		uO.vaciar();
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
