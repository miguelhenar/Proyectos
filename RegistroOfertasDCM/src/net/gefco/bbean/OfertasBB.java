
package net.gefco.bbean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import net.gefco.modelo.HistoricoOferta;
import net.gefco.modelo.Oferta;
import net.gefco.modelo.Usuario;
import net.gefco.modelo.dto.CBAlerta;
import net.gefco.modelo.dto.CBOferta;
import net.gefco.negocio.GestorAlertas;
import net.gefco.negocio.GestorHistoricoOfertas;
import net.gefco.negocio.GestorOfertas;
import net.gefco.util.JReportsUtil;

public class OfertasBB {

	//private static String REPORT_PATH = "C:\\GefcoWeb\\Ej41ProvisionesTabs\\ResumenDetalleProvision.jasper";
	//private static String REPORT_EXPORT_PATH_EXCEL = "D:\\ListadoOfertas.xls";
	//private static String REPORT_EXPORT_PATH_PDF = "C:\\GefcoWeb\\Ej41ProvisionesTabs\\ResumenDetalleProvision.pdf";
	
	
	//Este es el usuario que se ha logado
	private Usuario usuario;
	//este es la oferta que se utiliza en registroModificacionOferta
	private Oferta oferta;
	//Criterio Busqueda Oferta: se usa para la búsqueda
	private CBOferta CBOferta;	
	private CBAlerta CBAlerta;
	
	private String estado;
	
