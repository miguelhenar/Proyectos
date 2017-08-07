package net.gefco.persistencia;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

@SuppressWarnings("unchecked")
public class AbstractDaoHibernateTemplate<T,k extends Serializable> implements InterfaceDao<T, k> {
	
	protected HibernateTemplate hibernateTemplate;

	private Class<T> tipo = (Class<T>)
							((ParameterizedType) getClass()
							.getGenericSuperclass()).getActualTypeArguments()[0];

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void insertar(T obj){
		hibernateTemplate.save(obj);
		hibernateTemplate.refresh(obj);
	}
	
	public void insertar(List<T> lista){
		for (T elemento:lista){
			if (!(elemento==null)){
				try {
					hibernateTemplate.save(elemento);
					hibernateTemplate.refresh(elemento);
				} catch (DataAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void modificar(T obj){
		hibernateTemplate.merge(obj);
	}
	
	public void borrar(k id){
		T obj = (T) hibernateTemplate.get(tipo, id);
		hibernateTemplate.delete(obj);
	}	
	
	public T buscar(k id){
		return (T) hibernateTemplate.get(tipo, id);
	}
	
	public List<T> buscar(String campo, Object valor){
		List<T> rs = hibernateTemplate.find("select a from "+tipo.getName()+" a where a." + campo +"=?",
				new Object[]{valor});
		return rs;
	}
	
	public List<T> listarTodos(){
		List<T> ls = hibernateTemplate.find("from "+tipo.getName());
		return ls;
	}		

	public List<T> listarTodosOrdenado(String campo){
		List<T> ls = hibernateTemplate.find("from "+tipo.getName()+" order by "+campo);	
		return ls;
	}
}
