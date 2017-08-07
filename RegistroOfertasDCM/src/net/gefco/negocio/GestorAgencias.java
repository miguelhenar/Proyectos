package net.gefco.negocio;

import java.util.ArrayList;
import java.util.List;

import net.gefco.modelo.Agencia;
import net.gefco.modelo.Usuario;
import net.gefco.persistencia.AgenciaDao;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class GestorAgencias {

	private AgenciaDao agenciaDao;

	public void setAgenciaDao(AgenciaDao agenciaDao) {
		this.agenciaDao = agenciaDao;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void insertar(Agencia agencia) {
		agenciaDao.insertar(agencia);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void modificar(Agencia agencia) {
		agenciaDao.modificar(agencia);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void borrar(Agencia agencia) {
		agenciaDao.borrar(agencia.getAgen_codigo());
	}

	public Agencia buscar(String codigo) {
		return agenciaDao.buscar(codigo);
	}
	
	public List<Agencia> listarTodos(){
		return agenciaDao.listarTodos();
	}

	public List<Agencia> listarTodos(Usuario usuario) {
		/*Si es administrador se devuelven los apuntes de todas las agencias. Si no,
		se devuelven sólo los de su agencia*/
		if(usuario.getUsua_rol().getRol_codigo().equals("ADMINISTRADOR") ||
		  usuario.getUsua_rol().getRol_codigo().equals("VISUALIZACION")){
			return agenciaDao.listarTodos();
		} else {
			List<Agencia> agencias = new ArrayList<Agencia>();
			agencias.add(usuario.getUsua_agencia());
			return agencias;
		}
		
	}

}