	//Objeto de negocio
	private GestorOfertas gestorOfertas;
	private GestorAlertas gestorAlertas;
	private GestorHistoricoOfertas gestorHistoricoOfertas;

		
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Oferta getOferta() {
		return oferta;
	}
	
	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}
			
	public GestorAlertas getGestorAlertas() {
		return gestorAlertas;
	}

	public void setGestorAlertas(GestorAlertas gestorAlertas) {
		this.gestorAlertas = gestorAlertas;
	}

	public void setGestorOfertas(GestorOfertas gestorOfertas) {
		this.gestorOfertas = gestorOfertas;
	}	
	
	public GestorOfertas getGestorOfertas() {
		return gestorOfertas;
	}
	
	public void setGestorHistoricoOfertas(GestorHistoricoOfertas gestorHistoricoOfertas) {
		this.gestorHistoricoOfertas = gestorHistoricoOfertas;
	}	
	
	public GestorHistoricoOfertas getHistoricoGestorOfertas() {
		return gestorHistoricoOfertas;
	}
	
	public CBAlerta getCBAlerta() {
		return CBAlerta;		
	}

	public void setCBAlerta(CBAlerta cBAlerta) {
		CBAlerta = cBAlerta;
		//Le asignamos el criterio de búsqueda el usuario logado
		//Si no hacemos esto, al entrar en la pantalla aparecen
		//los datos de s los usuarios.
		if (!usuario.getUsua_rol().getRol_codigo().equals("ADMINISTRADOR") && 
			!usuario.getUsua_rol().getRol_codigo().equals("VISUALIZACION")){
			Usuario uAux = new Usuario();
			uAux.copiarValores(usuario, true);
			CBAlerta.setUsuario(uAux);
		}
	}

	public CBOferta getCBOferta() {
		return CBOferta;
	}
	
	public void setCBOferta(CBOferta cBOferta) {
		CBOferta = cBOferta;
		//Le asignamos el criterio de búsqueda el usuario logado
		//Si no hacemos esto, al entrar en la pantalla aparecen
		//los datos de s los usuarios.
		if (!usuario.getUsua_rol().getRol_codigo().equals("ADMINISTRADOR") &&
			!usuario.getUsua_rol().getRol_codigo().equals("VISUALIZACION")){
			Usuario uAux = new Usuario();
			uAux.copiarValores(usuario, true);
			CBOferta.setUsuario(uAux);
		}
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getEstado() {
		return estado;
	}     

	//devuelve un String:la regla de navegación (en este caso devolvemos null pq nos quedamos en la m isma página)
	//los més de las BB que responden a botones devuelven reglas de navegación
	public String vaciarCBOferta() {
		CBOferta.vaciar();

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
	
	public String vaciarCBAlerta() {
		CBAlerta.vaciar();
		if (usuario != null && CBAlerta.getFechaTopeF()==null) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, usuario.getUsua_dias_alerta());
			CBAlerta.setFechaTopeF(c.getTime());
		}

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
	
	public String vaciarFormularioOferta(){
		oferta.vaciar();
		estado=null;
		
		//Con immediate=true no podemos modificar el contenido de los campos del formulario.
		//lo que hacemos aquí es crear un nuevo árbol para sustituir al que queremos vaciar.		
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
		
	@SuppressWarnings("deprecation")
	public String seleccionarOferta(){
		
		//Sólo tengo el Id y tengo que completar la BB. Reemplazamos la BB inyectada con los valores del
		//detalle provisión del id correspondoente
		//buscamos en la BBDD el detalle provisión que se ha pulsado
		Oferta oAux = gestorOfertas.buscar(oferta.getOfer_codigo());
		//Copiamos los datos en la oferta que está en la sesión y que es 
		//la que se usa para rellenar el formulario.
		oferta.copiarValores(oAux);
		
		//Con immediate=true no podemos modificar el contenido de los campos del formulario.
		//lo que hacemos aquí es crear un nuevo árbol para sustituir al que queremos vaciar.		
		//Pedimos el facesContext
		FacesContext fCtx = FacesContext.getCurrentInstance();
		//Obtenemos el Id del árbol que se está visualizando actualmente
		String vId = fCtx.getViewRoot().getViewId(); 
		//Obtenemos el Viewhandler
		ViewHandler vh = fCtx.getApplication().getViewHandler();
		//Le pedimos al ViewHandler que cree un nuevo árbol (vacío) con el id del antiguo
		UIViewRoot nuevoArbol = vh.createView(fCtx, vId);
		
		//Recuperamos el objeto PermisosMenuBB del árbol y cambiamos la propiedad
		//OpNivel1 para que se seleccione su título correspondiente en el menú de la página
		PermisosMenuBB p = (PermisosMenuBB) fCtx.getApplication().getVariableResolver().resolveVariable(fCtx, "permisosMenuBB");
//        p.setOpNivel1("inicial");
		p.setPaginaAnterior("listadoOfertas");
		p.setOpSeleccionada("editarOferta");
        		
		//Sustituimos el árbol viejo por el nuevo
		fCtx.setViewRoot(nuevoArbol);
		
		estado=null;
		
		return p.abrirPagina();
	}
	
	@SuppressWarnings("deprecation")
	public String seleccionarAlerta(){
		//Sólo tengo el Id y tengo que completar la BB. Reemplazamos la BB inyectada con los valores del
		//detalle provisión del id correspondoente
		//buscamos en la BBDD el detalle provisión que se ha pulsado
		Oferta oAux = gestorOfertas.buscar(oferta.getOfer_codigo());
		//Copiamos los datos en la oferta que está en la sesión y que es 
		//la que se usa para rellenar el formulario.
		oferta.copiarValores(oAux);
		
		//Con immediate=true no podemos modificar el contenido de los campos del formulario.
		//lo que hacemos aquí es crear un nuevo árbol para sustituir al que queremos vaciar.		
		//Pedimos el facesContext
		FacesContext fCtx = FacesContext.getCurrentInstance();
		//Obtenemos el Id del árbol que se está visualizando actualmente
		String vId = fCtx.getViewRoot().getViewId(); 
		//Obtenemos el Viewhandler
		ViewHandler vh = fCtx.getApplication().getViewHandler();
		//Le pedimos al ViewHandler que cree un nuevo árbol (vacío) con el id del antiguo
		UIViewRoot nuevoArbol = vh.createView(fCtx, vId);
		
		//Recuperamos el objeto PermisosMenuBB del árbol y cambiamos la propiedad
		//OpNivel1 para que se seleccione su título correspondiente en el menú de la página
		PermisosMenuBB p = (PermisosMenuBB) fCtx.getApplication().getVariableResolver().resolveVariable(fCtx, "permisosMenuBB");
//        p.setOpNivel1("inicial");
		p.setPaginaAnterior("listadoAlertas");
		p.setOpSeleccionada("listadoAlertas");
        
		
		//Sustituimos el árbol viejo por el nuevo
		fCtx.setViewRoot(nuevoArbol);
		
		estado=null;
		
		return "verInicial";
	}
	
	public List<Oferta> getListaOfertas(){	
		return gestorOfertas.listarOfertas(CBOferta,usuario);
	}
	
	public List<Oferta> getListaAlertas(){	
		if (usuario != null && CBAlerta.getFechaTopeF()==null) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, usuario.getUsua_dias_alerta());
			CBAlerta.setFechaTopeF(c.getTime());
		}

		return gestorAlertas.listarAlertas(CBAlerta,usuario);
	}	
	
	public String insertar() {
		HistoricoOferta auxHistoricoOferta=new HistoricoOferta();
		oferta.setOfer_fechaRegistro(new Date());
		gestorOfertas.insertarOferta(oferta);		
		auxHistoricoOferta.copiarValoresOferta(oferta);
		gestorHistoricoOfertas.insertarHistoricoOferta(auxHistoricoOferta);
		estado="La oferta "+oferta.getOfer_codigo()+" ha sido guardada";
		oferta.setOfer_codigo(0);				
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public String modificar() {		
		HistoricoOferta auxHistoricoOferta=new HistoricoOferta();		
		gestorOfertas.modificarOferta(oferta);		
		auxHistoricoOferta.copiarValoresOferta(oferta);
		auxHistoricoOferta.setOfer_fechaRegistro(new Date());
		gestorHistoricoOfertas.insertarHistoricoOferta(auxHistoricoOferta);
		//Recuperamos el objeto PermisosMenuBB del árbol y cambiamos la propiedad
		//OpNivel1 para que se seleccione su título correspondiente en el menú de la página
		//Primer pedimos el facesContext
		FacesContext fCtx = FacesContext.getCurrentInstance();
		PermisosMenuBB p = (PermisosMenuBB) fCtx.getApplication().getVariableResolver().resolveVariable(fCtx, "permisosMenuBB");
        if (p.getPaginaAnterior() == "listadoOfertas") {
        	//p.setOpNivel1("listado_ofertas");
        	p.setOpSeleccionada("listado_ofertas");
        	oferta.vaciar();		
        	return "verListadoOfertas";
        }
        else if (p.getPaginaAnterior() == "listadoAlertas") {
//        	p.setOpNivel1("listado_alertas");
        	p.setOpSeleccionada("listado_alertas");
        	oferta.vaciar();		
        	return "verListadoAlertas";
        }
        else return null;
	}

	@SuppressWarnings("deprecation")
	public String cancelar() {
		oferta.vaciar();
		estado=null;
		
		//Con immediate=true no podemos modificar el contenido de los campos del formulario.
		//lo que hacemos aquí es crear un nuevo árbol para sustituir al que queremos vaciar.		
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
		
		PermisosMenuBB p = (PermisosMenuBB) fCtx.getApplication().getVariableResolver().resolveVariable(fCtx, "permisosMenuBB");
        if (p.getPaginaAnterior() == "listadoOfertas") {
//        	p.setOpNivel1("listado_ofertas");
        	p.setOpSeleccionada("listado_ofertas");
        	oferta.vaciar();		
        	return "verListadoOfertas";
        }
        else if (p.getPaginaAnterior() == "listadoAlertas") {
        	//p.setOpNivel1("listado_alertas");
        	p.setOpSeleccionada("listado_alertas");
        	oferta.vaciar();
        	return "verListadoAlertas";
        }
        else return null;
	}
	
	@SuppressWarnings("deprecation")
	public String borrar() {
		gestorOfertas.borrarOferta(oferta);
		
		//Recuperamos el objeto PermisosMenuBB del árbol y cambiamos la propiedad
		//OpNivel1 para que se seleccione su título correspondiente en el menú de la página
		//Primer pedimos el facesContext
		FacesContext fCtx = FacesContext.getCurrentInstance();
		PermisosMenuBB p = (PermisosMenuBB) fCtx.getApplication().getVariableResolver().resolveVariable(fCtx, "permisosMenuBB");
        if (p.getPaginaAnterior() == "listadoOfertas") {
//        	p.setOpNivel1("listado_ofertas");
        	p.setOpSeleccionada("listadoOfertas");
        	oferta.vaciar();		
        	return "verListadoOfertas";
        }
        else if (p.getPaginaAnterior() == "listadoAlertas") {
//        	p.setOpNivel1("listado_alertas");
        	p.setOpSeleccionada("listadoAlertas");
        	oferta.vaciar();		
        	return "verListadoAlertas";
        }
        else return p.abrirPagina();        
	}
	//botón nuevo
	public String nuevo(){
		estado=null;
		//gestorOfertas.borrarOferta(oferta);
		oferta.setOfer_codigo(0);
		return null;
	}

//Validaciones
	public void validarCampos(FacesContext fc, UIComponent uic, Object o) {
			
		//validarMaduracion
		UIComponent maduracion = uic.getParent().findComponent("ofer_maduracion");        
		String valorMaduracion = (""+((EditableValueHolder) maduracion).getSubmittedValue()).toString();   
		
        UIComponent motivoPerdida = uic.getParent().findComponent("ofer_motivoPerdida");
        String valorMotivoPerdida = ((EditableValueHolder) motivoPerdida).getSubmittedValue().toString();
                
        if (valorMotivoPerdida.equals("1") && valorMaduracion.equals("6")) {
                 ResourceBundle resourceBundle = ResourceBundle.getBundle(
                                           "net.gefco.i18n.errores", 
                                           fc.getViewRoot().getLocale());
                 String strTextoError = resourceBundle.getString("error_perdida_sin_motivo_relleno");
                 throw new ValidatorException(new FacesMessage(strTextoError));
              
        }
        
        if (!valorMotivoPerdida.equals("1") && !valorMaduracion.equals("6")) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("net.gefco.i18n.errores",fc.getViewRoot().getLocale());
            String strTextoError = resourceBundle.getString("error_motivo_perdida_relleno");
            throw new ValidatorException(new FacesMessage(strTextoError));
         
        }
       
        //validarOvl
        UIComponent cotizadoOvl = uic.getParent().findComponent("ofer_cotizadoOvl");
        String valorCotizadoOvl = ((EditableValueHolder) cotizadoOvl).getSubmittedValue().toString().replace("0.0", "");
        
        UIComponent conseguidoOvl = uic.getParent().findComponent("ofer_conseguidoOvl");
        String valorConseguidoOvl = ((EditableValueHolder) conseguidoOvl).getSubmittedValue().toString().replace("0.0", "");
        
        UIComponent renovadoOvl = uic.getParent().findComponent("ofer_renovadoOvl");
        String valorRenovadoOvl = ((EditableValueHolder) renovadoOvl).getSubmittedValue().toString().replace("0.0", "");
        
        UIComponent ovlGrupaje = uic.getParent().findComponent("ofer_ovlGrupaje");
        String valorOvlGrupaje = ((EditableValueHolder) ovlGrupaje).getSubmittedValue().toString();
        
        UIComponent ovlLtl = uic.getParent().findComponent("ofer_ovlLtl");
        String valorOvlLtl = ((EditableValueHolder) ovlLtl).getSubmittedValue().toString();
        
        UIComponent ovlFtl = uic.getParent().findComponent("ofer_ovlFtl");
        String valorOvlFtl = ((EditableValueHolder) ovlFtl).getSubmittedValue().toString();
        
        UIComponent ovlXdock = uic.getParent().findComponent("ofer_ovlXdock");
        String valorOvlXdock = ((EditableValueHolder) ovlXdock).getSubmittedValue().toString();
        
        UIComponent ovlMaf = uic.getParent().findComponent("ofer_ovlMaf");
        String valorOvlMaf = ((EditableValueHolder) ovlMaf).getSubmittedValue().toString();
        
		if ((valorOvlGrupaje.equals("true") || valorOvlLtl.equals("true")
				|| valorOvlFtl.equals("true") || valorOvlXdock.equals("true") || valorOvlMaf.equals("true")) 
				&& valorCotizadoOvl.equals("") && valorConseguidoOvl.equals("") && valorRenovadoOvl.equals("")) {
                 ResourceBundle resourceBundle = ResourceBundle.getBundle("net.gefco.i18n.errores",fc.getViewRoot().getLocale());
                 String strTextoError = resourceBundle.getString("error_ovl_sin_importe");
                 throw new ValidatorException(new FacesMessage(strTextoError));              
        }  
		if ((!valorCotizadoOvl.equals("") || !valorConseguidoOvl.equals("") || !valorRenovadoOvl.equals("") ) 
				&& valorOvlGrupaje.equals("false")&& valorOvlLtl.equals("false")
				&& valorOvlFtl.equals("false") && valorOvlXdock.equals("false") && valorOvlMaf.equals("false")) {
                 ResourceBundle resourceBundle = ResourceBundle.getBundle("net.gefco.i18n.errores",fc.getViewRoot().getLocale());
                 String strTextoError = resourceBundle.getString("error_ovl_sin_actividad");
                 throw new ValidatorException(new FacesMessage(strTextoError));              
        }
        
		
		//validarOvs
		UIComponent cotizadoOvs = uic.getParent().findComponent("ofer_cotizadoOvs");
        String valorCotizadoOvs = ((EditableValueHolder) cotizadoOvs).getSubmittedValue().toString().replace("0.0", "");
        
        UIComponent conseguidoOvs = uic.getParent().findComponent("ofer_conseguidoOvs");
        String valorConseguidoOvs = ((EditableValueHolder) conseguidoOvs).getSubmittedValue().toString().replace("0.0", "");
        
        UIComponent renovadoOvs = uic.getParent().findComponent("ofer_renovadoOvs");
        String valorRenovadoOvs = ((EditableValueHolder) renovadoOvs).getSubmittedValue().toString().replace("0.0", "");
	        
        UIComponent ovsGrupaje = uic.getParent().findComponent("ofer_ovsGrupaje");
        String valorOvsGrupaje = ((EditableValueHolder) ovsGrupaje).getSubmittedValue().toString();
        
        UIComponent ovsLcl = uic.getParent().findComponent("ofer_ovsLcl");
        String valorOvsLcl = ((EditableValueHolder) ovsLcl).getSubmittedValue().toString();
        
        UIComponent ovsFcl = uic.getParent().findComponent("ofer_ovsFcl");
        String valorOvsFcl = ((EditableValueHolder) ovsFcl).getSubmittedValue().toString();
        
        UIComponent ovsFtl = uic.getParent().findComponent("ofer_ovsFtl");
        String valorOvsFtl = ((EditableValueHolder) ovsFtl).getSubmittedValue().toString();
        
        UIComponent ovsAereo = uic.getParent().findComponent("ofer_ovsAereo");
        String valorOvsAereo = ((EditableValueHolder) ovsAereo).getSubmittedValue().toString();
        
        if ((valorOvsGrupaje.equals("true") || valorOvsLcl.equals("true")
				|| valorOvsFcl.equals("true") || valorOvsFtl.equals("true") || valorOvsAereo.equals("true")) 
				&& valorCotizadoOvs.equals("") && valorConseguidoOvs.equals("") && valorRenovadoOvs.equals("")) {
                 ResourceBundle resourceBundle = ResourceBundle.getBundle("net.gefco.i18n.errores",fc.getViewRoot().getLocale());
                 String strTextoError = resourceBundle.getString("error_ovs_sin_importe");
                 throw new ValidatorException(new FacesMessage(strTextoError));              
        }  
		if ((!valorCotizadoOvs.equals("") || !valorConseguidoOvs.equals("") || !valorRenovadoOvs.equals("") ) 
				&& valorOvsGrupaje.equals("false")&& valorOvsLcl.equals("false")
				&& valorOvsFcl.equals("false") && valorOvsFtl.equals("false") && valorOvsAereo.equals("false")) {
                 ResourceBundle resourceBundle = ResourceBundle.getBundle("net.gefco.i18n.errores",fc.getViewRoot().getLocale());
                 String strTextoError = resourceBundle.getString("error_ovs_sin_actividad");
                 throw new ValidatorException(new FacesMessage(strTextoError));              
        }
       		
			
		//validarIli
		UIComponent cotizadoIli = uic.getParent().findComponent("ofer_cotizadoIli");
        String valorCotizadoIli = ((EditableValueHolder) cotizadoIli).getSubmittedValue().toString().replace("0.0", "");
        
        UIComponent conseguidoIli = uic.getParent().findComponent("ofer_conseguidoIli");
        String valorConseguidoIli = ((EditableValueHolder) conseguidoIli).getSubmittedValue().toString().replace("0.0", "");
        
        UIComponent renovadoIli = uic.getParent().findComponent("ofer_renovadoIli");
        String valorRenovadoIli = ((EditableValueHolder) renovadoIli).getSubmittedValue().toString().replace("0.0", "");
        
        UIComponent iliAlmacenaje = uic.getParent().findComponent("ofer_iliAlmacenaje");
        String valorIliAlmacenaje = ((EditableValueHolder) iliAlmacenaje).getSubmittedValue().toString();
        
        UIComponent iliTransporte = uic.getParent().findComponent("ofer_iliTransporte");
        String valorIliTransporte = ((EditableValueHolder) iliTransporte).getSubmittedValue().toString();
                  
        if ((valorIliAlmacenaje.equals("true") || valorIliTransporte.equals("true")) 
				&& valorCotizadoIli.equals("") && valorConseguidoIli.equals("") && valorRenovadoIli.equals("")) {
                 ResourceBundle resourceBundle = ResourceBundle.getBundle("net.gefco.i18n.errores",fc.getViewRoot().getLocale());
                 String strTextoError = resourceBundle.getString("error_ili_sin_importe");
                 throw new ValidatorException(new FacesMessage(strTextoError));              
        }  
		if ((!valorCotizadoIli.equals("") || !valorConseguidoIli.equals("") || !valorRenovadoIli.equals("") ) 
				&& valorIliAlmacenaje.equals("false")&& valorIliTransporte.equals("false")) {
                 ResourceBundle resourceBundle = ResourceBundle.getBundle("net.gefco.i18n.errores",fc.getViewRoot().getLocale());
                 String strTextoError = resourceBundle.getString("error_ili_sin_actividad");
                 throw new ValidatorException(new FacesMessage(strTextoError));              
        }    
		
		//validarTla
		UIComponent cotizadoTla = uic.getParent().findComponent("ofer_cotizadoTla");
        String valorCotizadoTla = ((EditableValueHolder) cotizadoTla).getSubmittedValue().toString().replace("0.0", "");
        
        UIComponent conseguidoTla = uic.getParent().findComponent("ofer_conseguidoTla");
        String valorConseguidoTla = ((EditableValueHolder) conseguidoTla).getSubmittedValue().toString().replace("0.0", "");
        
        UIComponent renovadoTla = uic.getParent().findComponent("ofer_renovadoTla");
        String valorRenovadoTla = ((EditableValueHolder) renovadoTla).getSubmittedValue().toString().replace("0.0", "");
        
        UIComponent tlaTransporte = uic.getParent().findComponent("ofer_tlaTransporte");
        String valorTlaTransporte = ((EditableValueHolder) tlaTransporte).getSubmittedValue().toString();
        
        UIComponent tlaPvno = uic.getParent().findComponent("ofer_tlaPvno");
        String valorTlaPvno = ((EditableValueHolder) tlaPvno).getSubmittedValue().toString();
                  
        if ((valorTlaTransporte.equals("true") || valorTlaPvno.equals("true")) 
				&& valorCotizadoTla.equals("") && valorConseguidoTla.equals("") && valorRenovadoTla.equals("")) {
                 ResourceBundle resourceBundle = ResourceBundle.getBundle("net.gefco.i18n.errores",fc.getViewRoot().getLocale());
                 String strTextoError = resourceBundle.getString("error_tla_sin_importe");
                 throw new ValidatorException(new FacesMessage(strTextoError));              
        }  
		if ((!valorCotizadoTla.equals("") || !valorConseguidoTla.equals("") || !valorRenovadoTla.equals("") ) 
				&& valorTlaTransporte.equals("false")&& valorTlaPvno.equals("false")) {
                 ResourceBundle resourceBundle = ResourceBundle.getBundle("net.gefco.i18n.errores",fc.getViewRoot().getLocale());
                 String strTextoError = resourceBundle.getString("error_tla_sin_actividad");
                 throw new ValidatorException(new FacesMessage(strTextoError));              
        }       
	
		//validarGsm
		UIComponent cotizadoGsm = uic.getParent().findComponent("ofer_cotizadoGsm");
        String valorCotizadoGsm = ((EditableValueHolder) cotizadoGsm).getSubmittedValue().toString().replace("0.0", "");
        
        UIComponent conseguidoGsm = uic.getParent().findComponent("ofer_conseguidoGsm");
        String valorConseguidoGsm = ((EditableValueHolder) conseguidoGsm).getSubmittedValue().toString().replace("0.0", "");
        
        UIComponent renovadoGsm = uic.getParent().findComponent("ofer_renovadoGsm");
        String valorRenovadoGsm = ((EditableValueHolder) renovadoGsm).getSubmittedValue().toString().replace("0.0", "");
        
        UIComponent gsmTransporte = uic.getParent().findComponent("ofer_gsmTransporte");
        String valorGsmTransporte = ((EditableValueHolder) gsmTransporte).getSubmittedValue().toString();
        
        if ((valorGsmTransporte.equals("true")) 
				&& valorCotizadoGsm.equals("") && valorConseguidoGsm.equals("") && valorRenovadoGsm.equals("")) {
                 ResourceBundle resourceBundle = ResourceBundle.getBundle("net.gefco.i18n.errores",fc.getViewRoot().getLocale());
                 String strTextoError = resourceBundle.getString("error_gsm_sin_importe");
                 throw new ValidatorException(new FacesMessage(strTextoError));              
        }  
		if ((!valorCotizadoGsm.equals("") || !valorConseguidoGsm.equals("") || !valorRenovadoGsm.equals("") ) 
				&& valorGsmTransporte.equals("false")) {
                 ResourceBundle resourceBundle = ResourceBundle.getBundle("net.gefco.i18n.errores",fc.getViewRoot().getLocale());
                 String strTextoError = resourceBundle.getString("error_gsm_sin_actividad");
                 throw new ValidatorException(new FacesMessage(strTextoError));              
        }
			
	}
	
	
	public void validarEmpresaClienteProspect(FacesContext fc, UIComponent uic, Object o) {
        //String valorCotizadoTla = String.valueOf(o);
        UIComponent esCliente = uic.getParent().findComponent("ofer_esCliente");
        String valorEsCliente = ((EditableValueHolder) esCliente).getSubmittedValue().toString();
        UIComponent esProspect = uic.getParent().findComponent("ofer_esProspect");
        String valorEsProspect = ((EditableValueHolder) esProspect).getSubmittedValue().toString();
        UIComponent esAgente = uic.getParent().findComponent("ofer_esAgente");
        String valorEsAgente = ((EditableValueHolder) esAgente).getSubmittedValue().toString();
        
        int contadorTrues = 0;
        contadorTrues = (valorEsCliente.equals("true")?1:0) + (valorEsProspect.equals("true")?1:0) + (valorEsAgente.equals("true")?1:0);
        
		if (contadorTrues!=1) {
                 ResourceBundle resourceBundle = ResourceBundle.getBundle("net.gefco.i18n.errores",fc.getViewRoot().getLocale());
                 String strTextoError = resourceBundle.getString("error_tipo_cliente");
                 throw new ValidatorException(new FacesMessage(strTextoError));              
        }   		
	}
	
	public String exportarExcel() {
		JReportsUtil.exportarExcel("listadoOfertas.jasper",getListaOfertas(),"listadoOfertas.xls");		
		return null;
	}

	public String exportarExcelHcoOferta() {
		List<HistoricoOferta> historicoOferta = gestorOfertas.listarHcoOferta(oferta.getOfer_codigo());
		JReportsUtil.exportarExcel("listadoHcoOferta.jasper",historicoOferta,"listadoHcoOferta.xls");		
		return null;		
	}
	
