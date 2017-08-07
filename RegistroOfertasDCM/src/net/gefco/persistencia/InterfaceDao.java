package net.gefco.persistencia;

import java.io.Serializable;
import java.util.List;

public interface InterfaceDao<T, k extends Serializable> {

	public abstract void insertar(T obj);
	public abstract void insertar(List<T> lista);
	public abstract void modificar(T obj);
	public abstract void borrar(k id);
	public abstract T buscar(k id);
	public abstract List<T> buscar(String campo, Object valor);
	public abstract List<T> listarTodos();
	public abstract List<T> listarTodosOrdenado(String campo);
}