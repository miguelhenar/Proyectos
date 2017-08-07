package net.gefco.persistencia;

import java.util.List;

import net.gefco.modelo.Pole;
import net.gefco.modelo.Usuario;

public class PoleDaoHibernateTemplateImplementation 
	extends AbstractDaoHibernateTemplate<net.gefco.modelo.Pole, Integer> 
	implements PoleDao {
	
	public List<Pole> listarPorUsuario(Usuario usuario){	
		usuario = (Usuario) hibernateTemplate.get(Usuario.class, usuario.getUsua_codigo());
		return usuario.getPoles();		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<net.gefco.modelo.Pole> listarTodos() {
		List<net.gefco.modelo.Pole> lp = hibernateTemplate.find("select p from Pole p");
		return lp;
	}
}
