package net.gefco.manejoDataTable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.gefco.modelo.Usuario;

import org.richfaces.model.DataProvider;
import org.richfaces.model.ExtendedTableDataModel;
import org.richfaces.model.selection.Selection;
import org.richfaces.model.selection.SimpleSelection;

public class UsuariosDT {
	public UsuariosDT() {
		super();
	}
	
	private String panelVisible;
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private List<Usuario> selected = new ArrayList<Usuario>();
	private Selection selection = new SimpleSelection();
	private String selectionMode = "single";
	private String sortMode = "single";
	private Object tableState;
	private ExtendedTableDataModel<Usuario> dataModel;
	private String filtroNombre ="";
	private String filtroNombreOperador ="Contiene";
	private String filtroAgencia ="";
	private String filtroAgenciaOperador ="Contiene";
	private String filtroRol ="";
	private String filtroRolOperador ="Contiene";
	
	public String getPanelVisible() {
		return panelVisible;
	}

	public void setPanelVisible(String panelVisible) {
		this.panelVisible = panelVisible;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Usuario> getListSelected() {
		return selected;
	}

	public void setListSelected(List<Usuario> selected) {
		this.selected = selected;
	}

	public Selection getSelection() {
		return selection;
	}

	public void setSelection(Selection selection) {
		this.selection = selection;
	}

	public String getSelectionMode() {
		return selectionMode;
	}

	public void setSelectionMode(String selectionMode) {
		this.selectionMode = selectionMode;
	}

	public String getSortMode() {
		return sortMode;
	}

	public void setSortMode(String sortMode) {
		this.sortMode = sortMode;
	}

	public Object getTableState() {
		return tableState;
	}

	public void setTableState(Object tableState) {
		this.tableState = tableState;
	}

	public ExtendedTableDataModel<Usuario> getDataModel() {
		return dataModel;
	}

	public void setDataModel(ExtendedTableDataModel<Usuario> dataModel) {
		this.dataModel = dataModel;
	}
	
	public String getFiltroAgencia() {
		return filtroAgencia;
	}

	public void setFiltroAgencia(String filtroAgencia) {
		this.filtroAgencia = filtroAgencia;
	}

	public String getFiltroAgenciaOperador() {
		return filtroAgenciaOperador;
	}

	public void setFiltroAgenciaOperador(String filtroAgenciaOperador) {
		this.filtroAgenciaOperador = filtroAgenciaOperador;
	}

	public String getFiltroNombre() {
		return filtroNombre;
	}

	public void setFiltroNombre(String filtroNombre) {
		this.filtroNombre = filtroNombre;
	}

	public String getFiltroNombreOperador() {
		return filtroNombreOperador;
	}

	public void setFiltroNombreOperador(String filtroNombreOperador) {
		this.filtroNombreOperador = filtroNombreOperador;
	}


	public void setSelected(List<Usuario> selected) {
		this.selected = selected;
	}

	public String getFiltroRol() {
		return filtroRol;
	}

	public void setFiltroRol(String filtroRol) {
		this.filtroRol = filtroRol;
	}

	public String getFiltroRolOperador() {
		return filtroRolOperador;
	}

	public void setFiltroRolOperador(String filtroRolOperador) {
		this.filtroRolOperador = filtroRolOperador;
	}
	public List<Usuario> getSelected() {
		return selected;
	}

	
	public void usuariosTakeSelection() {
		selected.clear();
		Iterator<Object> iterator = getSelection().getKeys();
		while (iterator.hasNext()) {
			Object key = iterator.next();
			selected.add(getUsuariosDataModel().getObjectByKey(key));
		}
	}

	@SuppressWarnings("serial")
	public ExtendedTableDataModel<Usuario> getUsuariosDataModel() {
		if (dataModel == null) {
			dataModel = new ExtendedTableDataModel<Usuario>(
					new DataProvider<Usuario>() {
						//private static final long serialVersionUID = 5054087821033164847L;

						public Usuario getItemByKey(Object key) {
							for (Usuario c : usuarios) {
								if (key.equals(getKey(c))) {
									return c;
								}
							}
							return null;
						}

						public List<Usuario> getItemsByRange(int firstRow,
								int endRow) {
							return usuarios.subList(firstRow, endRow);
						}

						public Object getKey(Usuario item) {
							return item.getUsua_codigo();
						}

						public int getRowCount() {
							return usuarios.size();
						}
					});
		}
		return dataModel;
	}	
	
	public boolean filtrarListado(Object current){
		Usuario usuario = (Usuario) current;		
		if (filtroAgencia.length()!=0) {
			switch (filtroAgenciaOperador) {
			case "Contiene":
				if (!(""+usuario.getUsua_agencia().getAgen_nombre()).toUpperCase().contains(filtroAgencia.toUpperCase())) {
					return false;
				}				
				break;
			case "Igual a":
				if (!(""+usuario.getUsua_agencia().getAgen_nombre()).toUpperCase().equals(filtroAgencia.toUpperCase())) {
					return false;
				}				
				break;
			case "Empieza":
				if (!(""+usuario.getUsua_agencia().getAgen_nombre()).toUpperCase().startsWith(filtroAgencia.toUpperCase())) {
					return false;
				}				
				break;
			case "Termina":
				if (!(""+usuario.getUsua_agencia().getAgen_nombre()).toUpperCase().endsWith(filtroAgencia.toUpperCase())) {
					return false;
				}				
				break;
			}
		}
		if (filtroNombre.length()!=0) {
			switch (filtroNombreOperador) {
			case "Contiene":
				if (!(""+usuario.getUsua_nombre()).toUpperCase().contains(filtroNombre.toUpperCase())) {
					return false;
				}				
				break;
			case "Igual a":
				if (!(""+usuario.getUsua_nombre()).toUpperCase().equals(filtroNombre.toUpperCase())) {
					return false;
				}				
				break;
			case "Empieza":
				if (!(""+usuario.getUsua_nombre()).toUpperCase().startsWith(filtroNombre.toUpperCase())) {
					return false;
				}				
				break;
			case "Termina":
				if (!(""+usuario.getUsua_nombre()).toUpperCase().endsWith(filtroNombre.toUpperCase())) {
					return false;
				}				
				break;
			} 
		}
		if (filtroRol.length()!=0) {
			switch (filtroRolOperador) {
			case "Contiene":
				if (!(""+usuario.getUsua_rol().getRol_descripcion()).toUpperCase().contains(filtroRol.toUpperCase())) {
					return false;
				}				
				break;
			case "Igual a":
				if (!(""+usuario.getUsua_rol().getRol_descripcion()).toUpperCase().equals(filtroRol.toUpperCase())) {
					return false;
				}				
				break;
			case "Empieza":
				if (!(""+usuario.getUsua_rol().getRol_descripcion()).toUpperCase().startsWith(filtroRol.toUpperCase())) {
					return false;
				}				
				break;
			case "Termina":
				if (!(""+usuario.getUsua_rol().getRol_descripcion()).toUpperCase().endsWith(filtroRol.toUpperCase())) {
					return false;
				}				
				break;
			} 
		}
		return true;
	}
}
