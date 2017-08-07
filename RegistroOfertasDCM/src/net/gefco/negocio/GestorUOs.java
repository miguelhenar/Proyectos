package net.gefco.negocio;

import java.util.List;

import net.gefco.modelo.UO;
import net.gefco.persistencia.UODao;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class GestorUOs {

	private UODao uODao;

	public void setuODao(UODao uODao) {
		this.uODao = uODao;
	}

	public List<UO> listarTodos(){
		return uODao.listarTodos();
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void insertar(UO uo) {
		uODao.insertar(uo);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void modificar(UO uo) {
		uODao.modificar(uo);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void borrar(UO uo) {
		uODao.borrar(uo.getuO_codigo());
	}

	public UO buscar(int id) {
		return uODao.buscar(id);
	}

}
