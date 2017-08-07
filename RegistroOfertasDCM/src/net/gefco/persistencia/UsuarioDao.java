package net.gefco.persistencia;

import java.util.List;

import net.gefco.modelo.Usuario;

public interface UsuarioDao extends InterfaceDao<Usuario, Integer>{

	public Usuario buscarPorLogin(Usuario usuario);
	
	public List<Usuario> listarUsuariosDelResponsable(Usuario responsable);

	public List<Usuario> listarResponsables(Usuario usuario);
	
	public List<Usuario> listarTodosOrdenado();
	
//	public List<Usuario> listarTodos();
//	
//	public List<Usuario> listarTodosOrdenado(String campo);

	
}