//	public void validarMaduracion(FacesContext fc, UIComponent uic, Object o) {
//        String valorMaduracion = String.valueOf(o);        
//        UIComponent motivoPerdida = uic.getParent().findComponent("ofer_motivoPerdida");
//        String valorMotivoPerdida = ((EditableValueHolder) motivoPerdida).getSubmittedValue().toString();
//        
//        if (valorMotivoPerdida.equals("") && valorMaduracion.equals("6")) {
//                 ResourceBundle resourceBundle = ResourceBundle.getBundle(
//                                           "net.gefco.i18n.errores", 
//                                           fc.getViewRoot().getLocale());
//                 String strTextoError = resourceBundle.getString("error_perdida_sin_motivo_relleno");
//                 throw new ValidatorException(new FacesMessage(strTextoError));
//              
//        }
//        
//        if (!valorMotivoPerdida.equals("") && !valorMaduracion.equals("6")) {
//            ResourceBundle resourceBundle = ResourceBundle.getBundle("net.gefco.i18n.errores",fc.getViewRoot().getLocale());
//            String strTextoError = resourceBundle.getString("error_motivo_perdida_relleno");
//            throw new ValidatorException(new FacesMessage(strTextoError));
//         
//        }
//	}	
		
//	public void validarCotizadoOvl(FacesContext fc, UIComponent uic, Object o) {
//        String valorCotizadoOvl = String.valueOf(o);
//        
//        UIComponent ovlGrupaje = uic.getParent().findComponent("ofer_ovlGrupaje");
//        String valorOvlGrupaje = ((EditableValueHolder) ovlGrupaje).getSubmittedValue().toString();
//        
//        UIComponent ovlLtl = uic.getParent().findComponent("ofer_ovlLtl");
//        String valorOvlLtl = ((EditableValueHolder) ovlLtl).getSubmittedValue().toString();
//        
//        UIComponent ovlFtl = uic.getParent().findComponent("ofer_ovlFtl");
//        String valorOvlFtl = ((EditableValueHolder) ovlFtl).getSubmittedValue().toString();
//        
//        UIComponent ovlXdock = uic.getParent().findComponent("ofer_ovlXdock");
//        String valorOvlXdock = ((EditableValueHolder) ovlXdock).getSubmittedValue().toString();
//        
//        UIComponent ovlMaf = uic.getParent().findComponent("ofer_ovlMaf");
//        String valorOvlMaf = ((EditableValueHolder) ovlMaf).getSubmittedValue().toString();
//        
//		if (!valorCotizadoOvl.equals("") && valorOvlGrupaje.equals("false")&& valorOvlLtl.equals("false")
//				&& valorOvlFtl.equals("false") && valorOvlXdock.equals("false") && valorOvlMaf.equals("false")) {
//                 ResourceBundle resourceBundle = ResourceBundle.getBundle("net.gefco.i18n.errores",fc.getViewRoot().getLocale());
//                 String strTextoError = resourceBundle.getString("error_ovl_sin_actividad");
//                 throw new ValidatorException(new FacesMessage(strTextoError));              
//        }        
//	}
	
