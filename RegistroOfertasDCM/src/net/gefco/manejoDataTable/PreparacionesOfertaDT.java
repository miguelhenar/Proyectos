package net.gefco.manejoDataTable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.gefco.modelo.PreparacionOferta;

import org.richfaces.model.DataProvider;
import org.richfaces.model.ExtendedTableDataModel;
import org.richfaces.model.selection.Selection;
import org.richfaces.model.selection.SimpleSelection;

public class PreparacionesOfertaDT {
	public PreparacionesOfertaDT() {
		super();
	}
	private String panelVisible;
	private List<PreparacionOferta> preparacionesOferta = new ArrayList<PreparacionOferta>();
	private List<PreparacionOferta> selected = new ArrayList<PreparacionOferta>();
	private Selection selection = new SimpleSelection();
	private String selectionMode = "single";
	private String sortMode = "single";
	private Object tableState;
	private ExtendedTableDataModel<PreparacionOferta> dataModel;
	
	public String getPanelVisible() {
		return panelVisible;
	}

	public void setPanelVisible(String panelVisible) {
		this.panelVisible = panelVisible;
	}

	public List<PreparacionOferta> getTiposVehiculo() {
		return preparacionesOferta;
	}

	public void setTiposVehiculo(List<PreparacionOferta> preparacionesOferta) {
		this.preparacionesOferta = preparacionesOferta;
	}

	public List<PreparacionOferta> getSelected() {
		return selected;
	}

	public void setSelected(List<PreparacionOferta> selected) {
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

	public ExtendedTableDataModel<PreparacionOferta> getDataModel() {
		return dataModel;
	}

	public void setDataModel(ExtendedTableDataModel<PreparacionOferta> dataModel) {
		this.dataModel = dataModel;
	}

	public void preparacionesOfertaTakeSelection() {
		selected.clear();
		Iterator<Object> iterator = getSelection().getKeys();
		while (iterator.hasNext()) {
			Object key = iterator.next();
			selected.add(getPreparacionOfertaesDataModel().getObjectByKey(key));
		}
	}

	@SuppressWarnings("serial")
	public ExtendedTableDataModel<PreparacionOferta> getPreparacionOfertaesDataModel() {
		if (dataModel == null) {
			dataModel = new ExtendedTableDataModel<PreparacionOferta>(
					new DataProvider<PreparacionOferta>() {
						//private static final long serialVersionUID = 5054087821033164847L;

						public PreparacionOferta getItemByKey(Object key) {
							for (PreparacionOferta c : preparacionesOferta) {
								if (key.equals(getKey(c))) {
									return c;
								}
							}
							return null;
						}

						public List<PreparacionOferta> getItemsByRange(int firstRow,
								int endRow) {
							return preparacionesOferta.subList(firstRow, endRow);
						}

						public Object getKey(PreparacionOferta item) {
							return item.getProf_codigo();
						}

						public int getRowCount() {
							return preparacionesOferta.size();
						}
					});
		}
		return dataModel;
	}	

	
	public boolean filtrarListado(Object current){				
		return true;
	}
}
