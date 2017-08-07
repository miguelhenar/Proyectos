package net.gefco.bbean;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import net.gefco.modelo.Rol;
import net.gefco.modelo.Usuario;

public class PermisosMenuBB {

	// El usuario que se ha logado
	private Usuario usuario;

	// Un booleano por cada opcion del menu
	private boolean inicial;
	private boolean listadoAlertas;
	private boolean listadoOfertas;
	private boolean administracion;
	private boolean fuentes;
	private boolean maduraciones;
	private boolean motivosPerdida;
	private boolean poles;
	private boolean preparacionesOferta;
	private boolean produccion;
	private boolean tiposOferta;
	private boolean uOs;
	private boolean admin_usuario;
	private boolean usuarios;
	private String paginaAnterior;
		
	//Propiedad para almacenar la opcion del menú seleccionada (opción por defecto:Usuarios)
	private String opSeleccionada = "inicial";
	
	//propiedad para implementar "migas de pan"	
	private String [] migasDePan = new String[]{"Home"};	
	private String tituloFormularioActual = "";
		
	
//	//Una variable para almacenar que opcion está seleccionada por cada nivel del menu
//	private String opNivel1 = "inicial";
//	private String opNivel2;
//	private String opNivel3;

	public Usuario getUsuario() {
		return usuario;
	}	
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(new Locale(usuario.getUsua_idioma())); 
		Locale locale = context.getViewRoot().getLocale();	
		ResourceBundle bundle = ResourceBundle.getBundle("net.gefco.i18n.etiquetas",locale);
		//Etiquetas que se van a mostrar al entrar en la aplicación
		migasDePan = new String[]{bundle.getString("menu_home")};
		tituloFormularioActual=bundle.getString("menu_home");
		