//	public void validarConseguidoOvl(FacesContext fc, UIComponent uic, Object o) {
//		String valorConseguidoOvl = String.valueOf(o);
//		
//		UIComponent ovlGrupaje = uic.getParent().findComponent("ofer_ovlGrupaje");
//		String valorOvlGrupaje = ((EditableValueHolder) ovlGrupaje).getSubmittedValue().toString();
//		
//		UIComponent ovlLtl = uic.getParent().findComponent("ofer_ovlLtl");
//		String valorOvlLtl = ((EditableValueHolder) ovlLtl).getSubmittedValue().toString();
//		
//		UIComponent ovlFtl = uic.getParent().findComponent("ofer_ovlFtl");
//		String valorOvlFtl = ((EditableValueHolder) ovlFtl).getSubmittedValue().toString();
//		
//		UIComponent ovlXdock = uic.getParent().findComponent("ofer_ovlXdock");
//		String valorOvlXdock = ((EditableValueHolder) ovlXdock).getSubmittedValue().toString();
//		
//		UIComponent ovlMaf = uic.getParent().findComponent("ofer_ovlMaf");
//		String valorOvlMaf = ((EditableValueHolder) ovlMaf).getSubmittedValue().toString();
//		
//		if (!valorConseguidoOvl.equals("") && valorOvlGrupaje.equals("false")&& valorOvlLtl.equals("false")
//				&& valorOvlFtl.equals("false") && valorOvlXdock.equals("false") && valorOvlMaf.equals("false")) {
//			ResourceBundle resourceBundle = ResourceBundle.getBundle("net.gefco.i18n.errores",fc.getViewRoot().getLocale());
//			String strTextoError = resourceBundle.getString("error_ovl_sin_actividad");
//			throw new ValidatorException(new FacesMessage(strTextoError));              
//		}        
//	}
//	
//	public void validarRenovadoOvl(FacesContext fc, UIComponent uic, Object o) {
//        String valorRenovadoOvl = String.valueOf(o);
//        
//        UIComponent ovlGrupaje = uic.getParent().findComponent("ofer_ovlGrupaje");
//        String valorOvlGrupaje = ((EditableValueHolder) ovlGrupaje).getSubmittedValue().toString();
//        
//        UIComponent ovlLtl = uic.getParent().findComponent("ofer_ovlLtl");
//        String valorOvlLtl = ((EditableValueHolder) ovlLtl).getSubmittedValue().toString();
//        
//        UIComponent ovlFtl = uic.getParent().findComponent("ofer_ovlFtl");
//        String valorOvlFtl = ((EditableValueHolder) ovlFtl).getSubmittedValue().toString();
//        
//        UIComponent ovlXdock = uic.getParent().findComponent("ofer_ovlXdock");
//        String valorOvlXdock = ((EditableValueHolder) ovlXdock).getSubmittedValue().toString();
//        
//        UIComponent ovlMaf = uic.getParent().findComponent("ofer_ovlMaf");
//        String valorOvlMaf = ((EditableValueHolder) ovlMaf).getSubmittedValue().toString();
//        
//		if (!valorRenovadoOvl.equals("") && valorOvlGrupaje.equals("false")&& valorOvlLtl.equals("false")
//				&& valorOvlFtl.equals("false") && valorOvlXdock.equals("false") && valorOvlMaf.equals("false")) {
//                 ResourceBundle resourceBundle = ResourceBundle.getBundle(
//                                           "net.gefco.i18n.errores", 
//                                           fc.getViewRoot().getLocale());
//                 String strTextoError = resourceBundle.getString("error_ovl_sin_actividad");
//                 throw new ValidatorException(new FacesMessage(strTextoError));              
//        }        
//	}
	
