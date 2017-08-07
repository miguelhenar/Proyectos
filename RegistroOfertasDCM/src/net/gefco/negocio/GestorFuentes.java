package net.gefco.negocio;

import java.util.List;

import net.gefco.modelo.Fuente;
import net.gefco.persistencia.FuenteDao;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class GestorFuentes {

	private FuenteDao fuenteDao;
	
	public void setFuenteDao(FuenteDao fuenteDao) {
		this.fuenteDao = fuenteDao;
	}

	public List<Fuente> listarTodos(){
		return fuenteDao.listarTodos();
	}

	public List<Fuente> listarTodosOrdenado(String campo) {
		return fuenteDao.listarTodosOrdenado(campo);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void insertar(Fuente fuente) {
		fuenteDao.insertar(fuente);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void modificar(Fuente fuente) {
		fuenteDao.modificar(fuente);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void borrar(Fuente fuente) {
		fuenteDao.borrar(fuente.getFuen_codigo());
	}

	public Fuente buscar(int id) {
		return fuenteDao.buscar(id);
	}


}
