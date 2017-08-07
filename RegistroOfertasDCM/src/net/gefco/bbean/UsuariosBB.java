package net.gefco.bbean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.gefco.manejoDataTable.UsuariosDT;
import net.gefco.modelo.Pole;
import net.gefco.modelo.Usuario;
import net.gefco.negocio.GestorPoles;
import net.gefco.negocio.GestorUsuarios;
import net.gefco.util.JReportsUtil;

import org.springframework.dao.DataIntegrityViolationException;


public class UsuariosBB {
    
	private String estado;		
		
	//Este usuario es el que está en la SESION
	private Usuario usuario;	
	//Este usuario es el que se utiliza en la pagina usuarios.jspx
	private Usuario usuarioForm;

	private GestorPoles gestorPoles;	
	private GestorUsuarios gestorUsuarios;
	private UsuariosDT usuariosDT;
	
	private Integer[] polesSelectedItems;
	
	private Map <Integer,Boolean> selectedPoles = new HashMap<Integer,Boolean>();
		
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
		
	public UsuariosDT getUsuariosDT() {
		return usuariosDT;
	}

	public void setUsuariosDT(UsuariosDT usuariosDT) {
		this.usuariosDT = usuariosDT;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void setUsuarioForm(Usuario usuarioForm) {
		this.usuarioForm = usuarioForm;
	}
	
	public Usuario getUsuarioForm() {
		return usuarioForm;
	}

	public void setGestorUsuarios(GestorUsuarios gestorUsuarios) {
		this.gestorUsuarios = gestorUsuarios;
	}
	
	public GestorPoles getGestorPoles() {
		return gestorPoles;
	}

	public void setGestorPoles(GestorPoles gestorPoles) {
		this.gestorPoles = gestorPoles;
	}

	public Integer[] getPolesSelectedItems() {
		if (usuarioForm.getPoles()!=null) {
			int cont=0;
			polesSelectedItems = new Integer[usuarioForm.getPoles().size()];
			
			for (Pole p:usuarioForm.getPoles()){
				polesSelectedItems[cont] = p.getPole_codigo();
				cont++;
			}
		} else {
			polesSelectedItems = new Integer[0];
		}
		return polesSelectedItems;
	}

	public void setPolesSelectedItems(Integer[] polesSelectedItems) {
		this.polesSelectedItems = polesSelectedItems;
		
		List<Pole> poles = new ArrayList<Pole>();		
		for (Integer i:polesSelectedItems) {
			poles.add(gestorPoles.buscar(i));
		}
		
		usuarioForm.setPoles(poles);
	}

	public UsuariosBB() {
		super();
	}

	public UsuariosBB(String estado, Usuario usuario, Usuario usuarioForm,
			GestorPoles gestorPoles, GestorUsuarios gestorUsuarios,
			UsuariosDT usuariosDT, Integer[] polesSelectedItems) {
		super();
		this.estado = estado;
		this.usuario = usuario;
		this.usuarioForm = usuarioForm;
		this.gestorPoles = gestorPoles;
		this.gestorUsuarios = gestorUsuarios;
		this.usuariosDT = usuariosDT;
		this.polesSelectedItems = polesSelectedItems;
	}
	

	
//	public Map<Pole, Boolean> getSelectedPoles() {
//		selectedPoles.clear();
//		if (usuarioForm.getPoles()!=null) {
//			for (Pole p:usuarioForm.getPoles()){
//				selectedPoles.put(p, true);
//			}
//		} 
//		return selectedPoles;
//	}
//
//	public void setSelectedPoles(Map<Pole, Boolean> selectedPoles) {
//		this.selectedPoles = selectedPoles;
//		
//		List<Pole> poles = new ArrayList<Pole>();		
//		for (Pole p: this.getSelectedPoles().keySet()) {
//			if (this.getSelectedPoles().get(p)==true){
//			poles.add(p);
//			}
//		}
//		
//		usuarioForm.setPoles(poles);
//		this.selectedPoles = selectedPoles;
//		
//	}

	
	public Map<Integer, Boolean> getSelectedPoles() {
		return selectedPoles;
	}

	public void setSelectedPoles(Map<Integer, Boolean> selectedPoles) {
		this.selectedPoles = selectedPoles;
	}

	public String logar(){
		
		String sigVista = null;
		Usuario uAux = gestorUsuarios.buscarPorLogin(usuario);
		
		if(uAux!=null && uAux.getUsua_codigo()!=0){
			//Podemos sustituir el usuario que está en la sesión por el que nos
			//devuelve la consulta...
			usuario = uAux;
			FacesContext fCtx = FacesContext.getCurrentInstance();
			HttpServletRequest rq = (HttpServletRequest) fCtx.getExternalContext().getRequest();
			HttpSession s = rq.getSession(false);
			s.setAttribute("usuario", uAux);			
			sigVista = "verInicial";			
		}				
		return sigVista;
	}
	
	//Vaya nombre
	public String deslogar(){		
		FacesContext fCtx = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) fCtx.getExternalContext().getRequest();		
		request.getSession(true).invalidate();
		return "verLogin";
	}
	
