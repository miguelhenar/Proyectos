package net.gefco.negocio;

import java.util.ArrayList;
import java.util.List;

import net.gefco.modelo.Usuario;
import net.gefco.persistencia.UsuarioDao;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



public class GestorUsuarios {

	private UsuarioDao usuarioDao;

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	
	public Usuario buscar(int usua_codigo) {
		return usuarioDao.buscar(usua_codigo);
	}
	
	public List<Usuario> buscar(String campo, Object valor) {
		return usuarioDao.buscar(campo,valor);
	}
	
	
	public Usuario buscarPorLogin(Usuario usuario){
		return usuarioDao.buscarPorLogin(usuario);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void insertar(Usuario usuario) {
		usuarioDao.insertar(usuario);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void modificar(Usuario usuario) {
		usuarioDao.modificar(usuario);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void borrar(Usuario usuario) {
		usuarioDao.borrar(usuario.getUsua_codigo());
	}

	public List<Usuario> listarTodos(){
		return usuarioDao.listarTodos();
	}

//	@Transactional(propagation=Propagation.REQUIRED)
	public List<Usuario> listarTodosOrdenado(Usuario usuario) {
		
		/*Si es administrador se devuelven s los usuarios...*/
		if(usuario.getUsua_rol().getRol_codigo().equals("ADMINISTRADOR")){
			return usuarioDao.listarTodosOrdenado();
		} else if (usuario.getUsua_rol().getRol_codigo().equals("RESPONSABLE POLE")){
			return usuarioDao.listarUsuariosDelResponsable(usuario);
		} else if (usuario.getUsua_rol().getRol_codigo().equals("KAM")){			
			List<Usuario> usuarios = new ArrayList<Usuario>();
			usuarios.add(usuario);
			return usuarios;
		} else if (usuario.getUsua_rol().getRol_codigo().equals("VISUALIZACION")){
			return usuarioDao.listarTodosOrdenado();
		}	else {
			return null;
		}
				
	}	
	

	public List<Usuario> listarResponsables(Usuario usuario) {
		return usuarioDao.listarResponsables(usuario);
	}

}