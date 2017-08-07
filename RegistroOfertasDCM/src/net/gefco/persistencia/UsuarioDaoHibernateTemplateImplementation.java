package net.gefco.persistencia;

import java.util.List;

import net.gefco.modelo.Usuario;

public class UsuarioDaoHibernateTemplateImplementation 
	extends AbstractDaoHibernateTemplate<net.gefco.modelo.Usuario, Integer> 
	implements UsuarioDao {
	
	@SuppressWarnings("unchecked")
	public Usuario buscarPorLogin(Usuario usuario){				
		//List<Usuario> rs = hibernateTemplate.find("select u from Usuario u where u.usua_login=? and u.usua_pw=?",
		//		new Object[]{usuario.getUsua_login(),usuario.getUsua_pw()});
		List<Usuario> rs = hibernateTemplate.find("select distinct u from Usuario u left join u.poles p " +
				"where u.usua_login=? and u.usua_pw=?",
				new Object[]{usuario.getUsua_login(),usuario.getUsua_pw()});
		if(rs.size()==1){
			return rs.get(0);			
		}
		
		usuario.setUsua_pw(null);
		List<Usuario> rs2 = hibernateTemplate.findByExample(usuario);
		if(rs2.size()==1){
			return usuario;
		}
		
		return null;	
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listarUsuariosDelResponsable(Usuario responsable){
//		List<Usuario> lu = hibernateTemplate.find("select u from Usuario u where u.usua_responsable=? and u.usua_codigo!=?",
//				new Object[]{responsable.getUsua_codigo(),responsable.getUsua_codigo()});
		List<Usuario> lu = hibernateTemplate.find("select distinct u from Usuario u left join u.poles p " +
				"where u.usua_responsable=? and u.usua_codigo!=?",
				new Object[]{responsable.getUsua_codigo(),responsable.getUsua_codigo()});
		lu.add(responsable);
		return lu;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listarResponsables(Usuario usuario) {
		//List<Usuario> lu = hibernateTemplate.find("from Usuario u where u.usua_rol='ADMINISTRADOR' or u.usua_rol='RESPONSABLE POLE' or u.usua_codigo=?",
		//		new Object[]{usuario.getUsua_codigo()});
		List<Usuario> lu = hibernateTemplate.find(
			"select distinct u from Usuario u left join u.poles p " +
			"where u.usua_rol.rol_codigo='ADMINISTRADOR' or u.usua_rol.rol_codigo='RESPONSABLE POLE' or u.usua_codigo=?",
			new Object[]{usuario.getUsua_codigo()});
		return lu;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Usuario> listarTodosOrdenado() {
		//List<Usuario> lu = hibernateTemplate.find("select u from Usuario u order by u.usua_nombre");
		List<Usuario> lu = hibernateTemplate.find("select distinct u from Usuario u left join u.poles p " +
				"order by u.usua_nombre");
		return lu;
	}
	
//	@SuppressWarnings("unchecked")
//	public List<Usuario> listarTodos(){
//		List<Usuario> lu = hibernateTemplate.find("select distinct u from Usuario u left join u.poles p");
//		return lu;
//	}		
//
//	@SuppressWarnings("unchecked")
//	public List<Usuario> listarTodosOrdenado(String campo){
//		List<Usuario> lu = hibernateTemplate.find("select distinct u from Usuario u left join u.poles p"
//				+" order by "+campo);
//		return lu;		
//	}
}