	public String insertar(){
		//Campos obligatorios
		if (usuarioForm.getUsua_login().equals("")){
			estado="Introduzca el login";
			return "verUsuarios";
		}
		if (usuarioForm.getUsua_nombre().equals("")){
			estado="Introduzca el nombre";
			return "verUsuarios";
		}
		if (usuarioForm.getUsua_pw()==null ||
				usuarioForm.getUsua_pw().length()==0)	{		
			estado="Introduzca el pasword";
			return "verUsuarios";
		}
		if (usuarioForm.getUsua_idioma()==null ||
				usuarioForm.getUsua_idioma().length()==0)	{		
			estado="Introduzca el idioma";
			return "verUsuarios";
		}
		if (usuarioForm.getUsua_rol().getRol_codigo()==null ||
				usuarioForm.getUsua_rol().getRol_codigo().length()==0)	{		
			estado="Seleccione el rol";
			return "verUsuarios";
		}
		if (usuarioForm.getUsua_agencia().getAgen_codigo()==null ||
				usuarioForm.getUsua_agencia().getAgen_codigo().length()==0)	{		
			estado="Seleccione la agencia";
			return "verUsuarios";
		}
		if (usuarioForm.getUsua_dias_alerta()==0)	{		
			estado="Introduzca los días de alerta";
			return "verUsuarios";
		}
		
		//Rellenar el listado de poles del usuario en funcion de los cheks marcados en el jspx
		List<Pole> poles = new ArrayList<Pole>();		
		for (Integer i: this.getSelectedPoles().keySet()) {
			if (getSelectedPoles().get(i)==true) {poles.add(gestorPoles.buscar(i));}
		}
		usuarioForm.setPoles(poles);
		
//		//Verificar que no exista el login que se pretende crear.
//		List<Usuario> listAux = gestorUsuarios.buscar("usua_login",usuarioForm.getUsua_login());
//		if (listAux.size()!=0){
//			estado="el login " + usuarioForm.getUsua_login() + " ya existe.";
//			return "verUsuarios";
//		}
//		//Verificar que no exista el nombre que se pretende crear.
//		listAux = gestorUsuarios.buscar("usua_nombre",usuarioForm.getUsua_nombre());
//		if (listAux.size()!=0){
//			estado="el usuario " + usuarioForm.getUsua_nombre() + " ya existe.";
//			return "verUsuarios";
//		}
				
		try {
			//En este caso se utilizar el método modificar en vez del met. insertar porque el met. insertar
			//NO guarda las poles del usuario y el met. modificar SI.
			
			//gestorUsuarios.insertar(usuarioForm);
			gestorUsuarios.modificar(usuarioForm);
			usuariosDT.setPanelVisible("");
			
			estado = "Registro guardado";
		} catch (DataIntegrityViolationException e) {	
			estado = "Valor incorrecto. No se guardó ningún dato";
		}
		usuariosDT.setPanelVisible("");
		return "verUsuarios";
	}	
	
	public String modificar(){
		//Campos obligatorios
		if (usuario.getUsua_login()==null ||
				usuario.getUsua_login().length()==0)	{		
			estado="Introduzca el login";
			return "verUsuarios";
		}	
		if (usuario.getUsua_nombre()==null ||
				usuario.getUsua_nombre().length()==0)	{		
			estado="Introduzca el nombre";
			return "verUsuarios";
		}
		if (usuario.getUsua_pw()==null ||
				usuario.getUsua_pw().length()==0)	{		
			estado="Introduzca el pasword";
			return "verUsuarios";
		}
		if (usuario.getUsua_idioma()==null ||
				usuario.getUsua_idioma().length()==0)	{		
			estado="Introduzca el idioma";
			return "verUsuarios";
		}
		if (usuario.getUsua_rol().getRol_codigo()==null ||
				usuario.getUsua_rol().getRol_codigo().length()==0)	{		
			estado="Seleccione el rol";
			return "verUsuarios";
		}
		if (usuario.getUsua_agencia().getAgen_codigo()==null ||
				usuario.getUsua_agencia().getAgen_codigo().length()==0)	{		
			estado="Seleccione la agencia";
			return "verUsuarios";
		}
		if (usuario.getUsua_dias_alerta()==0)	{		
			estado="Introduzca los días de alerta";
			return "verUsuarios";
		}
		try{
			gestorUsuarios.modificar(usuario);
			//estado = "Registro guardado";
			usuariosDT.setPanelVisible("");
		} catch (DataIntegrityViolationException e) {	
				estado = "Valor incorrecto. No se guardó ningún dato. Revise si el usuario que está intentando crear ya existe";
		}
		return "verUsuario";
	}

