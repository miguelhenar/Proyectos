package net.gefco.bbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import net.gefco.modelo.MotivoPerdida;
import net.gefco.negocio.GestorMotivosPerdida;

import org.springframework.dao.DataIntegrityViolationException;

public class MotivosPerdidaBB {

	private String estado;
	private MotivoPerdida motivoPerdida;
	private GestorMotivosPerdida gestorMotivosPerdida;
	
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public MotivoPerdida getMotivoPerdida() {
		return motivoPerdida;
	}

	public void setMotivoPerdida(MotivoPerdida motivoPerdida) {
		this.motivoPerdida = motivoPerdida;
	}

	public void setGestorMotivosPerdida(GestorMotivosPerdida gestorMotivosPerdida) {
		this.gestorMotivosPerdida = gestorMotivosPerdida;
	}

	public List<MotivoPerdida> getListaMotivosPerdida() {		
		return gestorMotivosPerdida.listarTodos();
	}

	public List<SelectItem> getListaMotivosPerdidaSI() {
		List<MotivoPerdida> lista = gestorMotivosPerdida.listarTodosOrdenado("mope_codigo");
		List<SelectItem> listaSI = new ArrayList<SelectItem>();
		for(MotivoPerdida motivoPerdida: lista){
			SelectItem si = new SelectItem(motivoPerdida.getMope_codigo(), motivoPerdida.getMope_nombre());
			listaSI.add(si);
		}
		return listaSI;
	}

	public String insertar(){
		if (motivoPerdida.getMope_nombre()==null ||
				motivoPerdida.getMope_nombre().length()==0)	{		
			estado="Introduzca el motivo";
			return null;
		}	
		try {
			gestorMotivosPerdida.insertar(motivoPerdida);
			estado = "Registro guardado";
		} catch (DataIntegrityViolationException e) {	
			estado = "Valor incorrecto. No se guardó ningún dato";
		}
		return null;
	}
	
	public String modificar(){
		if (motivoPerdida.getMope_nombre()==null ||
				motivoPerdida.getMope_nombre().length()==0)	{		
			estado="Introduzca el motivo";
			return null;
		}	
		gestorMotivosPerdida.modificar(motivoPerdida);
		return null;
	}
	
	public String borrar(){
		if (motivoPerdida.getMope_nombre()==null ||
				motivoPerdida.getMope_nombre().length()==0)	{			
			return null;
		}
		try {
			gestorMotivosPerdida.borrar(motivoPerdida);
			motivoPerdida.vaciar();
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
		MotivoPerdida aux = gestorMotivosPerdida.buscar(motivoPerdida.getMope_codigo());
		motivoPerdida.copiarValores(aux);
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
		motivoPerdida.vaciar();
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
