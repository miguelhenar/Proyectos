package net.gefco.bbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import net.gefco.modelo.Rol;
import net.gefco.negocio.GestorRoles;

public class RolesBB {

	private GestorRoles gestorRoles;

	public void setGestorRoles(GestorRoles gestorRoles) {
		this.gestorRoles = gestorRoles;
	}

	public List<SelectItem> getListaRolesSI() {
		List<Rol> listaR = gestorRoles.listarTodos();
		List<SelectItem> listaSI = new ArrayList<SelectItem>();
		listaSI.add(new SelectItem(null, "Seleccione..."));
		for (Rol r : listaR) {
			SelectItem si = new SelectItem(r.getRol_codigo(), r.getRol_descripcion());
			listaSI.add(si);
		}
		return listaSI;
	}
	

}
