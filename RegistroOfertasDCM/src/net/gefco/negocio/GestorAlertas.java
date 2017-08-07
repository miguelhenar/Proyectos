package net.gefco.negocio;

import java.util.List;

import net.gefco.modelo.Oferta;
import net.gefco.modelo.Usuario;
import net.gefco.modelo.dto.CBAlerta;
import net.gefco.persistencia.OfertaDao;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public class GestorAlertas {
	private OfertaDao ofertaDao;

	public void setOfertaDao(OfertaDao ofertaDao) {
		this.ofertaDao = ofertaDao;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void insertarOferta(Oferta aux){
		ofertaDao.insertar(aux);        
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void modificarOferta(Oferta aux){		
		ofertaDao.modificar(aux);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void borrarOferta(Oferta aux){
		ofertaDao.borrar(aux.getOfer_codigo());
	}

	public Oferta buscar(int id){
		return ofertaDao.buscar(id);
	}

	public List<Oferta> listarTodos(){
		return ofertaDao.listarTodos();
	}	
		
	public List<Oferta> listarAlertas(
			CBAlerta cBAlerta, Usuario usuario) {
		return ofertaDao.listarAlertas(cBAlerta, usuario);
	}
}
