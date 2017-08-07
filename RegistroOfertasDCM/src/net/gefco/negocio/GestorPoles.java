package net.gefco.negocio;

import java.util.List;

import net.gefco.modelo.Pole;
import net.gefco.modelo.Usuario;
import net.gefco.persistencia.PoleDao;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED)
public class GestorPoles {

	private PoleDao poleDao;

	public void setPoleDao(PoleDao poleDao) {
		this.poleDao = poleDao;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void insertar(Pole pole) {
		poleDao.insertar(pole);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void modificar(Pole pole) {
		poleDao.modificar(pole);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void borrar(Pole pole) {
		poleDao.borrar(pole.getPole_codigo());
	}

	public Pole buscar(int codigo) {
		return poleDao.buscar(codigo);
	}
	
	public List<Pole> listarTodos(){
		return poleDao.listarTodos();
	}

	public List<Pole> listarPorUsuario(Usuario usuario) {
		return poleDao.listarPorUsuario(usuario);		
	}

}












