package net.gefco.bbean;

import java.util.List;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import net.gefco.modelo.HistoricoOferta;
import net.gefco.negocio.GestorHistoricoOfertas;

import org.springframework.dao.DataIntegrityViolationException;

public class HistoricoOfertasBB {
	private String estado;
	private HistoricoOferta historicoOferta;	
	private GestorHistoricoOfertas gestorHistoricoOfertas;
		
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public HistoricoOferta getHistoricoOferta() {
		return historicoOferta;
	}

	public void setHistoricoOferta(HistoricoOferta historicoOferta) {
		this.historicoOferta = historicoOferta;
	}

	public void setGestorHistoricoOfertas(GestorHistoricoOfertas gestorHistoricoOfertas) {
		this.gestorHistoricoOfertas = gestorHistoricoOfertas;
	}

	public List<HistoricoOferta> getListaHistoricoOfertas() {		
		return gestorHistoricoOfertas.listarTodos();
	}

	
	public String insertar(){
		try {
			gestorHistoricoOfertas.insertarHistoricoOferta(historicoOferta);
		} catch (DataIntegrityViolationException e) {	
			estado = "Valor incorrecto. No se guardó ningún dato";
		}
		return null;
	}
	
	public String modificar(){
		gestorHistoricoOfertas.modificarHistoricoOferta(historicoOferta);
		return null;
	}
	
	public String borrar(){
		try {
			gestorHistoricoOfertas.borrarHistoricoOferta(historicoOferta);
			historicoOferta.vaciar();
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
		HistoricoOferta aux = gestorHistoricoOfertas.buscar(historicoOferta.getHiof_codigo());
		historicoOferta.copiarValores(aux);
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
		historicoOferta.vaciar();
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