//	public void validarCotizadoOvs(FacesContext fc, UIComponent uic, Object o) {
//        String valorCotizadoOvs = String.valueOf(o);
//        
//        UIComponent ovsGrupaje = uic.getParent().findComponent("ofer_ovsGrupaje");
//        String valorOvsGrupaje = ((EditableValueHolder) ovsGrupaje).getSubmittedValue().toString();
//        
//        UIComponent ovsLcl = uic.getParent().findComponent("ofer_ovsLcl");
//        String valorOvsLcl = ((EditableValueHolder) ovsLcl).getSubmittedValue().toString();
//        
//        UIComponent ovsFcl = uic.getParent().findComponent("ofer_ovsFcl");
//        String valorOvsFcl = ((EditableValueHolder) ovsFcl).getSubmittedValue().toString();
//        
//        UIComponent ovsAereo = uic.getParent().findComponent("ofer_ovsAereo");
//        String valorOvsAereo = ((EditableValueHolder) ovsAereo).getSubmittedValue().toString();
//                
//		if (!valorCotizadoOvs.equals("") && valorOvsGrupaje.equals("false")&& valorOvsLcl.equals("false")
//				&& valorOvsFcl.equals("false") && valorOvsAereo.equals("false")) {
//                 ResourceBundle resourceBundle = ResourceBundle.getBundle("net.gefco.i18n.errores",fc.getViewRoot().getLocale());
//                 String strTextoError = resourceBundle.getString("error_ovs_sin_actividad");
//                 throw new ValidatorException(new FacesMessage(strTextoError));              
//        }        
//	}	

