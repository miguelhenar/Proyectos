package net.gefco.negocio;

import java.util.List;

import net.gefco.modelo.Maduracion;
import net.gefco.persistencia.MaduracionDao;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class GestorMaduraciones {

	private MaduracionDao maduracionDao;

	public void setMaduracionDao(MaduracionDao maduracionDao) {
		this.maduracionDao = maduracionDao;
	}

	public List<Maduracion> listarTodos(){
		return maduracionDao.listarTodos();
	}

	public List<Maduracion> listarTodosOrdenado(String campo) {
		return maduracionDao.listarTodosOrdenado(campo);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void insertar(Maduracion maduracion) {
		maduracionDao.insertar(maduracion);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void modificar(Maduracion maduracion) {
		maduracionDao.modificar(maduracion);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void borrar(Maduracion maduracion) {
		maduracionDao.borrar(maduracion.getMadu_codigo());
	}

	public Maduracion buscar(int id) {
		return maduracionDao.buscar(id);
	}


}
