package net.gefco.manejoDataTable;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public class OperadoresDT {
	public OperadoresDT() {
		super();
	}
	
	public List<SelectItem> getListaOperadoresTextoSI() {
		List<SelectItem> listaSI = new ArrayList<SelectItem>();
		listaSI.add(new SelectItem("Contiene", "Contiene"));
		listaSI.add(new SelectItem("Igual a", "Igual a"));
		listaSI.add(new SelectItem("Empieza", "Empieza"));
		listaSI.add(new SelectItem("Termina", "Termina"));		
		return listaSI;
	}
	
	public List<SelectItem> getListaOperadoresNumeroSI() {
		List<SelectItem> listaSI = new ArrayList<SelectItem>();
		listaSI.add(new SelectItem("=", "="));
		listaSI.add(new SelectItem(">", ">"));
		listaSI.add(new SelectItem("<", "<"));
		listaSI.add(new SelectItem(">=", ">="));
		listaSI.add(new SelectItem("<=", "<="));
		return listaSI;
	}
}
	