	public String modificarUsuarioForm(){
		//Campos obligatorios
		usuariosDT.setPanelVisible("panelEdicion");
		if (usuarioForm.getUsua_login()==null ||
				usuarioForm.getUsua_login().length()==0)	{		
			estado="Introduzca el login";
			return "verUsuarios";
		}	
		if (usuarioForm.getUsua_nombre()==null ||
				usuarioForm.getUsua_nombre().length()==0)	{		
			estado="Introduzca el nombre";
			return "verUsuarios";
		}
		if (usuarioForm.getUsua_pw()==null ||
				usuarioForm.getUsua_pw().length()==0)	{		
			estado="Introduzca el pasword";
			return "verUsuarios";
		}
		if (usuarioForm.getUsua_idioma()==null ||
				usuarioForm.getUsua_idioma().length()==0)	{		
			estado="Introduzca el idioma";
			return "verUsuarios";
		}
		if (usuarioForm.getUsua_rol().getRol_codigo()==null ||
				usuarioForm.getUsua_rol().getRol_codigo().length()==0)	{		
			estado="Seleccione el rol";
			return "verUsuarios";
		}
		if (usuarioForm.getUsua_agencia().getAgen_codigo()==null ||
				usuarioForm.getUsua_agencia().getAgen_codigo().length()==0)	{		
			estado="Seleccione la agencia";
			return "verUsuarios";
		}
		if (usuarioForm.getUsua_dias_alerta()==0)	{		
			estado="Introduzca los días de alerta";
			return "verUsuarios";
		}
		
		List<Pole> poles = new ArrayList<Pole>();		
		for (Integer i: this.getSelectedPoles().keySet()) {
			if (getSelectedPoles().get(i)==true) {poles.add(gestorPoles.buscar(i));}
		}
		
		usuarioForm.setPoles(poles);
				
		gestorUsuarios.modificar(usuarioForm);
		usuariosDT.setPanelVisible("");
		return "verUsuarios";
	}

	public String borrar(){
		try {
			gestorUsuarios.borrar(usuarioForm);
			usuarioForm.vaciar();
			estado="Registro eliminado";
			//Pedimos el facesContext
			FacesContext fCtx = FacesContext.getCurrentInstance();
			//Obtenemos el Id del �rbol que se est� visualizando actualmente
			String vId = fCtx.getViewRoot().getViewId(); 
			//Obtenemos el Viewhandler
			ViewHandler vh = fCtx.getApplication().getViewHandler();
			//Le pedimos al ViewHandler que cree un nuevo �rbol (vac�o) con el id del antiguo
			UIViewRoot nuevoArbol = vh.createView(fCtx, vId);
			//Sustituimos el viejo por el nuevo
			fCtx.setViewRoot(nuevoArbol);
		} catch (DataIntegrityViolationException e) {
			estado = "No se pudo eliminar el registro";
		}
		usuariosDT.setPanelVisible("panelEdicion");
		return "verUsuarios";
	}
				
	public String seleccionar(){
		Usuario aux = gestorUsuarios.buscar(usuarioForm.getUsua_codigo());
		usuarioForm.copiarValores(aux, false);	
		
		
		selectedPoles.clear();
		if (usuarioForm.getPoles()!=null) {
			for (Pole p:usuarioForm.getPoles()){
				selectedPoles.put(p.getPole_codigo(), true);
			}
		} 
		
		
		//Pedimos el facesContext
		FacesContext fCtx = FacesContext.getCurrentInstance();
		//Obtenemos el Id del �rbol que se est� visualizando actualmente
		String vId = fCtx.getViewRoot().getViewId(); 
		//Obtenemos el Viewhandler
		ViewHandler vh = fCtx.getApplication().getViewHandler();
		//Le pedimos al ViewHandler que cree un nuevo �rbol (vac�o) con el id del antiguo
		UIViewRoot nuevoArbol = vh.createView(fCtx, vId);
		//Sustituimos el viejo por el nuevo
		fCtx.setViewRoot(nuevoArbol);
		usuariosDT.setPanelVisible("panelEdicion");
		return "verUsuarios";
	}
	
