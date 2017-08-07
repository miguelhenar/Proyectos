package net.gefco.persistencia;

import java.util.List;

public class RolDaoHibernateTemplateImplementation 
	extends AbstractDaoHibernateTemplate<net.gefco.modelo.Rol, String> 
	implements RolDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<net.gefco.modelo.Rol> listarTodos() {
		List<net.gefco.modelo.Rol> lr = hibernateTemplate.find("select r from Rol r");
		return lr;
	}
}
