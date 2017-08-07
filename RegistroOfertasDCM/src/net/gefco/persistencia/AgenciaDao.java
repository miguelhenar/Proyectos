package net.gefco.persistencia;

import java.util.List;

public interface AgenciaDao extends InterfaceDao<net.gefco.modelo.Agencia, String>{
	public List<net.gefco.modelo.Agencia> listarTodos();
}
