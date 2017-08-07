package net.gefco.negocio;

import java.util.List;

import net.gefco.modelo.TipoOferta;
import net.gefco.persistencia.TipoOfertaDao;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class GestorTiposOferta {

	private TipoOfertaDao tipoOfertaDao;

	public void setTipoOfertaDao(TipoOfertaDao tipoOfertaDao) {
		this.tipoOfertaDao = tipoOfertaDao;
	}

	public List<TipoOferta> listarTodos(){
		return tipoOfertaDao.listarTodos();
	}

	public List<TipoOferta> listarTodosOrdenado(String campo) {
		return tipoOfertaDao.listarTodosOrdenado(campo);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void insertar(TipoOferta tipoOferta) {
		tipoOfertaDao.insertar(tipoOferta);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void modificar(TipoOferta tipoOferta) {
		tipoOfertaDao.modificar(tipoOferta);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void borrar(TipoOferta tipoOferta) {
		tipoOfertaDao.borrar(tipoOferta.getTiof_codigo());
	}

	public TipoOferta buscar(int id) {
		return tipoOfertaDao.buscar(id);
	}

}
