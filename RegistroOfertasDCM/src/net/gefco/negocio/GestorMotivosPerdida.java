package net.gefco.negocio;

import java.util.List;

import net.gefco.modelo.MotivoPerdida;
import net.gefco.persistencia.MotivoPerdidaDao;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class GestorMotivosPerdida {

	private MotivoPerdidaDao motivoPerdidaDao;

	public void setMotivoPerdidaDao(MotivoPerdidaDao motivoPerdidaDao) {
		this.motivoPerdidaDao = motivoPerdidaDao;
	}

	public List<MotivoPerdida> listarTodos(){
		return motivoPerdidaDao.listarTodos();
	}
	
	public List<MotivoPerdida> listarTodosOrdenado(String campo){
		return motivoPerdidaDao.listarTodosOrdenado(campo);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void insertar(MotivoPerdida motivoPerdida) {
		motivoPerdidaDao.insertar(motivoPerdida);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void modificar(MotivoPerdida motivoPerdida) {
		motivoPerdidaDao.modificar(motivoPerdida);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void borrar(MotivoPerdida motivoPerdida) {
		motivoPerdidaDao.borrar(motivoPerdida.getMope_codigo());
	}

	public MotivoPerdida buscar(int id) {
		return motivoPerdidaDao.buscar(id);
	}

}
