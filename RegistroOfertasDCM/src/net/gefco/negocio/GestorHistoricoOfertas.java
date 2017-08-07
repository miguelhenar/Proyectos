package net.gefco.negocio;

import java.util.List;

import net.gefco.modelo.HistoricoOferta;
import net.gefco.persistencia.HistoricoOfertaDao;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public class GestorHistoricoOfertas {
	private HistoricoOfertaDao historicoOfertaDao;

	public void setHistoricoOfertaDao(HistoricoOfertaDao historicoOfertaDao) {
		this.historicoOfertaDao = historicoOfertaDao;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void insertarHistoricoOferta(HistoricoOferta aux){		
		historicoOfertaDao.insertar(aux);        
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void modificarHistoricoOferta(HistoricoOferta aux){		
		historicoOfertaDao.modificar(aux);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void borrarHistoricoOferta(HistoricoOferta i){
		historicoOfertaDao.borrar(i.getHiof_codigo());
	}

	public HistoricoOferta buscar(int id){
		return historicoOfertaDao.buscar(id);
	}

	public List<HistoricoOferta> listarTodos(){
		return historicoOfertaDao.listarTodos();
	}	
	
	
}
