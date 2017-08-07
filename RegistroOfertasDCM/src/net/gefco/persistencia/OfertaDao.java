package net.gefco.persistencia;

import java.util.List;

import net.gefco.modelo.HistoricoOferta;
import net.gefco.modelo.Oferta;
import net.gefco.modelo.Usuario;
import net.gefco.modelo.dto.CBAlerta;
import net.gefco.modelo.dto.CBOferta;



public interface OfertaDao  extends InterfaceDao<net.gefco.modelo.Oferta, Integer>{	
	
	List<Oferta> listarOfertas(CBOferta cBOferta, Usuario usuario);
	
	List<Oferta> listarAlertas(CBAlerta cBAlerta, Usuario usuario);

	List<HistoricoOferta> listarHcoOferta(int oferCodigo);
}