//	public void validarConseguidoOvs(FacesContext fc, UIComponent uic, Object o) {
//        String valorConseguidoOvs = String.valueOf(o);
//        
//        UIComponent ovsGrupaje = uic.getParent().findComponent("ofer_ovsGrupaje");
//        String valorOvsGrupaje = ((EditableValueHolder) ovsGrupaje).getSubmittedValue().toString();
//        
//        UIComponent ovsLcl = uic.getParent().findComponent("ofer_ovsLcl");
//        String valorOvsLcl = ((EditableValueHolder) ovsLcl).getSubmittedValue().toString();
//        
//        UIComponent ovsFcl = uic.getParent().findComponent("ofer_ovsFcl");
//        String valorOvsFcl = ((EditableValueHolder) ovsFcl).getSubmittedValue().toString();
//        
//        UIComponent ovsAereo = uic.getParent().findComponent("ofer_ovsAereo");
//        String valorOvsAereo = ((EditableValueHolder) ovsAereo).getSubmittedValue().toString();
//                
//		if (!valorConseguidoOvs.equals("") && valorOvsGrupaje.equals("false")&& valorOvsLcl.equals("false")
//				&& valorOvsFcl.equals("false") && valorOvsAereo.equals("false")) {
//                 ResourceBundle resourceBundle = ResourceBundle.getBundle("net.gefco.i18n.errores",fc.getViewRoot().getLocale());
//                 String strTextoError = resourceBundle.getString("error_ovs_sin_actividad");
//                 throw new ValidatorException(new FacesMessage(strTextoError));              
//        }        
//	}
//	
//	public void validarRenovadoOvs(FacesContext fc, UIComponent uic, Object o) {
//        String valorRenovadoOvs = String.valueOf(o);
//        
//        UIComponent ovsGrupaje = uic.getParent().findComponent("ofer_ovsGrupaje");
//        String valorOvsGrupaje = ((EditableValueHolder) ovsGrupaje).getSubmittedValue().toString();
//        
//        UIComponent ovsLcl = uic.getParent().findComponent("ofer_ovsLcl");
//        String valorOvsLcl = ((EditableValueHolder) ovsLcl).getSubmittedValue().toString();
//        
//        UIComponent ovsFcl = uic.getParent().findComponent("ofer_ovsFcl");
//        String valorOvsFcl = ((EditableValueHolder) ovsFcl).getSubmittedValue().toString();
//        
//        UIComponent ovsAereo = uic.getParent().findComponent("ofer_ovsAereo");
//        String valorOvsAereo = ((EditableValueHolder) ovsAereo).getSubmittedValue().toString();
//                
//		if (!valorRenovadoOvs.equals("") && valorOvsGrupaje.equals("false")&& valorOvsLcl.equals("false")
//				&& valorOvsFcl.equals("false") && valorOvsAereo.equals("false")) {
//                 ResourceBundle resourceBundle = ResourceBundle.getBundle("net.gefco.i18n.errores",fc.getViewRoot().getLocale());
//                 String strTextoError = resourceBundle.getString("error_ovs_sin_actividad");
//                 throw new ValidatorException(new FacesMessage(strTextoError));              
//        }        
//	}
	
