package net.gefco.negocio;

import java.util.List;

import net.gefco.modelo.PreparacionOferta;
import net.gefco.persistencia.PreparacionOfertaDao;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class GestorPreparacionesOferta {

	private PreparacionOfertaDao preparacionOfertaDao;

	public void setPreparacionOfertaDao(PreparacionOfertaDao preparacionOfertaDao) {
		this.preparacionOfertaDao = preparacionOfertaDao;
	}

	public List<PreparacionOferta> listarTodos(){
		return preparacionOfertaDao.listarTodos();
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void insertar(PreparacionOferta preparacionOferta) {
		preparacionOfertaDao.insertar(preparacionOferta);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void modificar(PreparacionOferta preparacionOferta) {
		preparacionOfertaDao.modificar(preparacionOferta);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void borrar(PreparacionOferta preparacionOferta) {
		preparacionOfertaDao.borrar(preparacionOferta.getProf_codigo());
	}

	public PreparacionOferta buscar(int codigo) {
		return preparacionOfertaDao.buscar(codigo);
	}
}
