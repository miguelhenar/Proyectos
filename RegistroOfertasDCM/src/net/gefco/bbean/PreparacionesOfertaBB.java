package net.gefco.bbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import net.gefco.manejoDataTable.PreparacionesOfertaDT;
import net.gefco.modelo.PreparacionOferta;
import net.gefco.negocio.GestorPreparacionesOferta;

import org.springframework.dao.DataIntegrityViolationException;

public class PreparacionesOfertaBB {

	private String estado;
	private PreparacionOferta preparacionOferta;
	private GestorPreparacionesOferta gestorPreparacionesOferta;
	private PreparacionesOfertaDT preparacionesOfertaDT;
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public PreparacionOferta getPreparacionOferta() {
		return preparacionOferta;
	}

	public void setPreparacionOferta(PreparacionOferta preparacionOferta) {
		this.preparacionOferta = preparacionOferta;
	}

	public void setGestorPreparacionesOferta(GestorPreparacionesOferta gestorPreparacionesOferta) {
		this.gestorPreparacionesOferta = gestorPreparacionesOferta;
	}

	public GestorPreparacionesOferta getGestorPreparacionesOferta() {
		return gestorPreparacionesOferta;
	}

	public PreparacionesOfertaDT getPreparacionesOfertaDT() {
		return preparacionesOfertaDT;
	}

	public void setPreparacionesOfertaDT(PreparacionesOfertaDT preparacionesOfertaDT) {
		this.preparacionesOfertaDT = preparacionesOfertaDT;
	}

	public List<PreparacionOferta> getListaPreparacionesOferta() {	
		return gestorPreparacionesOferta.listarTodos();
	}

	public List<SelectItem> getListaPreparacionesOfertaSI() {
		List<PreparacionOferta> lista = gestorPreparacionesOferta.listarTodos();
		List<SelectItem> listaSI = new ArrayList<SelectItem>();
		listaSI.add(new SelectItem(null,"Seleccione..."));
		for(PreparacionOferta PrepOf: lista){
			SelectItem si = new SelectItem(PrepOf.getProf_codigo(), PrepOf.getUo().getuO_nombre()+"/"+PrepOf.getProf_equipo());
			listaSI.add(si);
		}
		return listaSI;
	}

	public String insertar(){
		if (preparacionOferta.getProf_equipo()==null ||
				preparacionOferta.getProf_equipo().length()==0)	{		
			estado="Introduzca el equipo";
			return "verPreparacionesOferta";
		}	
		if (preparacionOferta.getUo().getuO_codigo()==0) {		
			estado="seleccione la UO";
			return "verPreparacionesOferta";
		}
		try {
			gestorPreparacionesOferta.insertar(preparacionOferta);
			estado = "Registro guardado";
		} catch (DataIntegrityViolationException e) {	
			estado = "Valor incorrecto. No se guardó ningún dato";
		}
		preparacionesOfertaDT.setPanelVisible("");
		return "verPreparacionesOferta";		
	}
	
	public String modificar(){
		if (preparacionOferta.getProf_equipo()==null ||
				preparacionOferta.getProf_equipo().length()==0)	{		
			estado="Introduzca el equipo";
			return "verPreparacionesOferta";
		}	
		if (preparacionOferta.getUo().getuO_codigo()==0) {		
			estado="seleccione la UO";
			return "verPreparacionesOferta";
		}		
		
		try{
			gestorPreparacionesOferta.modificar(preparacionOferta);
			//estado = "Registro guardado";
			preparacionesOfertaDT.setPanelVisible("");
		} catch (DataIntegrityViolationException e) {	
				estado = "Valor incorrecto. No se guardó ningún dato. Revise si el dato que está intentando crear ya existe";
		}
		return "verPreparacionesOferta";		
	}
	
	public String borrar(){
		if (preparacionOferta.getProf_equipo()==null ||
				preparacionOferta.getProf_equipo().length()==0)	{		
			return "verPreparacionesOferta";
		}	
		if (preparacionOferta.getUo().getuO_codigo()==0) {		
			estado="seleccione la UO";
			return "verPreparacionesOferta";
		}
		try {
			gestorPreparacionesOferta.borrar(preparacionOferta);
			preparacionOferta.vaciar();
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
		preparacionesOfertaDT.setPanelVisible("panelEdicion");
		return "verPreparacionesOferta";		
	}
	
	public String seleccionar(){
		PreparacionOferta aux = gestorPreparacionesOferta.buscar(preparacionOferta.getProf_codigo());
		preparacionOferta.copiarValores(aux);
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
		preparacionesOfertaDT.setPanelVisible("panelEdicion");
		return "verPreparacionesOferta";		
	}
	
//	public String vaciar(){
//		preparacionOferta.vaciar();
//		//Pedimos el facesContext
//		FacesContext fCtx = FacesContext.getCurrentInstance();
//		//Obtenemos el Id del árbol que se está visualizando actualmente
//		String vId = fCtx.getViewRoot().getViewId(); 
//		//Obtenemos el Viewhandler
//		ViewHandler vh = fCtx.getApplication().getViewHandler();
//		//Le pedimos al ViewHandler que cree un nuevo árbol (vacío) con el id del antiguo
//		UIViewRoot nuevoArbol = vh.createView(fCtx, vId);
//		//Sustituimos el viejo por el nuevo
//		fCtx.setViewRoot(nuevoArbol);
//		return null;
//	}
	
	public String nuevo(){
		preparacionOferta.vaciar();
		preparacionesOfertaDT.setPanelVisible("panelEdicion");
		return  "verPreparacionesOferta";
	}
	
	public String cancelar(){
		preparacionOferta.vaciar();
		  preparacionesOfertaDT.setPanelVisible("");
	      return "verPreparacionesOferta";
	  }
}
