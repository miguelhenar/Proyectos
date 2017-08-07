package net.gefco.negocio;

import java.util.List;

import net.gefco.modelo.Rol;
import net.gefco.persistencia.RolDao;

public class GestorRoles {

	private RolDao rolDao;

	public void setRolDao(RolDao rolDao) {
		this.rolDao = rolDao;
	}
	
	public List<Rol> listarTodos(){
		return rolDao.listarTodos();
	}
	
}