//	public void validarCotizadoIli(FacesContext fc, UIComponent uic, Object o) {
//        String valorCotizadoIli = String.valueOf(o);
//        
//        UIComponent iliAlmacenaje = uic.getParent().findComponent("ofer_iliAlmacenaje");
//        String valorIliAlmacenaje = ((EditableValueHolder) iliAlmacenaje).getSubmittedValue().toString();
//        
//        UIComponent iliTransporte = uic.getParent().findComponent("ofer_iliTransporte");
//        String valorIliTransporte = ((EditableValueHolder) iliTransporte).getSubmittedValue().toString();
//                  
//		if (!valorCotizadoIli.equals("") && valorIliAlmacenaje.equals("false")&& valorIliTransporte.equals("false")) {
//                 ResourceBundle resourceBundle = ResourceBundle.getBundle("net.gefco.i18n.errores",fc.getViewRoot().getLocale());
//                 String strTextoError = resourceBundle.getString("error_ili_sin_actividad");
//                 throw new ValidatorException(new FacesMessage(strTextoError));              
//        }        
//	}
//	
//	public void validarConseguidoIli(FacesContext fc, UIComponent uic, Object o) {
//        String valorConseguidoIli = String.valueOf(o);
//        
//        UIComponent iliAlmacenaje = uic.getParent().findComponent("ofer_iliAlmacenaje");
//        String valorIliAlmacenaje = ((EditableValueHolder) iliAlmacenaje).getSubmittedValue().toString();
//        
//        UIComponent iliTransporte = uic.getParent().findComponent("ofer_iliTransporte");
//        String valorIliTransporte = ((EditableValueHolder) iliTransporte).getSubmittedValue().toString();
//                  
//		if (!valorConseguidoIli.equals("") && valorIliAlmacenaje.equals("false")&& valorIliTransporte.equals("false")) {
//                 ResourceBundle resourceBundle = ResourceBundle.getBundle("net.gefco.i18n.errores",fc.getViewRoot().getLocale());
//                 String strTextoError = resourceBundle.getString("error_ili_sin_actividad");
//                 throw new ValidatorException(new FacesMessage(strTextoError));              
//        }        
//	}
//	
//	public void validarRenovadoIli(FacesContext fc, UIComponent uic, Object o) {
//        String valorRenovadoIli = String.valueOf(o);
//        
//        UIComponent iliAlmacenaje = uic.getParent().findComponent("ofer_iliAlmacenaje");
//        String valorIliAlmacenaje = ((EditableValueHolder) iliAlmacenaje).getSubmittedValue().toString();
//        
//        UIComponent iliTransporte = uic.getParent().findComponent("ofer_iliTransporte");
//        String valorIliTransporte = ((EditableValueHolder) iliTransporte).getSubmittedValue().toString();
//                  
//		if (!valorRenovadoIli.equals("") && valorIliAlmacenaje.equals("false")&& valorIliTransporte.equals("false")) {
//                 ResourceBundle resourceBundle = ResourceBundle.getBundle("net.gefco.i18n.errores",fc.getViewRoot().getLocale());
//                 String strTextoError = resourceBundle.getString("error_ili_sin_actividad");
//                 throw new ValidatorException(new FacesMessage(strTextoError));              
//        }        
//	}
	
