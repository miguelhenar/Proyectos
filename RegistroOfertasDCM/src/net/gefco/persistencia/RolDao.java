package net.gefco.persistencia;

import java.util.List;

public interface RolDao extends InterfaceDao<net.gefco.modelo.Rol, String> {
	public List<net.gefco.modelo.Rol> listarTodos();
}