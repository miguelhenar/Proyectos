package net.gefco.persistencia;

import java.util.List;

public class AgenciaDaoHibernateTemplateImplementation 
	extends AbstractDaoHibernateTemplate<net.gefco.modelo.Agencia, String> 
	implements AgenciaDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<net.gefco.modelo.Agencia> listarTodos() {
		List<net.gefco.modelo.Agencia> la = hibernateTemplate.find("select a from Agencia a");
		return la;
	}
}
