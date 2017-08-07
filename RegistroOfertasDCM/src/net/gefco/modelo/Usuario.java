package net.gefco.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int usua_codigo;
	private String usua_nombre;

	@ManyToOne
	@JoinColumn(name = "usua_agencia", referencedColumnName = "agen_codigo")
	private Agencia usua_agencia = new Agencia();	

	//el siguiente responsable es un usuario, pero lo hacemos como int 
	//pq no somos capaces de implementar  relaciones recursivas.
	private int usua_responsable;	
		
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(joinColumns={@JoinColumn(name="uspo_usuario",referencedColumnName="usua_codigo")},  
			inverseJoinColumns={@JoinColumn(name="uspo_pole",referencedColumnName="pole_codigo")})
	private List<Pole> poles=new ArrayList<Pole>();
	@Size(max=5)
    private String usua_login;
	private String usua_pw;
	private String usua_idioma;
	private int usua_dias_alerta;

	@ManyToOne
	@JoinColumn(name = "usua_rol", referencedColumnName = "rol_codigo")
	private Rol usua_rol = new Rol();

	public Usuario() {
		super();
		//  Auto-generated constructor stub
	}
	 
	public Usuario(int usuaCodigo, String usuaNombre, Agencia usuaAgencia,
			int usuaResponsable, List<Pole> poles,
			String usuaLogin, String usuaPw, String usuaIdioma,
			int usuaDiasAlerta, Rol usuaRol) {
		super();
		usua_codigo = usuaCodigo;
		usua_nombre = usuaNombre;
		usua_agencia = usuaAgencia;
		usua_responsable = usuaResponsable;
		this.poles = poles;
		usua_login = usuaLogin;
		usua_pw = usuaPw;
		usua_idioma = usuaIdioma;
		usua_dias_alerta = usuaDiasAlerta;
		usua_rol = usuaRol;
	}



	public int getUsua_codigo() {
		return usua_codigo;
	}

	public void setUsua_codigo(int usuaCodigo) {
		usua_codigo = usuaCodigo;
	}

	public String getUsua_nombre() {
		return usua_nombre;
	}

	public void setUsua_nombre(String usuaNombre) {
		usua_nombre = usuaNombre;
	}

	public Agencia getUsua_agencia() {
		return usua_agencia;
	}

	public void setUsua_agencia(Agencia usuaAgencia) {
		usua_agencia = usuaAgencia;
	}
	
	public void setUsua_dias_alerta(int usua_dias_alerta) {
		this.usua_dias_alerta = usua_dias_alerta;
	}
	
	public int getUsua_dias_alerta() {
		return usua_dias_alerta;
	}

	public int getUsua_responsable() {
		return usua_responsable;
	}

	public void setUsua_responsable(int usuaResponsable) {
		usua_responsable = usuaResponsable;
	}

	public String getUsua_login() {
		return usua_login;
	}

	public void setUsua_login(String usuaLogin) {
		usua_login = usuaLogin;
	}

	public String getUsua_pw() {
		return usua_pw;
	}

	public void setUsua_pw(String usuaPw) {
		usua_pw = usuaPw;
	}

	public String getUsua_idioma() {
		return usua_idioma;
	}

	public void setUsua_idioma(String usuaIdioma) {
		usua_idioma = usuaIdioma;
	}

	public Rol getUsua_rol() {
		return usua_rol;
	}

	public void setUsua_rol(Rol usuaRol) {
		usua_rol = usuaRol;
	}
	
	public List<Pole> getPoles() {
		return poles;
	}

	public void setPoles(List<Pole> poles) {
		this.poles = poles;
	}
		
	public void copiarValores(Usuario aux, boolean copiarId) {
		if(copiarId){usua_codigo=aux.usua_codigo;}
		usua_nombre    		= aux.usua_nombre;
		usua_agencia		= aux.usua_agencia;
		usua_responsable 	= aux.usua_responsable;
		usua_login     		= aux.usua_login;
		usua_pw       		= aux.usua_pw;
		usua_rol       		= aux.usua_rol;
		usua_idioma    		= aux.usua_idioma;
		usua_dias_alerta	= aux.usua_dias_alerta;
		//poles				= aux.poles;
		poles=new ArrayList<Pole>(); 
		for (int e = 0; e < aux.poles.size(); e++) {
			poles.add(aux.poles.get(e));
		}
		
	}
	
	public void vaciar() {
		usua_codigo 		= 0;
		usua_nombre    		= null;
		usua_agencia		= new Agencia();
		usua_responsable 	= 0;
		usua_login     		= null;
		usua_pw       		= null;
		usua_rol       		= new Rol();
		usua_idioma    		= null;
		usua_dias_alerta	= 0;
		poles				= null;		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ usua_codigo;		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (usua_codigo != other.usua_codigo)
			return false;
		if (poles == null) {
			if (other.poles != null)
				return false;
		} else if (!poles.equals(other.poles))
			return false;
		return true;
	}
}