	public String cancelar(){
		usuarioForm.vaciar();
		usuariosDT.setPanelVisible("");
		return "verUsuarios";
	}
	
	public String nuevo(){
		usuarioForm.vaciar();
		usuariosDT.setPanelVisible("panelEdicion");
		return "verUsuarios";
	}
	
	public String vaciar(){
		usuarioForm.vaciar();
		selectedPoles.clear();
		//Pedimos el facesContext
		FacesContext fCtx = FacesContext.getCurrentInstance();
		//Obtenemos el Id del �rbol que se est� visualizando actualmente
		String vId = fCtx.getViewRoot().getViewId(); 
		//Obtenemos el Viewhandler
		ViewHandler vh = fCtx.getApplication().getViewHandler();
		//Le pedimos al ViewHandler que cree un nuevo �rbol (vac�o) con el id del antiguo
		UIViewRoot nuevoArbol = vh.createView(fCtx, vId);
		//Sustituimos el viejo por el nuevo
		fCtx.setViewRoot(nuevoArbol);
		//Como este método lo usan varias páginas, devolvemos la página de la que venimos
		return FacesContext.getCurrentInstance().getViewRoot().getViewId();
	}	
		
	public List<Usuario> getListaUsuarios(){
		return gestorUsuarios.listarTodosOrdenado(usuario);
	}
	
	public List<SelectItem> getListaUsuariosSI() {
		List<Usuario> lista = gestorUsuarios.listarTodosOrdenado(usuario);
		List<SelectItem> listaSI = new ArrayList<SelectItem>();

		//sólo colocaremos el "seleccione" si hay más de una pq el usuario el administrador
		if(lista.size()>1)
			listaSI.add(new SelectItem(null, "Seleccione..."));			
		for (Usuario u : lista) {
			SelectItem si = new SelectItem(u.getUsua_codigo(), u.getUsua_nombre());
			listaSI.add(si);
		}
		return listaSI;
	}		
	
	public List<SelectItem> getListaNombresUsuariosSI() {
		List<Usuario> lista = gestorUsuarios.listarTodosOrdenado(usuario);
		List<SelectItem> listaSI = new ArrayList<SelectItem>();
		
		//Metemos por defecto el usuario o Todos (si es administrador) y después el resto de usuarios
				if(usuario.getUsua_rol().getRol_codigo().equals("ADMINISTRADOR")){
					SelectItem si = new SelectItem("%","Todos");
					listaSI.add(si);
				} else {
					SelectItem si = new SelectItem(usuario.getUsua_nombre(), usuario.getUsua_nombre());
					listaSI.add(si);
				}
				for (Usuario u : lista) {
					if(usuario.getUsua_rol().getRol_codigo().equals("ADMINISTRADOR")){
						SelectItem si1 = new SelectItem(u.getUsua_nombre(), u.getUsua_nombre());
						listaSI.add(si1);
					} else {
						if(!(u.getUsua_codigo()==(u.getUsua_codigo()))){					
							SelectItem si1 = new SelectItem(u.getUsua_nombre(), u.getUsua_nombre());
							listaSI.add(si1);
						}
					}
				}
		return listaSI;
	}
	
	public List<SelectItem> getListaResponsablesSI() {
		List<Usuario> lista = gestorUsuarios.listarResponsables(usuario);
		List<SelectItem> listaSI = new ArrayList<SelectItem>();

		//sólo colocaremos el "seleccione" si hay más de una pq el usuario el administrador
		if(lista.size()>1)
			listaSI.add(new SelectItem(null, "Seleccione..."));
		for (Usuario u : lista) {
			SelectItem si = new SelectItem(u.getUsua_codigo(), u.getUsua_nombre());
			listaSI.add(si);
		}
		return listaSI;
	}	
	
	public String exportarPDF(){
		JReportsUtil.exportarPdf("listadoUsuarios.jasper", gestorUsuarios.listarTodosOrdenado(usuario), "listadoUsuarios.pdf");
		return null;
	}		
}