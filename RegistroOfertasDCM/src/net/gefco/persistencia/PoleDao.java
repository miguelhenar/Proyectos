package net.gefco.persistencia;

import java.util.List;

import net.gefco.modelo.Pole;
import net.gefco.modelo.Usuario;

public interface PoleDao extends InterfaceDao<net.gefco.modelo.Pole, Integer>{
	public List<Pole> listarPorUsuario(Usuario usuario);
	public List<Pole> listarTodos();
}
