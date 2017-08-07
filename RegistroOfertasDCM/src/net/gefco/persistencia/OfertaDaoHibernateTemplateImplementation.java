package net.gefco.persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.gefco.modelo.HistoricoOferta;
import net.gefco.modelo.Oferta;
import net.gefco.modelo.Usuario;
import net.gefco.modelo.dto.CBAlerta;
import net.gefco.modelo.dto.CBOferta;

public class OfertaDaoHibernateTemplateImplementation 
	extends AbstractDaoHibernateTemplate<Oferta, Integer> 
	implements OfertaDao {

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Oferta> listarOfertas(CBOferta cBOferta, Usuario usuario) {
	
		List<String> claves  = new ArrayList<String>();
		List<Object> valores = new ArrayList<Object>();

		String query = "from Oferta o where 1=1 ";
		
		//Filtro del código de la oferta
		int codigoOferta = cBOferta.getCodigo();
		if(codigoOferta!=0){
			query = query + "and o.ofer_codigo=:codigoOf ";
			claves.add("codigoOf");
			valores.add(codigoOferta);
		}	
		
		//Filtro Empresa
		String empresa = cBOferta.getEmpresa();
		if(empresa!=null && empresa.length()>0){
			query = query + "and o.ofer_empresa like :emp ";
			claves.add("emp");
			valores.add("%"+empresa+"%");
		}	
		
		//Filtros Fechas Tope
		Date fechaTopeInicio = (Date) cBOferta.getFechaTopeI();
		if(fechaTopeInicio!=null){			
			fechaTopeInicio.setHours(0);
			fechaTopeInicio.setMinutes(0);
			fechaTopeInicio.setSeconds(0);
			query = query + "and o.ofer_fechaTope>=:fechaTI ";
			claves.add("fechaTI");
			valores.add(fechaTopeInicio);
		}
		
		Date fechaTopeFin = (Date) cBOferta.getFechaTopeF();
		if(fechaTopeFin!=null){
			fechaTopeFin.setHours(23);
			fechaTopeFin.setMinutes(59);
			fechaTopeFin.setSeconds(59);
			query = query + "and o.ofer_fechaTope<=:fechaTF ";
			claves.add("fechaTF");
			valores.add(fechaTopeFin);
		}
		
		//Filtros Fechas UltimoEstado
		Date fechaUltimoEstadoInicio = (Date) cBOferta.getFechaUltimoEstadoI();
		if(fechaUltimoEstadoInicio!=null){			
			fechaUltimoEstadoInicio.setHours(0);
			fechaUltimoEstadoInicio.setMinutes(0);
			fechaUltimoEstadoInicio.setSeconds(0);
			query = query + "and o.ofer_fechaUltimoEstado>=:fechaUI ";
			claves.add("fechaUI");
			valores.add(fechaUltimoEstadoInicio);
		}
		
		Date fechaUltimoEstadoFin = (Date) cBOferta.getFechaUltimoEstadoF();
		if(fechaUltimoEstadoFin!=null){
			fechaUltimoEstadoFin.setHours(23);
			fechaUltimoEstadoFin.setMinutes(59);
			fechaUltimoEstadoFin.setSeconds(59);
			query = query + "and o.ofer_fechaUltimoEstado<=:fechaUF ";
			claves.add("fechaUF");
			valores.add(fechaUltimoEstadoFin);
		}
				
		//Filtro Maduración
		int maduracion = cBOferta.getMaduracion().getMadu_codigo();
		if(maduracion!=0){
			query = query + "and o.ofer_maduracion.madu_codigo=:codigoMad ";
			claves.add("codigoMad");
			valores.add(maduracion);
		}
		
		//Filtro Pole
		int pole = cBOferta.getPole().getPole_codigo();
		if(pole!=0){
			query = query + "and o.ofer_pole.pole_codigo=:codigoPol ";
			claves.add("codigoPol");
			valores.add(pole);
		}
		
		//Filtro Tipo Oferta
		int tipoOferta = cBOferta.getTipoOferta().getTiof_codigo();
		if(tipoOferta!=0){
			query = query + "and o.ofer_tipoOferta.tiof_codigo=:codigoTiOf ";
			claves.add("codigoTiOf");
			valores.add(tipoOferta);
		}
		
		//Filtro del usuario
		int codigoUsuario = cBOferta.getUsuario().getUsua_codigo();
		if(codigoUsuario!=0){
			query = query + "and o.ofer_usuario.usua_codigo=:codigoUs ";
			claves.add("codigoUs");
			valores.add(codigoUsuario);
		} else {
			List<Usuario> lista = new ArrayList<Usuario>();
			if(usuario.getUsua_rol().getRol_codigo().equals("ADMINISTRADOR") ||
			   usuario.getUsua_rol().getRol_codigo().equals("VISUALIZACION")){
				lista = hibernateTemplate.find("from Usuario u");
			}
			else {			
				lista = hibernateTemplate.find("from Usuario u where u.usua_responsable=? and u.usua_codigo!=?",
						new Object[]{usuario.getUsua_codigo(),usuario.getUsua_codigo()});
				lista.add(usuario);
			}
			query = query + "and (1=0 ";

			for (Usuario u : lista) {
				query = query + "or o.ofer_usuario.usua_codigo=:codigoUs" + u.getUsua_codigo() + " ";
				claves.add("codigoUs" + u.getUsua_codigo());
				valores.add(u.getUsua_codigo());
			}	
			query = query + ") ";
		}
												
		//Ordenamos los datos por código de oferta
		query = query + "order by o.ofer_codigo desc ";
								
		//hacemos un array con la misma longitud que la lista de claves
		String [] clavesArray= new String[claves.size()];
		for (int i = 0; i < clavesArray.length; i++) {
			clavesArray[i] = claves.get(i);
		}
		List<Oferta> rs = hibernateTemplate.findByNamedParam(query, clavesArray, valores.toArray());
		
		return rs;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Oferta> listarAlertas(CBAlerta cBAlerta, Usuario usuario) {
		
		List<String> claves  = new ArrayList<String>();
		List<Object> valores = new ArrayList<Object>();

		String query = "from Oferta o where 1=1 ";
		
		//cogemos sólo las ofertas Negociación y primer contacto
		query = query + "and (o.ofer_maduracion.madu_nombre='Negociación' " +
					"or o.ofer_maduracion.madu_nombre='Primer Contacto')";
		
		//Filtro del código de la oferta
		int codigoOferta = cBAlerta.getCodigo();
		if(codigoOferta!=0){
			query = query + "and o.ofer_codigo=:codigoOf ";
			claves.add("codigoOf");
			valores.add(codigoOferta);
		}	
		
		//Filtro Empresa
		String empresa = cBAlerta.getEmpresa();
		if(empresa!=null && empresa.length()>0){
			query = query + "and o.ofer_empresa like :emp ";
			claves.add("emp");
			valores.add("%"+empresa+"%");
		}					
		
		Date fechaTopeFin = (Date) cBAlerta.getFechaTopeF();
		if(fechaTopeFin!=null){
			fechaTopeFin.setHours(23);
			fechaTopeFin.setMinutes(59);
			fechaTopeFin.setSeconds(59);
			query = query + "and o.ofer_fechaTope<=:fechaTF ";
			claves.add("fechaTF");
			valores.add(fechaTopeFin);
		}
		
		//Filtros Fechas UltimoEstado
		Date fechaUltimoEstadoInicio = (Date) cBAlerta.getFechaUltimoEstadoI();
		if(fechaUltimoEstadoInicio!=null){			
			fechaUltimoEstadoInicio.setHours(0);
			fechaUltimoEstadoInicio.setMinutes(0);
			fechaUltimoEstadoInicio.setSeconds(0);
			query = query + "and o.ofer_fechaUltimoEstado>=:fechaUI ";
			claves.add("fechaUI");
			valores.add(fechaUltimoEstadoInicio);
		}
		
		Date fechaUltimoEstadoFin = (Date) cBAlerta.getFechaUltimoEstadoF();
		if(fechaUltimoEstadoFin!=null){
			fechaUltimoEstadoFin.setHours(23);
			fechaUltimoEstadoFin.setMinutes(59);
			fechaUltimoEstadoFin.setSeconds(59);
			query = query + "and o.ofer_fechaUltimoEstado<=:fechaUF ";
			claves.add("fechaUF");
			valores.add(fechaUltimoEstadoFin);
		}
				
		//Filtro Maduración
		int maduracion = cBAlerta.getMaduracion().getMadu_codigo();
		if(maduracion!=0){
			query = query + "and o.ofer_maduracion.madu_codigo=:codigoMad ";
			claves.add("codigoMad");
			valores.add(maduracion);
		}
		
		//Filtro Pole
		int pole = cBAlerta.getPole().getPole_codigo();
		if(pole!=0){
			query = query + "and o.ofer_pole.pole_codigo=:codigoPol ";
			claves.add("codigoPol");
			valores.add(pole);
		}
		
		//Filtro Tipo Oferta
		int tipoOferta = cBAlerta.getTipoOferta().getTiof_codigo();
		if(tipoOferta!=0){
			query = query + "and o.ofer_tipoOferta.tiof_codigo=:codigoTiOf ";
			claves.add("codigoTiOf");
			valores.add(tipoOferta);
		}
				
		//Filtro del usuario
		int codigoUsuario = cBAlerta.getUsuario().getUsua_codigo();
		if(codigoUsuario!=0){
			query = query + "and o.ofer_usuario.usua_codigo=:codigoUs ";
			claves.add("codigoUs");
			valores.add(codigoUsuario);
		} else {
			List<Usuario> lista = new ArrayList<Usuario>();
			if(usuario.getUsua_rol().getRol_codigo().equals("ADMINISTRADOR") ||
			   usuario.getUsua_rol().getRol_codigo().equals("VISUALIZACION")){
				lista = hibernateTemplate.find("from Usuario u");
			}
			else {			
				lista = hibernateTemplate.find("from Usuario u where u.usua_responsable=? and u.usua_codigo!=?",
						new Object[]{usuario.getUsua_codigo(),usuario.getUsua_codigo()});
				lista.add(usuario);
			}
			query = query + "and (1=0 ";

			for (Usuario u : lista) {
				query = query + "or o.ofer_usuario.usua_codigo=:codigoUs" + u.getUsua_codigo() + " ";
				claves.add("codigoUs" + u.getUsua_codigo());
				valores.add(u.getUsua_codigo());
			}	
			query = query + ") ";
		}
												
		//Ordenamos los datos por código de oferta
		query = query + "order by o.ofer_fechaTope desc, o.ofer_codigo desc ";
								
		//hacemos un array con la misma longitud que la lista de claves
		String [] clavesArray= new String[claves.size()];
		for (int i = 0; i < clavesArray.length; i++) {
			clavesArray[i] = claves.get(i);
		}
		List<Oferta> rs = hibernateTemplate.findByNamedParam(query, clavesArray, valores.toArray());
		
		return rs;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<HistoricoOferta> listarHcoOferta(int codigo) {
		
		List<String> claves  = new ArrayList<String>();
		List<Object> valores = new ArrayList<Object>();

		String query = "from HistoricoOferta o where 1=1 ";
				
		//Filtro del código de la oferta
		if(codigo!=0){
			query = query + "and o.ofer_codigo=:codigoOf ";
			claves.add("codigoOf");
			valores.add(codigo);
		}	
		
		//Ordenamos los datos por código de oferta
		query = query + "order by o.ofer_fechaRegistro desc ";
								
		//hacemos un array con la misma longitud que la lista de claves
		String [] clavesArray= new String[claves.size()];
		for (int i = 0; i < clavesArray.length; i++) {
			clavesArray[i] = claves.get(i);
		}
		List<HistoricoOferta> rs = hibernateTemplate.findByNamedParam(query, clavesArray, valores.toArray());
		
		return rs;
	}
	
}