//	public void validarCotizadoTla(FacesContext fc, UIComponent uic, Object o) {
//        String valorCotizadoTla = String.valueOf(o);
//        
//        UIComponent tlaTransporte = uic.getParent().findComponent("ofer_tlaTransporte");
//        String valorTlaTransporte = ((EditableValueHolder) tlaTransporte).getSubmittedValue().toString();
//        
//        UIComponent tlaPvno = uic.getParent().findComponent("ofer_tlaPvno");
//        String valorTlaPvno = ((EditableValueHolder) tlaPvno).getSubmittedValue().toString();
//                  
//		if (!valorCotizadoTla.equals("") && valorTlaTransporte.equals("false")&& valorTlaPvno.equals("false")) {
//                 ResourceBundle resourceBundle = ResourceBundle.getBundle("net.gefco.i18n.errores",fc.getViewRoot().getLocale());
//                 String strTextoError = resourceBundle.getString("error_tla_sin_actividad");
//                 throw new ValidatorException(new FacesMessage(strTextoError));              
//        }        
//	}
//		
//	public void validarConseguidoTla(FacesContext fc, UIComponent uic, Object o) {
//        String valorConseguidoTla = String.valueOf(o);
//        
//        UIComponent tlaTransporte = uic.getParent().findComponent("ofer_tlaTransporte");
//        String valorTlaTransporte = ((EditableValueHolder) tlaTransporte).getSubmittedValue().toString();
//        
//        UIComponent tlaPvno = uic.getParent().findComponent("ofer_tlaPvno");
//        String valorTlaPvno = ((EditableValueHolder) tlaPvno).getSubmittedValue().toString();
//                  
//		if (!valorConseguidoTla.equals("") && valorTlaTransporte.equals("false")&& valorTlaPvno.equals("false")) {
//                 ResourceBundle resourceBundle = ResourceBundle.getBundle("net.gefco.i18n.errores",fc.getViewRoot().getLocale());
//                 String strTextoError = resourceBundle.getString("error_tla_sin_actividad");
//                 throw new ValidatorException(new FacesMessage(strTextoError));              
//        }        
//	}
//	
//	public void validarRenovadoTla(FacesContext fc, UIComponent uic, Object o) {
//        String valorRenovadoTla = String.valueOf(o);
//        
//        UIComponent tlaTransporte = uic.getParent().findComponent("ofer_tlaTransporte");
//        String valorTlaTransporte = ((EditableValueHolder) tlaTransporte).getSubmittedValue().toString();
//        
//        UIComponent tlaPvno = uic.getParent().findComponent("ofer_tlaPvno");
//        String valorTlaPvno = ((EditableValueHolder) tlaPvno).getSubmittedValue().toString();
//                  
//		if (!valorRenovadoTla.equals("") && valorTlaTransporte.equals("false")&& valorTlaPvno.equals("false")) {
//                 ResourceBundle resourceBundle = ResourceBundle.getBundle("net.gefco.i18n.errores",fc.getViewRoot().getLocale());
//                 String strTextoError = resourceBundle.getString("error_tla_sin_actividad");
//                 throw new ValidatorException(new FacesMessage(strTextoError));              
//        }        
//	}
	
//	public void validarCotizadoGsm(FacesContext fc, UIComponent uic, Object o) {
//        String valorCotizadoGsm = String.valueOf(o);
//        
//        UIComponent gsmTransporte = uic.getParent().findComponent("ofer_gsmTransporte");
//        String valorGsmTransporte = ((EditableValueHolder) gsmTransporte).getSubmittedValue().toString();
//        
//		if (!valorCotizadoGsm.equals("") && valorGsmTransporte.equals("false")) {
//                 ResourceBundle resourceBundle = ResourceBundle.getBundle("net.gefco.i18n.errores",fc.getViewRoot().getLocale());
//                 String strTextoError = resourceBundle.getString("error_gsm_sin_actividad");
//                 throw new ValidatorException(new FacesMessage(strTextoError));              
//        }        
//	}
//		
//	public void validarConseguidoGsm(FacesContext fc, UIComponent uic, Object o) {
//        String valorConseguidoGsm = String.valueOf(o);
//        
//        UIComponent gsmTransporte = uic.getParent().findComponent("ofer_gsmTransporte");
//        String valorGsmTransporte = ((EditableValueHolder) gsmTransporte).getSubmittedValue().toString();
//        
//		if (!valorConseguidoGsm.equals("") && valorGsmTransporte.equals("false")) {
//                 ResourceBundle resourceBundle = ResourceBundle.getBundle("net.gefco.i18n.errores",fc.getViewRoot().getLocale());
//                 String strTextoError = resourceBundle.getString("error_gsm_sin_actividad");
//                 throw new ValidatorException(new FacesMessage(strTextoError));              
//        }        
//	}
//	
//	public void validarRenovadoGsm(FacesContext fc, UIComponent uic, Object o) {
//        String valorRenovadoGsm = String.valueOf(o);
//        
//        UIComponent gsmTransporte = uic.getParent().findComponent("ofer_gsmTransporte");
//        String valorGsmTransporte = ((EditableValueHolder) gsmTransporte).getSubmittedValue().toString();
//        
//		if (!valorRenovadoGsm.equals("") && valorGsmTransporte.equals("false")) {
//                 ResourceBundle resourceBundle = ResourceBundle.getBundle("net.gefco.i18n.errores",fc.getViewRoot().getLocale());
//                 String strTextoError = resourceBundle.getString("error_gsm_sin_actividad");
//                 throw new ValidatorException(new FacesMessage(strTextoError));              
//        }        
//	}
//	public String exportarPDF() {
//		JReportsUtil.exportarPDF("ResumenDetalleProvision.jasper",getResumenDetallesProvision(),"detallesProvision.pdf");
//		return null;
//	}	
//	
	
}
	