package net.gefco.bbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public class IdiomasBB {

	public List<SelectItem> getListaIdiomasSI(){
		List<SelectItem> lista = new ArrayList<SelectItem>();
		lista.add(new SelectItem("es","Espa�ol"));
		lista.add(new SelectItem("en","Ingles"));
		return lista;
	}
	
}