		Rol r = usuario.getUsua_rol();
		if (r.getRol_codigo().equals("ADMINISTRADOR")) {
			inicial            	= true;
			setListadoAlertas(true);
			setListadoOfertas(true);
			administracion    	= true;
			fuentes        		= true;
			maduraciones		= true;
			motivosPerdida		= true;
			poles				= true;
			preparacionesOferta	= true;
			tiposOferta			= true;
			uOs					= true;
			admin_usuario		= true;
			usuarios          	= true;
		} else if (r.getRol_codigo().equals("RESPONSABLE POLE")) {
			inicial            	= true;
			setListadoAlertas(true);
			setListadoOfertas(true);
			administracion    	= true;
			fuentes        		= false;
			maduraciones		= false;
			motivosPerdida		= false;
			poles				= false;
			preparacionesOferta	= false;
			tiposOferta			= false;
			uOs					= false;
			admin_usuario		= true;
			usuarios          	= false;
		}else if (r.getRol_codigo().equals("KAM")) {
			inicial            	= true;
			setListadoAlertas(true);
			setListadoOfertas(true);
			administracion    	= true;
			fuentes        		= false;
			maduraciones		= false;
			motivosPerdida		= false;
			poles				= false;
			preparacionesOferta	= false;
			tiposOferta			= false;
			uOs					= false;
			admin_usuario		= true;
			usuarios          	= false;
		}else if (r.getRol_codigo().equals("VISUALIZACION")) {
			inicial            	= true;
			setListadoAlertas(true);
			setListadoOfertas(true);
			administracion    	= true;
			fuentes        		= false;
			maduraciones		= false;
			motivosPerdida		= false;
			poles				= false;
			preparacionesOferta	= false;
			tiposOferta			= false;
			uOs					= false;
			admin_usuario		= true;
			usuarios          	= false;
		} else {
			System.out.println(" * * * * * * * * * * * * * * *   ERROR:   Rol de usuario no encontrado en 'if' de PermisosMenuBB.SetUsuario");
		}
			
	}

	public boolean isAdministracion() {
		return administracion;
	}

	public void setAdministracion(boolean administracion) {
		this.administracion = administracion;
	}
		
	public boolean isFuentes() {
		return fuentes;
	}

	public void setFuentes(boolean fuentes) {
		this.fuentes = fuentes;
	}

	public boolean isMaduraciones() {
		return maduraciones;
	}

	public void setMaduraciones(boolean maduraciones) {
		this.maduraciones = maduraciones;
	}

	public boolean isMotivosPerdida() {
		return motivosPerdida;
	}

	public void setMotivosPerdida(boolean motivosPerdida) {
		this.motivosPerdida = motivosPerdida;
	}

	public boolean isPoles() {
		return poles;
	}

	public void setPoles(boolean poles) {
		this.poles = poles;
	}

	public boolean isPreparacionesOferta() {
		return preparacionesOferta;
	}

	public void setPreparacionesOferta(boolean preparacionesOferta) {
		this.preparacionesOferta = preparacionesOferta;
	}

	public boolean isTiposOferta() {
		return tiposOferta;
	}

	public void setTiposOferta(boolean tiposOferta) {
		this.tiposOferta = tiposOferta;
	}

	public boolean isuOs() {
		return uOs;
	}

	public void setuOs(boolean uOs) {
		this.uOs = uOs;
	}

	public boolean isAdmin_usuario() {
		return admin_usuario;
	}

	public void setAdmin_usuario(boolean adminUsuario) {
		admin_usuario = adminUsuario;
	}

	public boolean isUsuarios() {
		return usuarios;
	}

	public void setUsuarios(boolean usuarios) {
		this.usuarios = usuarios;
	}

	public boolean isProduccion() {
		return produccion;
	}

	public void setProduccion(boolean produccion) {
		this.produccion = produccion;
	}

	public boolean isInicial() {
		return inicial;
	}
		
	public void setInicial(boolean inicio) {
		this.inicial = inicio;
	}

	public boolean isListadoAlertas() {
		return listadoAlertas;
	}

	public void setListadoAlertas(boolean listadoAlertas) {
		this.listadoAlertas = listadoAlertas;
	}

	public void setListadoOfertas(boolean listadoOfertas) {
		this.listadoOfertas = listadoOfertas;
	}

	public boolean isListadoOfertas() {
		return listadoOfertas;
	}	
	
	public String getPaginaAnterior() {
		return paginaAnterior;
	}

	public void setPaginaAnterior(String paginaAnterior) {
		this.paginaAnterior = paginaAnterior;
	}

	public String getOpSeleccionada() {
		return opSeleccionada;
	}

	public void setOpSeleccionada(String opSeleccionada) {
		this.opSeleccionada = opSeleccionada;
	}

	public String[] getMigasDePan() {
		return migasDePan;
	}

	public void setMigasDePan(String[] migasDePan) {
		this.migasDePan = migasDePan;
	}

	public String getTituloFormularioActual() {
		return tituloFormularioActual;
	}

	public void setTituloFormularioActual(String tituloFormularioActual) {
		this.tituloFormularioActual = tituloFormularioActual;
	}

	public boolean tienePermiso(String recurso){
		
		return true;
	}

	public String abrirPagina() {
		//Devuelve la regla de navegación
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale(new Locale(usuario.getUsua_idioma())); 
		Locale locale = context.getViewRoot().getLocale();	
		ResourceBundle bundle = ResourceBundle.getBundle("net.gefco.i18n.etiquetas",locale);
		switch(opSeleccionada){		
		case "inicial":		
			migasDePan = new String[]{bundle.getString("menu_home")};
			tituloFormularioActual=bundle.getString("menu_home");
			return  "verInicial";			
		case "listadoOfertas":		
			migasDePan = new String[]{bundle.getString("menu_home"),bundle.getString("menu_listado_ofertas")};
			tituloFormularioActual=bundle.getString("menu_listado_ofertas");
			return  "verListadoOfertas";	
		case "editarOferta":		
			migasDePan = new String[]{bundle.getString("menu_home"),bundle.getString("menu_listado_ofertas"),bundle.getString("menu_edicion_oferta")};
			tituloFormularioActual=bundle.getString("menu_edicion_oferta");
			return  "verInicial";			
		case "listadoAlertas":		
			migasDePan = new String[]{bundle.getString("menu_home"),bundle.getString("menu_alertas")};
			tituloFormularioActual=bundle.getString("menu_alertas");
			return  "verListadoAlertas";
		case "fuentes":
			migasDePan = new String[]{bundle.getString("menu_home"),bundle.getString("menu_administracion"), bundle.getString("menu_fuentes")};
			tituloFormularioActual=bundle.getString("menu_fuentes");
			return  "verFuentes";
		case "maduraciones":
			migasDePan = new String[]{bundle.getString("menu_home"),bundle.getString("menu_administracion"), bundle.getString("menu_maduraciones")};
			tituloFormularioActual=bundle.getString("menu_maduraciones");
			return  "verMaduraciones";			
		case "motivosPerdida":
			migasDePan = new String[]{bundle.getString("menu_home"),bundle.getString("menu_administracion"), bundle.getString("menu_motivosPerdida")};
			tituloFormularioActual=bundle.getString("menu_motivosPerdida");
			return  "verMotivosPerdida";			
		case "poles":
			migasDePan = new String[]{bundle.getString("menu_home"),bundle.getString("menu_administracion"), bundle.getString("menu_poles")};
			tituloFormularioActual=bundle.getString("menu_poles");
			return  "verPoles";			
		case "preparacionesOferta":
			migasDePan = new String[]{bundle.getString("menu_home"),bundle.getString("menu_administracion"), bundle.getString("menu_preparacionesOferta")};
			tituloFormularioActual=bundle.getString("menu_preparacionesOferta");
			return  "verPreparacionesOferta";
		case "tiposOferta":
			migasDePan = new String[]{bundle.getString("menu_home"),bundle.getString("menu_administracion"), bundle.getString("menu_tiposOferta")};
			tituloFormularioActual=bundle.getString("menu_tiposOferta");
			return  "verTiposOferta";
		case "uOs":
			migasDePan = new String[]{bundle.getString("menu_home"),bundle.getString("menu_administracion"), bundle.getString("menu_uOs")};
			tituloFormularioActual=bundle.getString("menu_uOs");
			return  "verUOs";		
		case "usuario": 				
			migasDePan = new String[]{bundle.getString("menu_home"),bundle.getString("menu_administracion"), bundle.getString("menu_usuario")};
			tituloFormularioActual=bundle.getString("menu_usuario"); 
			return  "verUsuario";
		case "usuarios":				
			migasDePan = new String[]{bundle.getString("menu_home"),bundle.getString("menu_administracion"), bundle.getString("menu_usuarios")};
			tituloFormularioActual=bundle.getString("menu_usuarios"); 
			return  "verUsuarios";		
		}
		return null;	
	}		
}
